package testapp.dao.impl;

import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import testapp.bean.Person;
import testapp.dao.PersonDao;

public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao{

	public Person findById(Long id) {
		Person p = null;
		Collection c = getHibernateTemplate().find("from Person p where p.id=?", id);
		if(c.size() > 1){
			throw new RuntimeException("Found " + c.size() + " persons with id " + id);
		}else if(c.size() == 1){
			p = (Person)c.iterator().next();
		}
		return p;
	}

	public Collection findByName(String name) {
		return getHibernateTemplate().find("from Person p where p.name=?", name);
	}

	public Long save(Person person) {
		return (Long)getHibernateTemplate().save(person);
	}
	
	public void update(Person person){
		getHibernateTemplate().update(person);
	}

	public Collection getAll() {
		return getHibernateTemplate().find("from Person p order by p.name");
	}

}
