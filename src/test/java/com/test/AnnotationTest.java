package com.test;

import com.test.mapper.AuthorMapper;
import com.test.model.Author;
import com.test.model.Post;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by Pavel Gavrilov
 */
public class AnnotationTest implements JDBCParams{
    @Test
    public void annotationPreparedTest() {
        MyBatisUtil.init(URL, USERNAME, PASSWORD, DRIVER);
        MyBatisUtil.addMappers(AuthorMapper.class);
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        AuthorMapper mapper = session.getMapper(AuthorMapper.class);

        List<Author> ivan = mapper.getAuthorByNamePrepared("ivan");

        session.close();

        ivan.stream()
                .map(Author::getPosts)
                .forEach(System.out::println);
    }

    @Test
    public void annotationTest() {
        MyBatisUtil.init(URL, USERNAME, PASSWORD, DRIVER);
        MyBatisUtil.addMappers(AuthorMapper.class);
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        AuthorMapper mapper = session.getMapper(AuthorMapper.class);

        List<Author> ivan = mapper.getAuthorByName("ivan");

        session.close();
    }

    @Test
    public void getPostsTest() {
        MyBatisUtil.init(URL, USERNAME, PASSWORD, DRIVER);
        MyBatisUtil.addMappers(AuthorMapper.class);
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        AuthorMapper mapper = session.getMapper(AuthorMapper.class);

        List<Post> posts = mapper.getPostsByAuthorId(1);

        session.close();
    }
}
