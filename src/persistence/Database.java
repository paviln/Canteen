package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private static final String URL = "jdbc:sqlserver://localhost:1433";
    private static final String DATABASENAME = "Canteen";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";

    public static Connection getConnection()
    {
        try
        {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            return DriverManager.getConnection(URL + ";databaseName=" + DATABASENAME, USERNAME, PASSWORD);
        } catch (SQLException ex)
        {
            throw new RuntimeException("Error connecting to the database");
        }
    }
}
