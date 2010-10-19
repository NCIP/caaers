class ModifySurgery extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_surgery_interventions", "study_intervention_id", "integer")
        execute('alter table ae_surgery_interventions add constraint fk_sr_ntrvntn_id foreign key (study_intervention_id) references other_interventions (id)')
    }

    void down(){
        dropColumn("ae_surgery_interventions", "study_intervention_id")
    }
}
