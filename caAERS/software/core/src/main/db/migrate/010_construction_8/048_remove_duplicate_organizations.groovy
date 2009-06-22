class DeleteDuplicateOrganizations extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 	execute('delete from organizations where id = (select max(id) from organizations where nci_institute_code = \'NCI\')');
    }
    void down() {
    	
    }
}