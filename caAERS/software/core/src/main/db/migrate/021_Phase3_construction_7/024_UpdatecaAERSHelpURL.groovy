class UpdatecaAERSBaseHelpURL extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
      execute("update configuration set value = 'https://cabig-kc.nci.nih.gov/CTMS/KC/index.php/CaAERS_2.3' where key = 'caaersBaseHelpUrl'");
    }

    void down() {
      // not required
    }
}