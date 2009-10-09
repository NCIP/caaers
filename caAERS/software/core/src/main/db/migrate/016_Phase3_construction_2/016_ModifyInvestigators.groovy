class ModifyInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("investigators","last_login", "date")
		
    }

    void down() {
        dropColumn("investigators", "last_login")
    }
}