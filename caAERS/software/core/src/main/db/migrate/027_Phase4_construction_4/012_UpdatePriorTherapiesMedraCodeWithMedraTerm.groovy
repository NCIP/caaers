class UpdatePriorTherapiesMedraCodeWithMedraTerm extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
		 execute("update prior_therapies set meddra_code = meddra_term where id < 200");
    }

    void down() {
    
    }
}