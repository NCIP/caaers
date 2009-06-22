class AddResearchStaffLoginIDColumn  extends edu.northwestern.bioinformatics.bering.Migration {
    public void up(){
        addColumn('RESEARCH_STAFFS','LOGIN_ID','string');
    }

    public void down(){
        dropColumn('RESEARCH_STAFFS', 'LOGIN_ID');
    }
}

