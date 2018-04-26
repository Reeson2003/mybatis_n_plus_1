package com.test;

import com.test.model.Author;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by Pavel Gavrilov
 */
public class XmlTest implements JDBCParams {
    @Test
    public void xmlTest() {
        MyBatisUtil.init(URL, USERNAME, PASSWORD, DRIVER);
        MyBatisUtil.addMapper("mappers/AuthorBestMapper.xml");
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();

        List<Author> ivan = session.selectList("AuthorBestMapper.selectAuthorBestPractice", "ivan");

        session.close();
    }
}
