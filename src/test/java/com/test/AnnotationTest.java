package com.test;

import com.test.mapper.AuthorMapper;
import com.test.model.Author;
import com.test.model.Post;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * Created by Pavel Gavrilov
 */
public class AnnotationTest extends Assert implements JDBCParams{
    @BeforeClass
    public static void init() {
        MyBatisUtil.init(URL, USERNAME, PASSWORD, DRIVER);
        MyBatisUtil.addMappers(AuthorMapper.class);
    }

    @Test
    public void annotationPreparedTest() {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        AuthorMapper mapper = session.getMapper(AuthorMapper.class);

        List<Author> ivan = mapper.getAuthorByNamePrepared("ivan");
        assertEquals(3, ivan.size());

        session.close();
    }

    @Test
    public void annotationTest() {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        AuthorMapper mapper = session.getMapper(AuthorMapper.class);

        List<Author> ivan = mapper.getAuthorByName("ivan");
        assertEquals(3, ivan.size());

        session.close();
    }

    @Test
    public void getPostsTest() {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        AuthorMapper mapper = session.getMapper(AuthorMapper.class);

        List<Post> posts = mapper.getPostsByAuthorId(1);
        assertEquals(3, posts.size());

        session.close();
    }
}
