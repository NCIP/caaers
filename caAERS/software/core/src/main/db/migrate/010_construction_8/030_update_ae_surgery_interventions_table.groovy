class updateAeSurgeryInterventions  extends edu.northwestern.bioinformatics.bering.Migration {


    public void up(){
    	addColumn("ae_surgery_interventions", "intervention_site_id", "integer")
    	dropColumn("ae_surgery_interventions", "anatomic_site_id")
    	execute('ALTER TABLE ae_surgery_interventions ADD CONSTRAINT fk_ae_surgery_int_site FOREIGN KEY (intervention_site_id) REFERENCES intervention_sites')
    }


    public void down(){
    	addColumn("ae_surgery_interventions", "anatomic_site_id", "integer")
    	execute("ALTER TABLE ae_surgery_interventions DROP CONSTRAINT fk_ae_surgery_int_site");
    	dropColumn("ae_surgery_interventions", "intervention_site_id")
    }

  }
