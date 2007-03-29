class UseCtcTermForAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("ALTER TABLE adverse_events DROP CONSTRAINT fk_adv_ev_ctc_ver");
        dropColumn("adverse_events", "term")
        dropColumn("adverse_events", "ctc_category_id")
        dropColumn("adverse_events", "ctc_version_id")
        addColumn("adverse_events", "ctc_term_id", "integer")
        execute("ALTER TABLE adverse_events ADD CONSTRAINT fk_adv_ev_ctc_term FOREIGN KEY (ctc_term_id) REFERENCES ctc_terms")
    }

    void down() {
        execute("ALTER TABLE adverse_events DROP CONSTRAINT fk_adv_ev_ctc_term");
        dropColumn("adverse_events", "ctc_term_id")
        addColumn("adverse_events", "term", "string")
        addColumn("adverse_events", "ctc_category_id", "integer")
        addColumn("adverse_events", "ctc_version_id", "integer")
        execute("ALTER TABLE adverse_events ADD CONSTRAINT fk_adv_ev_ctc_ver FOREIGN KEY (ctc_version_id) REFERENCES ctc_versions");
    }
}