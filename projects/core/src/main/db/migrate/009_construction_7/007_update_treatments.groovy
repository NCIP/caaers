class UpdateTreatments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	 dropColumn("treatments","treatment_assignment_code");
         addColumn("treatments","treatment_assignment_id", 'integer');
         addColumn("treatments","total_courses","integer");
	 addColumn("treatments","treatment_description",'string');
         execute("ALTER TABLE treatments ADD CONSTRAINT fk_trmt_trmt_assgnmt FOREIGN KEY (treatment_assignment_id) REFERENCES treatment_assignment (id)");
    }
      
    void down() {
    	addColumn("treatments","treatment_assignment_code",'string');
    	execute("ALTER TABLE treatments drop CONSTRAINT fk_trmt_trmt_assgnmt");
        dropColumn("treatments","treatment_assignment_id");
        dropColumn("treatments","treatment_description");
        dropColumn("treatments","total_courses");
    }
}
