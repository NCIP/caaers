class ChangeSiteInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('site_investigators','email_address' , 'string' , nullable:true);       
    }

    void down() {
        removeColumn('site_investigators','email_address');
    }
}