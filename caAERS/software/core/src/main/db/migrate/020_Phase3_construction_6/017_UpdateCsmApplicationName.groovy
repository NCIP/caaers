class UpdateCsmApplicationName extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
		execute("update csm_application set application_name='CTMS_SUITE' where application_name='caaers' and application_id = -1;");      
    }
    void down(){
      //not required. 
    }
}