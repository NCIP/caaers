class AlterIndHolders extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		execute('ALTER TABLE ind_holders   ADD CONSTRAINT fk_indhldrs_inv_id FOREIGN KEY (inv_id)   REFERENCES investigators (id)')
	}
	void down(){
		//should be done by the original script
	}
}