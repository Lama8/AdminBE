package admin.db;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
	List<T> findAll() throws SQLException;
	T find(int id) throws SQLException;
	T add(T object) throws SQLException, IdException;
	T update(T object) throws SQLException;
	T delete(int id) throws SQLException;
}
