// Unifies the subclasses of ExpeditedReportPerson into one table.
// Simplifies the relational model and, in particular, the relationship with contact_mechanisms.
// NOTE:  further refactoring to separate out the fields belonging to Person may or may not be
// desirable.  In that case, using joined-subclass (combined with the STI here), might work.

class MergePhysicianAndReporterIntoOneTable extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('ae_report_people') { t ->
            t.addVersionColumn()
            t.addColumn('first_name', 'string', nullable: false)
            t.addColumn('middle_name', 'string')
            t.addColumn('last_name', 'string', nullable: false)
            t.addColumn('report_id', 'integer')
            t.addColumn('grid_id', 'string')
            t.addColumn('role', 'string', nullable: false, limit: 2) // discriminator
        }

        execute("ALTER TABLE ae_report_people ADD CONSTRAINT fk_ae_people_aer FOREIGN KEY (report_id) REFERENCES ae_reports")

        // Demote contact mechanisms from a list of @Entity to a map @CollectionOfElements
        // referential integrity's going out the window anyway, so it's easier to drop and re-add.
        dropTable('contact_mechanisms')
        createTable('contact_mechanisms') { t ->
            t.setIncludePrimaryKey(false)
            t.addColumn('person_id', 'integer', nullable: false)
            t.addColumn('type', 'string', nullable: false)
            t.addColumn('value', 'string')
        }

        execute("ALTER TABLE contact_mechanisms ADD CONSTRAINT fk_contact_mech_person FOREIGN KEY (person_id) REFERENCES ae_report_people")

        dropTable('reporters')
        dropTable('physicians')
    }

    void down() {
        // copied from 5-1
        createTable('reporters') { t ->
            t.addColumn('first_name', 'string', nullable:false)
            t.addColumn('last_name', 'string', nullable:false)
            t.addColumn('middle_name', 'string', nullable:true)
            t.addColumn('maiden_name', 'string', nullable:true)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }
        // ... and from 5-2
        createTable('physicians') { t ->
            t.addColumn('first_name', 'string', nullable:false)
            t.addColumn('last_name', 'string', nullable:false)
            t.addColumn('middle_name', 'string', nullable:true)
            t.addColumn('maiden_name', 'string', nullable:true)
            t.addColumn('grid_id', 'string', nullable:true)
            t.addVersionColumn()
        }

        // Bering doesn't support adding a column matching the default PK, so
        // we have to drop and recreate this table
        dropTable('contact_mechanisms')
        // merged from 5-3 and 5-4
        createTable('contact_mechanisms') { t ->
            t.addColumn('type', 'string', nullable:false)
            t.addColumn('value', 'string')
            t.addColumn('reporter_id', 'string')
            t.addColumn('physician_id', 'string')
            t.addColumn('grid_id', 'string')
            t.addVersionColumn()
        }

        dropTable('ae_report_people')
    }
}