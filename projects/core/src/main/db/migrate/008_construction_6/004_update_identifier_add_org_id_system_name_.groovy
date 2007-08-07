class CreateParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
            addColumn("identifiers","organization_id", 'integer')
             addColumn("identifiers","system_name", 'string')
             addColumn("identifiers","discriminator_column", 'integer')

    }

    void down() {
    		dropColumn("identifiers","organization_id")
    		dropColumn("identifiers","system_name")
    		dropColumn("identifiers","discriminator_column")
    }
    
}