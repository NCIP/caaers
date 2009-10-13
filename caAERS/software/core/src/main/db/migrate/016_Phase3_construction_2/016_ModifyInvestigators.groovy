class ModifyInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("investigators","last_login", "timestamp")
		
    }

    void down() {
        dropColumn("investigators", "last_login")
    }
}