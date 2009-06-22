class ChangeStudiesNullableSponsorCode extends edu.northwestern.bioinformatics.bering.Migration{
    void up() {
	setNullable('studies','primary_sponsor_code', true);
    }
    
    void down() {
     //data could be present, so cannot revert
    }
}