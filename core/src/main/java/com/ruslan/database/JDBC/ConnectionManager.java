package java.com.ruslan.database.JDBC;

import java.com.ruslan.DI.annotation.PostConstruct;
import java.com.ruslan.DI.config.ConfigProperties;
import java.com.ruslan.DI.config.Configuration;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Configuration
public final class ConnectionManager {


    @ConfigProperties(configFileName = "database.properties", propertyName = "url-mysql")
    private String url;

    @ConfigProperties(configFileName = "database.properties", propertyName = "user-mysql")
    private String user;
    @ConfigProperties(configFileName = "database.properties", propertyName = "password-mysql")
    private String password;

    @ConfigProperties(configFileName = "database.properties", propertyName = "pool-size", type = Integer.class)
    private Integer poolSize;

    @ConfigProperties(configFileName = "database.properties", propertyName = "driver-url")
    private String driverUrl;

    private final Integer DEFAULT_POOL_SIZE = 5;
    private BlockingQueue<Connection> pool;

    public ConnectionManager() {

    }

    @PostConstruct
    public void initConnectionManager() {
        loadDriver();
        Integer size = poolSize == null ? DEFAULT_POOL_SIZE : poolSize;
        pool = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            Connection connection = open();
            var proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close")
                            ? pool.add((Connection) proxy)
                            : method.invoke(connection, args));
            pool.add(proxyConnection);
        }
    }

    public Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    Connection open() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDriver() {
        try {
            Class.forName(driverUrl);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
