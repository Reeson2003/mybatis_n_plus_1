package com.test;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.function.Consumer;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

/**
 * Created by Pavel Gavrilov
 */
public class MyBatisUtil {
    public static final Logger LOGGER = LogManager.getLogger(MyBatisUtil.class);
    private static MyBatisUtil instance = null;

    private SqlSessionFactory sqlSessionFactory;

    private MyBatisUtil(String url, String username, String password, String driver) {
        DataSource dataSource = getDataSource(url, username, password, driver);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.setLogImpl(Log4j2Impl.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    private MyBatisUtil(String configFilePath) {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(configFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized void init(String url, String username, String password, String driver) {
        if (instance == null)
            instance = new MyBatisUtil(url, username, password, driver);
    }

    public static synchronized void init(String configFilePath) {
        if (instance == null)
            instance = new MyBatisUtil(configFilePath);
    }

    public static synchronized void addMappers(Class... mappers) {
        Arrays.stream(mappers)
                .forEach(instance.sqlSessionFactory.getConfiguration()::addMapper);
    }

    public static synchronized void addMapper(String fileName) {
        InputStream in = MyBatisUtil.class.getClassLoader().getResourceAsStream(fileName) ;
        Configuration configuration = instance.sqlSessionFactory.getConfiguration() ;
        if (in != null){
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(in, configuration, fileName, configuration.getSqlFragments());
            xmlMapperBuilder.parse();
        }
    }

    private SqlSessionFactory getSessionFactory() {
        return sqlSessionFactory;
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return instance.getSessionFactory();
    }

    private DataSource getDataSource(String url, String username, String password, String driver) {
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriver(driver);
        return dataSource;
    }

    private void runScript(String fileName) {
        try {
            ScriptRunner runner = new ScriptRunner(sqlSessionFactory.openSession().getConnection());
            runner.setAutoCommit(true);
            runner.setSendFullScript(true);
            runner.setStopOnError(true);
            runner.setLogWriter(new Writer(LOGGER::debug));
            runner.setErrorLogWriter(new Writer(LOGGER::error));
            runner.runScript(getResourceAsReader(fileName));
            runner.closeConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runSqlScript(String fileName) {
        instance.runScript(fileName);
    }

    private static class Writer extends PrintWriter {
        Consumer<Object> print;
        private Writer(OutputStream out) {
            super(out);
        }

        public Writer(Consumer<Object> print) {
            super(System.out);
            this.print = print;
        }

        @Override
        public void print(Object obj) {
           print.accept(obj);
        }

        @Override
        public void println(Object x) {
            print.accept(x);
        }
    }
}
