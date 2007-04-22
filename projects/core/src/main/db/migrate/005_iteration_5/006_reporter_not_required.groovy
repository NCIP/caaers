class ReporterNotRequired extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        setNullable('ae_reports', 'reporter_id' , true);
        setNullable('ae_reports', 'physician_id' , true);
    }

    void down() {
        setNullable('ae_reports', 'reporter_id' , false);
        setNullable('ae_reports', 'physician_id' , false);
    }
}