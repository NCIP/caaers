class updateSurgeryInterventions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         execute("DELETE FROM surgery_interventions ");
         addColumn('surgery_interventions','list_index','integer', nullable:true)
         renameTable('surgery_interventions','ae_surgery_interventions');
         
    }
      
    void down() {
    	renameTable('ae_surgery_interventions','surgery_interventions');
        dropColumn("surgery_interventions","list_index");
    }
}
