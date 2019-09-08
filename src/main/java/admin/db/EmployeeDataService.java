
package admin.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.springframework.stereotype.Service;

import models.Department;
import models.EmployeeData;
import models.Role;
import models.WorkSite;

import javax.servlet.*;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
//import javax.mail.Authenticator;
import javax.mail.internet.*;

@Service
public class EmployeeDataService implements IService<EmployeeData> {
	@Override
	public List<EmployeeData> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public EmployeeData find(int id) throws SQLException {

		EmployeeData found = null;
		String sqlFindCommand = "Select U1.*,U2.firstname,WS.name"
				+ " From users U1 JOIN users U2 ON U1.managerid=U2.id"
				+ " JOIN worksite WS ON U1.worksiteid=WS.id Where U1.worksiteid=WS.id AND U1.id=?";
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (PreparedStatement command = conn.prepareStatement(sqlFindCommand)) {
				command.setInt(1, id);
				ResultSet result = command.executeQuery();
				result.next();

				int employeeId = result.getInt("U1.id");

				String sqlEmployeeRoles = "Select R.name" + " From roles R JOIN userrole UR ON R.id=UR.roleid "
						+ "Where UR.userid=?";
				List<Role> roles = new ArrayList<>();

				try (PreparedStatement command2 = conn.prepareStatement(sqlEmployeeRoles)) {
					command2.setInt(1, employeeId);
					ResultSet result2 = command2.executeQuery();

					while (result2.next()) {
						roles.add(new Role(result2.getString(1)));
					}
				}

				found = new EmployeeData(result.getInt("U1.id"), result.getInt("U1.employeenum"),
						result.getString("U1.firstname"), result.getString("U1.lastname"), result.getString("U1.email"),
						result.getString("U2.firstname"), result.getInt("U1.managerid"),
						result.getString("U1.department"), result.getString("WS.name"), result.getInt("U1.worksiteid"),
						result.getString("U1.country"), result.getString("U1.phone"),
						result.getBoolean("U1.loginstatus"), result.getBoolean("U1.locked"),
						result.getBoolean("U1.deactivated"), result.getString("U1.password"), roles);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}

	
// get sites name
	public List<WorkSite> findAllSites() throws SQLException {
		List<WorkSite> sites = new ArrayList<WorkSite>();
		String sqlSitesCommand = "Select id,name from worksite";
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (Statement command = conn.createStatement()) {
				ResultSet result = command.executeQuery(sqlSitesCommand);
				while (result.next()) {
					sites.add(new WorkSite(result.getInt("id"), result.getString("name")));
				}
			}
		}
		return sites;
	}

	
// get roles name
	public List<Role> findAllRoles() throws SQLException {
		List<Role> roles = new ArrayList<Role>();
		String sqlSitesCommand = "Select id,name From roles";
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (Statement command = conn.createStatement()) {
				ResultSet result = command.executeQuery(sqlSitesCommand);
				while (result.next()) {
					roles.add(new Role(result.getInt("id"), result.getString("name")));
				}
			}
		}
		return roles;
	}

	
//get departments name
	public List<Department> findAllDepartments() throws SQLException {
		List<Department> departments = new ArrayList<Department>();
		String sqlDepartmetsCommand = "select * from department";
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (Statement command = conn.createStatement()) {
				ResultSet result = command.executeQuery(sqlDepartmetsCommand);
				while (result.next()) {
					departments.add(new Department(result.getInt(1), result.getString(2)));
				}
			}
		}
		return departments;
	}

	
//get Managers (Name+ID) 
	public List<EmployeeData> findAllManagers() throws SQLException {
		List<EmployeeData> managers = new ArrayList<EmployeeData>();
		String sqlSitesCommand = "select U1.id,U1.firstname " + "From users U1 JOIN users U2 ON U1.id=U2.managerid "
				+ "JOIN userrole UR ON U1.id=UR.userid JOIN roles R ON UR.roleid=R.id" + " Where R.name='manager'";

		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (Statement command = conn.createStatement()) {

				ResultSet result = command.executeQuery(sqlSitesCommand);
				while (result.next()) {
					managers.add(new EmployeeData(result.getInt(1), result.getString(2)));
				}
			}
		}
		return managers;
	}

	
