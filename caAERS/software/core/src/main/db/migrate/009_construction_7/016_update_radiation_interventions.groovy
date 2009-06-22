class updateRadiationInterventions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         execute("DELETE FROM radiation_interventions ");
         addColumn('radiation_interventions','list_index','integer', nullable:true)
         renameTable('radiation_interventions','ae_radiation_interventions');
         
    }
      
    void down() {
    	renameTable('ae_radiation_interventions','radiation_interventions');
        dropColumn("radiation_interventions","list_index");
    }
}
