package btl_database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDataBase {
    private Connection connection = null;

    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\COSODULIEU\\b\\SqlServerConnect\\src\\main\\data.properties"));

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String url = properties.getProperty("dataURL");
        String user = properties.getProperty("user");
        String pass = properties.getProperty("password");
        if(connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(url,user,pass);
        }

        return connection;
    }

    public void Disconnect() throws SQLException {
        if (connection != null || !connection.isClosed()){
            connection.close();
        }
    }

}
