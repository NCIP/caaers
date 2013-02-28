/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
    private static Log logger = LogFactory.getLog(CompositeMigrator.class);
	List<Migrator<E>> children;
    private boolean stopOnError;
	
	
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

    public boolean getStopOnError() {
        return stopOnError;
    }

    public void setStopOnError(boolean stopOnError) {
        this.stopOnError = stopOnError;
    }

    /**
	 * The realized migrate method is purposely made final. The preMigrate template method is must be specialized.
	 */
	public  void migrate(E src, E dest, DomainObjectImportOutcome<E> outcome) {
		preMigrate(src, dest, outcome);
		if(children != null){
			for(Migrator<E> migrator : getChildren()){
                if(getStopOnError() && outcome.hasErrors()) {
                    logger.error("Stopping migration due to error");
                    logger.error(outcome.getValidationErrors());
                    return;
                }
				migrator.migrate(src, dest, outcome);
			}
		}
	}
	
	public abstract void preMigrate(E src, E dest , DomainObjectImportOutcome<E> outcome);
}
