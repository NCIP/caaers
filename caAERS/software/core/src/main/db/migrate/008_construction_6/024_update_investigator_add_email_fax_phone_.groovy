class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            addColumn("investigators","email_address", 'string');
            execute("UPDATE investigators set email_address='abc@email.com'") 
            setNullable("investigators", "email_address", false);
           	addColumn("investigators","phone_number", 'string');
           	execute("UPDATE investigators set phone_number='123-456-7890'") 
           	setNullable("investigators", "phone_number", false);
           	addColumn("investigators","fax_number", 'string');
    }

    void down() {
    
    
    		dropColumn("investigators","fax_number");
    		dropColumn("investigators","phone_number");
    		dropColumn("investigators","email_address");
    }
    
}