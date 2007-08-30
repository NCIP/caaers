class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            addColumn("research_staffs","email_address", 'string');
            execute("UPDATE research_staffs set email_address='abc@email.com'") 
           
            setNullable("research_staffs", "email_address", false);
            
           	addColumn("research_staffs","phone_number", 'string');
           	execute("UPDATE research_staffs set phone_number='123-456-7890'") 
           
           	setNullable("research_staffs", "phone_number", false);
           
           
           	addColumn("research_staffs","fax_number", 'string');
            

    }

    void down() {
    
    		dropColumn("research_staffs","fax_number");
    		
    		
    		dropColumn("research_staffs","phone_number");
    		
    		dropColumn("research_staffs","email_address");
    }
    
}