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
