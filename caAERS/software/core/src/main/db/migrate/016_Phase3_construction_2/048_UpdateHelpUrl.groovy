class UpdateHelpUrl extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){

        execute("update configuration set value='https://cabig-kc.nci.nih.gov/CTMS/KC/index.php/CaAERSv2.0.1_End_User_Guide' where key='caaersBaseHelpUrl'")

    }
  
	void down(){

    }
}