package gardenpeople.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConnectionFactory {
    
    private static ConnectionFactory instance =
                new ConnectionFactory();
    String url = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_2e8f95e644";
    String user = "b031b192ea7961";
    String password = "8c4aa023";
    String driverClass = "com.mysql.jdbc.Driver";
     
   
    private ConnectionFactory() {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    public static ConnectionFactory getInstance()   {
        return instance;
    }
     
    public Connection getConnection() throws SQLException,
    ClassNotFoundException {
        Connection connection =
            DriverManager.getConnection(url, user, password);
        return connection;
    }  
}