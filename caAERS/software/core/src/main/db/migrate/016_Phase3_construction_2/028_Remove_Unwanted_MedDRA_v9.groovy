class RemoveMedDRAV9 extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("update studies set other_meddra_id = null where other_meddra_id in (select id from meddra_versions where id not in (select version_id from meddra_llt))");
        execute("update terminologies set meddra_version_id = null where meddra_version_id in (select id from meddra_versions where id not in (select version_id from meddra_llt))");
        execute("update disease_terminologies set meddra_version_id = null where meddra_version_id in (select id from meddra_versions where id not in (select version_id from meddra_llt))");
        execute("delete from meddra_versions where id not in (select version_id from meddra_llt)");
    }

    void down() {
        //not possible
    }
}