package testapp.dao.impl;

import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import testapp.bean.Statement;
import testapp.dao.StatementDao;

public class StatementDaoImpl extends HibernateDaoSupport implements StatementDao{

	public Statement findById(Long id) {
		Statement s = null;
		Collection c = getHibernateTemplate().find("from Statement s where s.id=?", id);
		if(c.size() > 1){
			throw new RuntimeException("Found " + c.size() + " statements with id " + id);
		}else if(c.size() == 1){
			s = (Statement)c.iterator().next();
		}
		return s;
	}

	public Long save(Statement stmt) {
		return (Long)getHibernateTemplate().save(stmt);
	}
	
	public void update(Statement stmt){
		getHibernateTemplate().update(stmt);
	}

	public Collection getAll() {
		return getHibernateTemplate().find("from Statement s order by s.date");
	}

}
