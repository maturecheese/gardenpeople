package gardenpeople.dao;

import gardenpeople.db.ConnectionFactory;
import gardenpeople.model.County;
import gardenpeople.model.GardenOwner;
import gardenpeople.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountyDAO {
	Connection connection;
	Statement stmt;

	private int noOfRecords;

	public CountyDAO() {
	}

	private static Connection getConnection() throws SQLException,
			ClassNotFoundException {

		Connection con = ConnectionFactory.getInstance().getConnection();
		return con;
	}

	public ArrayList<County> findAll() {
		ArrayList<County> counties = new ArrayList<County>();
		try {
			String query = "Select * FROM counties";
			connection = getConnection();
			ResultSet resultSet = connection.createStatement().executeQuery(
					query);
			while (resultSet.next()) {
				County county = new County(resultSet.getInt("id"),
						resultSet.getString("name"));
				counties.add(county);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		Collections.sort(counties);
		return counties;
		

	}

	
}