
package admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import models.Department;
import models.EmployeeData;
import models.Role;
import models.WorkSite;

@Service
public class EmployeeDataService implements IService<EmployeeData> {

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
				
			//select the roles for this employee and insert it into the found object
				int employeeId=result.getInt("U1.id");
				
				String sqlEmployeeRoles="select R.name"
										+ " from roles R JOIN userrole UR ON R.id=UR.roleid "
										+ "where UR.userid=?";
				try(PreparedStatement command2=conn.prepareStatement(sqlEmployeeRoles)){
					command2.setInt(1,employeeId);
					ResultSet result2=command.executeQuery();
					command2.setInt(1,employeeId);
					Role roles=null;
					while(result2.next()) {
						roles=new Role(result2.getString("R.name"));
						
					}
					
					found = new EmployeeData(
							result.getInt("U1.id"),
							result.getInt("U1.employeenum"),
							result.getString("U1.firstname"),
							result.getString("U1.lastname"),
							result.getString("U1.email"),
							result.getString("U2.firstname"),
							result.getInt("U1.managerid"),
							result.getString("U1.department"),
							result.getString("WS.name"),
							result.getInt("U1.worksiteid"),
							result.getString("U1.country"),
							result.getString("U1.phone"),
							result.getBoolean("U1.loginstatus"),
							result.getBoolean("U1.locked"),
							result.getBoolean("U1.deactivated")),
							
							
							result.getObject
							//add the list of roles into the found object
				}
			}
		}
		catch (Exception e) {
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
		List<Department> depatments = new ArrayList<Department>();
		String sqlSitesCommand = "select * from department";
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (Statement command = conn.createStatement()) {
				ResultSet result = command.executeQuery(sqlSitesCommand);
				while (result.next()) {
					depatments.add(new Department(result.getInt("id"), result.getString("name")));
				}
			}
		}
		return depatments;
	}

//get Managers (Name+ID) 
	public List<EmployeeData> findAllManagers() throws SQLException {
		List<EmployeeData> managers = new ArrayList<EmployeeData>();
		String sqlSitesCommand = "select U1.firstname ,U1.id "
								+ "From users U1 JOIN users U2 ON U1.id=U2.managerid "
								+ "JOIN userrole UR ON U1.id=UR.userid JOIN roles R ON UR.roleid=R.id"
								+ " Where R.name='manager'";
		
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (Statement command = conn.createStatement()) {
				
				ResultSet result = command.executeQuery(sqlSitesCommand);
				while (result.next()) {
					managers.add(new EmployeeData(result.getInt("U1.id"), result.getString("U1.name")));
				}
			}
		}
		return managers;
	}

	
	public EmployeeData add(EmployeeData employee) throws SQLException,IdException {
		String sqlAddEmployeeStatement = "Insert INTO users (employeenum,firstname,lastname,email,managerid,"
										+ "department,worksiteid,country,phone,loginstatus,locked,deactivated)"
										+ " values (?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (PreparedStatement statement = conn.prepareStatement(sqlAddEmployeeStatement)) {

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
				
				List<EmployeeData> employees=findAll();
				for(EmployeeData emp: employees) {
					if(emp.getId() == employee.getId())
						throw new IdException("Id already exists");
				}
				
				int rowCountUpdated=statement.executeUpdate();
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public EmployeeData update(EmployeeData employee) throws SQLException {
		
		String sqlDelEmployeeStatement = "update users set employeenum=?,firstname=?,lastname=?,"
										+ "email=?,managerid=?,department=?,worksiteid=?,"
										+ "country=?,phone=?,loginstatus=?,locked=?,deactivated=? where id=?";
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

				statement.setInt(13, employee.getId());
				
				int rowCountUpdated = statement.executeUpdate();
				
			}
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		return employee;
	}

	// Deactivate an employee
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
	public EmployeeData unlock(int id) throws SQLException{
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

	@Override
	public List<EmployeeData> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}