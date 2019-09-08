package admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import models.Employees;
import models.Role;
@Service
public class EmployeesService implements IService<Employees> {

	@Override
	public List<Employees> findAll() throws SQLException {
		List<Employees> users=new ArrayList<Employees>();
		String sqlAllUserscommand="select U.id,U.employeenum,concat(U.firstname,U.lastname) as name,"
								+ "U.department,concat(WS.name,C.name) as worksite"
								+ " From users U JOIN worksite WS ON U.worksiteid=WS.id"
								+ " JOIN country C ON WS.countryid=C.id";
		try(Connection conn=DBManager.getInstance().getConnection()) {
			try(Statement command = conn.createStatement()){
				ResultSet result=command.executeQuery(sqlAllUserscommand);
				while(result.next()) {
					users.add(
							new Employees(
									result.getInt("U.id"),
									result.getInt("U.employeenum"),
									result.getString("name"),
									result.getString("U.department"),
									result.getString("worksite")
									));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	//find employee by name
		public List<Employees> find(String name) throws SQLException {

			List <Employees> found = new ArrayList<>();
			String sqlFindCommand ="SELECT U.id, U.employeenum,CONCAT(U.firstname,' ',U.lastname) as userName "
					+ ",W.name as workSiteName, U.department "
					+ "FROM users U join worksite W on U.worksiteid=W.id "
					+ "having userName=?";
			
			try (Connection conn = DBManager.getInstance().getConnection()) {
				try (PreparedStatement command = conn.prepareStatement(sqlFindCommand)) {
				 command.setString(1,name);
					ResultSet result = command.executeQuery();
					List<Integer> employees=new ArrayList<>();
					while(result.next()) {
						employees.add(result.getInt(1));
					}
					String sqlFindRoles="SELECT R.id,R.name" + 
							" FROM roles R join userrole US ON R.id=US.roleid" + 
							" WHERE US.userid=?";
					
					List<Role> employeeRoles=new ArrayList<>();
					try(PreparedStatement command2=conn.prepareStatement(sqlFindRoles)){
						for(int i=0;i<employees.size();i++) {
							command2.setInt(1,employees.get(i));
							ResultSet result2=command2.executeQuery();
							while(result2.next()) {
								employeeRoles.add(new Role(
										result2.getInt(1),
										result2.getString(2)));
							}
						}
						
					}
					for(Integer employee2:employees) {
					found.add(new Employees(
							result.getInt(1),
							result.getInt(2),
							result.getString(3),
							employeeRoles,
							result.getString(5),
							result.getString(6)
							));
				}}}
			 catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		}

	

	@Override
	public Employees find(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employees add(Employees object) throws SQLException, IdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employees update(Employees object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employees delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
