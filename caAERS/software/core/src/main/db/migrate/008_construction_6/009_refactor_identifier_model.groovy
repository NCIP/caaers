class RefactorIdentifierModel extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    
       		
	    	//setNullable('identifiers', 'source', true);
	    	//setNullable('IDENTIFIERS', 'organization_id', true);
	    	//setNullable('IDENTIFIERS', 'system_name', true);
	     	
	     	
	    	execute("update identifiers set organization_id = (select id from organizations where name like source)");
	     	execute("update identifiers set discriminator_column = 1 where organization_id is not null");
	     	execute("update identifiers set source ='' where organization_id is not null");
	     	
	     	dropColumn('IDENTIFIERS','system_name');
	     	
	     	renameColumn('IDENTIFIERS','source','system_name');
   	 	}

    void down() {
       
    		renameColumn('IDENTIFIERS','system_name',"source");
    		
    		addColumn("identifiers","system_name", 'string');
    		
    		execute("update identifiers set source =(select name from organizations where  where id=organization_id");
    		execute("update identifiers set discriminator_column = 2 where discriminator_column = 1");
    		execute("update identifiers set organization_id =null where system_name=''");
    		
    		setNullable('IDENTIFIERS', 'system_name', false);
			setNullable('IDENTIFIERS', 'organization_id', false);	     	
      		setNullable('IDENTIFIERS','source',false);
      		
	    	}
	    }