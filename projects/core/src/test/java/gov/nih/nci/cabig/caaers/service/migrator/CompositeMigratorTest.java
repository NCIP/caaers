package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class CompositeMigratorTest extends TestCase {

	public void testMigrate() {
		List<Migrator<Study>> list = new ArrayList<Migrator<Study>>();
		list.add(new MyMigratorOne());
		list.add(new MyMigratorTwo());
		MyCompositeMigrator migrator = new MyCompositeMigrator(list);
		DomainObjectImportOutcome<Study> outcome = new DomainObjectImportOutcome<Study>();
		migrator.migrate(null, null, outcome);
		assertEquals(3, outcome.getMessages().size());
		assertEquals("MyMigratorOne", outcome.getMessages().get(1).getMessage());
	}

}

class MyCompositeMigrator extends CompositeMigrator<gov.nih.nci.cabig.caaers.domain.Study>{
	
	public MyCompositeMigrator( List<Migrator<Study>> list) {
		super(list);
	}
	
	@Override
	public void preMigrate(Study src, Study dest,DomainObjectImportOutcome<Study> outcome) {
		outcome.addErrorMessage("MyCompositeMigrator", DomainObjectImportOutcome.Severity.ERROR);
	}
}

class MyMigratorOne implements Migrator<Study>{
	public void migrate(Study src, Study dest,
			DomainObjectImportOutcome<Study> outcome) {
		outcome.addErrorMessage("MyMigratorOne", DomainObjectImportOutcome.Severity.ERROR);
	}
}


class MyMigratorTwo implements Migrator<Study>{
	public void migrate(Study src, Study dest,
			DomainObjectImportOutcome<Study> outcome) {
		outcome.addErrorMessage("MyMigratorTwo", DomainObjectImportOutcome.Severity.ERROR);
	}
}