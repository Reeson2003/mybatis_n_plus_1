package com.test;

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
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
//        SqlSession session = sqlSessionFactory.openSession();
        List<Object> ivan = session.selectList("AuthorBestMapper.selectAuthorBestPractice", "ivan");
        System.out.println("ivan = " + ivan);
        session.close();
    }
}
