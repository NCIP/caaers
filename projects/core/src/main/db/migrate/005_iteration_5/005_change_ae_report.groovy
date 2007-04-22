class ChangeAeReport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        // these columns can't be created not-null -- the ae_reports table
        // could already have rows in it.  Plus they shouldn't be not-null
        // for business reasons.
        addColumn('ae_reports', 'reporter_id', 'string');
        addColumn('ae_reports', 'physician_id', 'string');
    }

    void down() {
        removeColumn('ae_reports','reporter_id');
        removeColumn('ae_reports','physician_id');
    }
}