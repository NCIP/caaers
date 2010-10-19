class ModifyRadiations extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("ae_radiation_interventions", "study_intervention_id", "integer")
        execute('alter table ae_radiation_interventions add constraint fk_rd_ntrvntn_id foreign key (study_intervention_id) references other_interventions (id)')
    }

    void down(){
        dropColumn("ae_radiation_interventions", "study_intervention_id")
    }
}
