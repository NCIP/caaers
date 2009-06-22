/*
 Move the FKs for all the 1:1 subcomponents of the AE report into the subcomponent tables
 */
class ReverseAerFkLocations extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("reporters", "report_id", "integer")
        addColumn("physicians", "report_id", "integer")
        addColumn("disease_history", "report_id", "integer")
        addColumn("participant_history", "report_id", "integer")
        execute("ALTER TABLE reporters ADD CONSTRAINT fk_reporter_report FOREIGN KEY (report_id) REFERENCES ae_reports")
        execute("ALTER TABLE physicians ADD CONSTRAINT fk_physician_report FOREIGN KEY (report_id) REFERENCES ae_reports")
        execute("ALTER TABLE disease_history ADD CONSTRAINT fk_disease_history_report FOREIGN KEY (report_id) REFERENCES ae_reports")
        execute("ALTER TABLE participant_history ADD CONSTRAINT fk_participant_history_report FOREIGN KEY (report_id) REFERENCES ae_reports")

        //execute("UPDATE reporters SET report_id=(SELECT aer.id FROM ae_reports aer WHERE aer.reporter_id=reporters.id)");
        //execute("UPDATE physicians SET report_id=(SELECT aer.id FROM ae_reports aer WHERE aer.physician_id=physicians.id)");
        //execute("UPDATE disease_history SET report_id=(SELECT aer.id FROM ae_reports aer WHERE aer.disease_history_id=disease_history.id)");
        //execute("UPDATE participant_history SET report_id=(SELECT aer.id FROM ae_reports aer WHERE aer.participant_history_id=participant_history.id)");

        dropColumn("ae_reports", "reporter_id")
        dropColumn("ae_reports", "physician_id")
        dropColumn("ae_reports", "disease_history_id")
        dropColumn("ae_reports", "participant_history_id")
    }

    void down() {
        addColumn("ae_reports", "reporter_id", "integer")
        addColumn("ae_reports", "physician_id", "integer")
        addColumn("ae_reports", "disease_history_id", "integer")
        addColumn("ae_reports", "participant_history_id", "integer")

        execute("UPDATE ae_reports SET reporter_id=(SELECT r.id FROM reporters r WHERE r.report_id=ae_reports.id)");
        execute("UPDATE ae_reports SET physician_id=(SELECT p.id FROM physicians p WHERE p.report_id=ae_reports.id)");
        execute("UPDATE ae_reports SET disease_history_id=(SELECT dh.id FROM disease_history dh WHERE dh.report_id=ae_reports.id)");
        execute("UPDATE ae_reports SET participant_history_id=(SELECT ph.id FROM participant_history ph WHERE ph.report_id=ae_reports.id)");

        dropColumn("reporters", "report_id")
        dropColumn("physicians", "report_id")
        dropColumn("disease_history", "report_id")
        dropColumn("participant_history", "report_id")
    }
}
