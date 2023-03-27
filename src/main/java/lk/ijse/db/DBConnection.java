package lk.ijse.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/grama_vista",
                "root",
                "1234"
        );
    }

    public static DBConnection getInstance() throws SQLException {
        return (dbConnection==null ) ? new DBConnection() : dbConnection;
    }

    public Connection getConnection(){ return connection; }
}
