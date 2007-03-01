package testapp.dao;

import java.util.Collection;

import testapp.bean.Statement;

public interface StatementDao {
	
	Statement findById(Long id);
	

	Long save(Statement statement);
	
	void update(Statement statement);

	Collection getAll();

}