//what does the employee object contains??
	@Override
	public EmployeeData add(EmployeeData employee) throws SQLException, IdException {
		int employeeID;
		EmployeeData newEmployee = null;
		String sqlAddEmployeeStatement = "Insert INTO users (employeenum,firstname,lastname,email,managerid,"
				+ "department,worksiteid,country,phone,loginstatus,locked,deactivated,password)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (PreparedStatement statement = conn.prepareStatement(sqlAddEmployeeStatement,
					Statement.RETURN_GENERATED_KEYS)) {

				statement.setInt(1, employee.getnumber());
				statement.setString(2, employee.getFirstName());
				statement.setString(3, employee.getLastName());
				statement.setString(4, employee.getEmail());
				statement.setInt(5, employee.getManagerId());
				statement.setString(6, employee.getDepartment());
				statement.setInt(7, employee.getWorkSiteId());
				statement.setString(8, employee.getCountry());
				statement.setString(9, employee.getPhone());
				statement.setBoolean(10, employee.getLoginStatus());
				statement.setBoolean(11, employee.getLocked());
				statement.setBoolean(12, employee.getDeactivated());
				statement.setString(13, employee.getPassword());

				int rowCountUpdated = statement.executeUpdate();

				ResultSet ids = statement.getGeneratedKeys();

				while (ids.next()) {
					employeeID = ids.getInt(1);
					System.out.println(employeeID);
					newEmployee = find(employeeID);
				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return newEmployee;
	}

	
//Update Employee's Info
	// the employee object contains also the roles
	// how to update the Employee's manager...pick from a managers list??
	@Override
	public EmployeeData update(EmployeeData employee) throws SQLException {

		String sqlDelEmployeeStatement = "update users set employeenum=?,firstname=?,lastname=?,"
				+ "email=?,managerid=?,department=?,worksiteid=?,"
				+ "country=?,phone=?,loginstatus=?,locked=?,deactivated=?,password=? where id=?";
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (PreparedStatement statement = conn.prepareStatement(sqlDelEmployeeStatement)) {

				statement.setInt(1, employee.getnumber());
				statement.setString(2, employee.getFirstName());
				statement.setString(3, employee.getLastName());
				statement.setString(4, employee.getEmail());
				statement.setInt(5, employee.getManagerId());
				statement.setString(6, employee.getDepartment());
				statement.setInt(7, employee.getWorkSiteId());
				statement.setString(8, employee.getCountry());
				statement.setString(9, employee.getPhone());
				statement.setBoolean(10, employee.getLoginStatus());
				statement.setBoolean(11, employee.getLocked());
				statement.setBoolean(12, employee.getDeactivated());
				statement.setString(13, employee.getPassword());

				statement.setInt(14, employee.getId());

				int rowCountUpdated = statement.executeUpdate();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return employee;
	}

	
//Deactivate an employee
	@Override
	public EmployeeData delete(int id) throws SQLException {

		String sqlDelEmployeeStatement = "update users set deactivated=true where id=?";
		EmployeeData deactevatedEmployee = null;
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (PreparedStatement statment = conn.prepareStatement(sqlDelEmployeeStatement)) {
				statment.setInt(1, id);

				int res = statment.executeUpdate();

				deactevatedEmployee = find(id);
			}
		}
		return deactevatedEmployee;
	}

	
//unlock Employee
	public EmployeeData unlock(int id) throws SQLException {
		String sqlUnlockEmployeeStatement = "update users set locked=true where id=?";
		EmployeeData lockedEmployee = null;
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (PreparedStatement statment = conn.prepareStatement(sqlUnlockEmployeeStatement)) {
				statment.setInt(1, id);

				int res = statment.executeUpdate();
				lockedEmployee = find(id);
			}
		}
		return lockedEmployee;
	}

	
	
	public void resetPassword(String toMail) throws SQLException, IdException {

		String sqlDelEmployeeStatement = "SELECT * from employee where email=?";
		String newPassword;
		String toEmail = toMail;
		EmployeeData employee;

		if (!isValid(toEmail))
			throw new IdException("email not found");

		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (PreparedStatement statment = conn.prepareStatement(sqlDelEmployeeStatement)) {
				statment.setString(1, "email");
				ResultSet result = statment.executeQuery(sqlDelEmployeeStatement);

				/*
				 * check if email exists in the Data base if resultSet is empty
				 */
				if (result.next() == false)
					throw new IdException("Email not found");

				/*
				 * generatePassword takes a number as an input (must be greater than 6) and
				 * generates a random password including numbers and symbols.
				 * 
				 * @return A string.
				 */
				newPassword = generatePassword(10);

				/*
				 * before sending the email, new password must be updated and saved in the
				 * database.
				 */

				try {
					employee = find(result.getInt("id")); // find gets the id of employee and returns the employee
					try {
						employee.setPassword(newPassword);
						update(employee); // update the new password of this employee in the database.
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					sendEmail(toEmail, newPassword, result.getString("firstname"), result.getInt("id"));
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (IdException e) {
					e.printStackTrace();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IdException e) {
			e.printStackTrace();
		}

	} // end of resetPassword

	
	
	private static boolean isValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	
	
	private String generatePassword(int length) {
		Random random = new Random();
		String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String smallLetters = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String symbols = "~!@#$%^&*?";
		String fullPassword = capitalLetters + symbols + numbers + smallLetters;
		StringBuilder password = new StringBuilder();

		// generate a new length-digit password from the 'fullPassword' combination
		for (int i = 0; i < length; i++)
			password.append(fullPassword.charAt(random.nextInt(fullPassword.length())));
		return password.toString();
	}

	
	
	private void sendEmail(String toMail, String newPassword, String firstName, Integer id) throws IdException, MessagingException { //, String newPassword, String firstName) throws MessagingException {

		final String password = "amdocs123";
		String fromEmail = "fullstacktest4@gmail.com";
		String toEmail = toMail;
		
		if( !isValid(toEmail))
			throw new IdException("not found");
		
		
		Properties properties = new Properties(); // for sending email
		properties.put("mail.smtp.auth", "true"); // authentication
		properties.put("mail.smtp.starttls.enable", "true"); // tls encryption
		properties.put("mail.smtp.host", "smtp.gmail.com"); // host: Gmail
		properties.put("mail.smtp.port", "587"); // SMTP port for Gmail is '587'

		javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		// start the mail message

//		System.out.println("your password is:" + newPassword);
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(newPassword + " is your Amdocs account new password");
			message.setSentDate(new Date());

			// text body part
			message.setText("Hi " + firstName + "\n\n" + "We received a request to reset your Amdocs password. \n"
					+ "Enter the following password:\n\n" + newPassword + "\n \n"
					+ "You can directly change your password");

			Transport.send(message);
			
			System.out.println("sent!");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) throws SQLException, IdException {
		EmployeeDataService emp = new EmployeeDataService();
		emp.resetPassword("majdrezik@gmail.com");
	}

}
