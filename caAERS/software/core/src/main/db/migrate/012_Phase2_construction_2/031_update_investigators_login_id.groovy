class UpdateInvestigatorsAddSupport extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("update investigators set login_id = email_address where login_id is null")
    }

    void down() {
       //not possible
    }
}