package testapp.dao;

import java.util.Collection;

import testapp.bean.Person;

public interface PersonDao {
	
	Person findById(Long id);
	
	Collection findByName(String name);
	
	Long save(Person person);
	
	void update(Person person);

	Collection getAll();

}
