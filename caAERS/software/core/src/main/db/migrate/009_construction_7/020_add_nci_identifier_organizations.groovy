class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
        void up() {
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = 'DEFAULT' where ID=1")
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = 'CTEP' where ID=6")
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = 'NCI' where ID=2")
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = 'DUKE' where ID=4")
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = 'DCP' where ID=5")
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = 'WAKE' where ID=3")
           	 }

            void down() {
            	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = '' where ID='1'")
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = '' where ID='6'")
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = '' where ID='2'")
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = '' where ID='4'")
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = '' where ID='5'")
           	 	execute("update ORGANIZATIONS set NCI_INSTITUTE_CODE = '' where ID='3'")
        	}

}