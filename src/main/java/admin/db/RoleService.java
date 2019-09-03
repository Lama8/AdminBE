package admin.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import models.Role;

@Service
public class RoleService implements IService<Role> {


public List<Role> findAll() throws SQLException {
	List<Role> roles = new ArrayList<Role>();
	
	try(Connection conn = DBManager.getInstance().getConnection()){			
		try(Statement command = conn.createStatement()){
			
			ResultSet result = command.executeQuery("SELECT * from roles");
			
			while(result.next()) {
				roles.add(new Role(result.getInt("id"),
								   result.getString("name")));
			}				
		}
	}
	return roles;

}

public Role find(int id) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}

public Role add(Role object) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}

public Role update(Role object) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}

public Role delete(int id) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}

public Date testTimezone() throws SQLException {
	try(Connection conn = DBManager.getInstance().getConnection()){			
		try(Statement command = conn.createStatement()){
			
			ResultSet result = command.executeQuery("call test_timezone()");
			
			while(result.next()) {
				return result.getDate(1);
			}				
		}
	}
	return Date.valueOf("");
}


}
