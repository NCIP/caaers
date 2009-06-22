class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	addColumn('agents','nsc' , 'string' , nullable:true);
    }

    void down() {
        removeColumn("agents", "nsc");
    }
}