class AddAdverseEventSignature extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		addColumn("adverse_events","signature", "string")
	}
	
	void down() {
		dropColumn("adverse_events", "signature");
		
	}
}