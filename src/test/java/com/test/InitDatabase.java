package com.test;

import org.junit.Test;

/**
 * Created by Pavel Gavrilov
 */
public class InitDatabase implements JDBCParams {
    @Test
    public void initDatabase() {
        MyBatisUtil.init(URL, USERNAME, PASSWORD, DRIVER);
        MyBatisUtil.runSqlScript("sql/query.sql");
    }
}
