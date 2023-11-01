package com.ruslan.JDBC;


import com.ruslan.DI.ObjectFactory;
import com.ruslan.DI.context.ApplicationContext;

import java.sql.SQLException;
import java.util.HashMap;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {

        ApplicationContext applicationContext = new ApplicationContext();
        ObjectFactory objectFactory = new ObjectFactory(applicationContext, new HashMap<>());
        applicationContext.setObjectFactory(objectFactory);
        ConnectionManager connectionManager = applicationContext.getObject(ConnectionManager.class);

        var connect = connectionManager.get();

        System.out.println(connect.getTransactionIsolation());
        // statement.execute(sql);

    }
}

