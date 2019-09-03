package admin.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
//	private final static String URL = "jdbc:mysql://localhost:3306/admin?useLegacyDatetimeCode=false&serverTimezone=IST";
//	private final static String USER = "root";
//	private final static String PASS = "Lamaaza15894";
//	
	private static DBManager instance;
	private Properties props;
	private DBManager() {
		try {
			props = new Properties();
			FileInputStream input = new FileInputStream("db.config"); 
			props.load(input); 
			input.close(); 
			System.out.println("mysql driver has been loaded");
			} catch (Exception e) {
				System.out.println(e);
				}
	}
	
	public static DBManager getInstance() {
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(props.getProperty("URL"), props);
	}
	
}
