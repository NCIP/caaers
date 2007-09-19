class UpdateConcomitantMedications extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 	 execute("ALTER TABLE concomitant_medications DROP CONSTRAINT fk_conmed_agent");
         dropColumn("concomitant_medications","agent_id");
         renameColumn('concomitant_medications', 'other', 'agent_name')
    }
      

    void down() {
        addColumn("concomitant_medications","agent_id", 'integer');
        execute("ALTER TABLE concomitant_medications ADD CONSTRAINT fk_conmed_agent FOREIGN KEY (agent_id) REFERENCES agents");
        renameColumn('concomitant_medications', 'agent_name', 'other')
    }
}