package admin.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Audit;

public class AuditService implements IService<Audit> {

	@Override
	public List<Audit> findAll() throws SQLException {
		List<Audit> audit=new ArrayList<Audit>();
		String sqlAllUserscommand="select A.date,A.time,U.firstname,A.activity "+ 
									"from users U JOIN audit A ON U.id=A.userid";
		try(Connection conn=DBManager.getInstance().getConnection()) {
			try(Statement command = conn.createStatement()){
				ResultSet result=command.executeQuery(sqlAllUserscommand);
				while(result.next()) {
					audit.add(
							new Audit(
									result.getInt("A.id"),
									result.getDate("A.date"),
									result.getTime("A.time"),
									result.getInt("U.firstname"),
									result.getString("A.activity")
									));
				}
			}
		}
		return audit;
	}
	

	@Override
	public Audit find(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Audit add(Audit object) throws SQLException, IdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Audit update(Audit object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Audit delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
