package com.test;

import com.test.mapper.AuthorMapper;
import com.test.model.Author;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by Pavel Gavrilov
 */
public class AnnotationTest implements JDBCParams{
    @Test
    public void annotationTest() {
        MyBatisUtil.init(URL, USERNAME, PASSWORD, DRIVER);
        MyBatisUtil.addMappers(AuthorMapper.class);
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        AuthorMapper mapper = session.getMapper(AuthorMapper.class);

        List<Author> ivan = mapper.getAuthorByName("ivan");
        System.out.println("ivan = " + ivan);
        session.close();
    }
}
