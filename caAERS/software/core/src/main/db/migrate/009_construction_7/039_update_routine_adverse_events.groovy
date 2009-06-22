class UpdateAdverseEvents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      addColumn("ae_routine_reports","treatment_assignment_id", 'integer'); 
    }

    void down() {
   		dropColumn('ae_routine_reports','treatment_assignment_id')
    }
}