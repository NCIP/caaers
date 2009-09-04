package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.List;

/**
 * The intent of this class is to serve as an aggregate for the migration process.
 * Eg:- Incase of {@link Study}, the migration consist of migrating basic properties, and individual complex properties. 
 * 
 * @author Biju Joseph
 *
 * @param <E>
 */
public abstract class CompositeMigrator<E extends AbstractMutableDomainObject> implements Migrator<E>{
	List<Migrator<E>> children;
	
	
	public void add(Migrator<E> o){
		children.add(o);
	}
	public void remove(Migrator<E> o){
		children.remove(o);
	}

	public List<Migrator<E>> getChildren() {
		return children;
	}

	public void setChildren(List<Migrator<E>> children) {
		this.children = children;
	}
	
	/**
	 * The realized migrate method is purposely made final. The preMigrate template method is must be specialized.
	 */
	public  void migrate(E src, E dest, DomainObjectImportOutcome<E> outcome) {
		preMigrate(src, dest, outcome);
		if(children != null){
			for(Migrator<E> migrator : children){
				migrator.migrate(src, dest, outcome);
			}
		}
	}
	
	public abstract void preMigrate(E src, E dest , DomainObjectImportOutcome<E> outcome);
}