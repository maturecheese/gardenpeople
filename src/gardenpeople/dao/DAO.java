package gardenpeople.dao;

import gardenpeople.db.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mark-i5 on 30/11/2014.
 */
public class DAO {

   protected Connection connection;
   protected PreparedStatement pStatement;
  // protected Statement stmt;




    public  DAO() { }

    protected static Connection getConnection()throws SQLException,ClassNotFoundException {

        Connection con = ConnectionFactory.getInstance().getConnection();
        return con;
    }

}
