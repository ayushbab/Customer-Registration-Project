package customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import customer.Cust;

public class Custjdbc{
	

	private static String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "rootpasswordgiven ";

	private static final String INSERT_USERS_SQL = "INSERT INTO A" + "  (partyId,firstName,lastName,address,city,state,country,phone,zip,password) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select  from A partyId,firstName,lastName,address,city,state,country,phone,zip,password where partyId =?";
	private static final String SELECT_ALL_USERS = "select * from A";
	private static final String DELETE_USERS_SQL = "delete from A where partyId = ?;";
	private static final String UPDATE_USERS_SQL = "update A set firstname = ?,lastname=?,address=?,city=?,state=?,country=?,phone=?,zip=?,password=?where partyId = ?;";

	public Custjdbc() {
	}

	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(Cust user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getfirstName());
			preparedStatement.setString(2, user.getlastName());
			preparedStatement.setString(3, user.getAddress());
			preparedStatement.setString(4, user.getcity());
			preparedStatement.setString(5, user.getCountry());
			preparedStatement.setLong(6, user.getPhone());
			preparedStatement.setString(7, user.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public static Cust selectUser(int partyId) {
		Cust user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, partyId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String address = rs.getString("address");
				String city= rs.getString("city");
				String state = rs.getString("state");
			    String country = rs.getString("country");
				int phone = rs.getInt("phone");
				int postal = rs.getInt("zip");
				String password = rs.getString("password");
				user = new Cust(partyId,firstname,lastname,address,city,state,country,phone,zip,password );
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<Cust> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Cust> register = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int partyId = rs.getInt("partyId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String address = rs.getString("address");
				String city= rs.getString("city");
				String state = rs.getString("state");
				String country = rs.getString("country");
				int phone = rs.getInt("phone");
				int zip = rs.getInt("zip");
				String password = rs.getString("password");
				A.add(new Cust(partyId,firstName,lastName,address,city,state,country,phone,zip,password ));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return register;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(Cust user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setLong(1, user.getpartyId());
			statement.setString(1, user.getfirstName());
			statement.setString(2, user.getlastName());
			statement.setString(3, user.getAddress());
			statement.setString(4, user.getcity());
			statement.setString(5, user.getstate());
			statement.setString(5, user.getCountry());
			statement.setInt(6, user.getPhone());
			statement.setInt(8, user.getzip());
			statement.setString(9, user.getPassword());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

} 