class ModifyAdverseEventAttributions extends edu.northwestern.bioinformatics.bering.Migration{
    void up() {
	setNullable('ae_attributions','cause_id', true);
    }
    
    void down() {
     //data could be present, so cannot revert
    }
}