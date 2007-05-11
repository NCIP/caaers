class ChangeIdentifiers extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('identifiers','stu_id', 'integer' , nullable:true);    
    }

    void down() {
        removeColumn('identifiers','stu_id');
    }
}