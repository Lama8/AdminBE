package admin.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import models.Role;
import models.Permission;

@Service
public class RoleService implements IService<Role> {
	
// added today
@Override
public List<Role> findAll() throws SQLException {
	List<Role> roles = new ArrayList<>();
	List<Role> rolesWithPermissions = new ArrayList<>();

	String sqlFindRoles="select id,name from roles";
	String findRolePermissions="select P.id,P.name"
							+ " from roles R JOIN permissions P ON R.id=P.role_id where P.role_id=?";
	
	try(Connection conn = DBManager.getInstance().getConnection()){			
		try(Statement command = conn.createStatement()){
			ResultSet result = command.executeQuery(sqlFindRoles);
			
			while(result.next()) {
				roles.add(new Role(result.getInt("id"),
						   result.getString("name")
						   ));
				}
			}
			try(PreparedStatement command2=conn.prepareStatement(findRolePermissions)){
				for(Role role:roles) {
					command2.setInt(1,role.getId());
					ResultSet result2=command2.executeQuery();
					List<Permission> rolePermissions=new ArrayList<>();

					while(result2.next()){
						rolePermissions.add(new Permission(
								result2.getInt(1),
								result2.getString(2)
								));
					}
					rolesWithPermissions.add(new Role(
							role.getId(),
							role.getName(),
							rolePermissions));

				}
			}
				
				
	}
	return rolesWithPermissions;

}
@Override
public Role find(int id) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Role add(Role object) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Role update(Role object) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Role delete(int id) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}


}
