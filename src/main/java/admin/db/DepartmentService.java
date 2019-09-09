package admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Service;

import models.Department;

@Service
public class DepartmentService implements IService<Department> {
	
	@Override
	public Department add(Department department) throws SQLException, IdException {
		int departmentId;
		Department newD=null;
		String sqlAddDepartment="Insert INTO department(name) values(?)";
		try (Connection conn = DBManager.getInstance().getConnection()) {
			try (PreparedStatement statement = conn.prepareStatement(sqlAddDepartment,Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1,department.getName());
				
				int rowCountUpdated = statement.executeUpdate();

				ResultSet ids = statement.getGeneratedKeys();

				while (ids.next()) {
					departmentId = ids.getInt(1);
					String sqlresult="select id,name From department Where id=?";
					try(PreparedStatement command = conn.prepareStatement(sqlresult)){
						command.setInt(1,departmentId);
						ResultSet result=command.executeQuery();
						result.next();
						newD=new Department(result.getInt(1), result.getString(2));
						
					}
				}
			  }
			}
		return newD;
	}

	@Override
	public List<Department> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department find(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department update(Department object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}