class AlterIndHolders extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		execute('ALTER TABLE ind_holders DROP CONSTRAINT fk_indhldrs_inv_id')
	}
	void down(){
		execute('ALTER TABLE ind_holders   ADD CONSTRAINT fk_indhldrs_inv_id FOREIGN KEY (inv_id)   REFERENCES investigators (id)')
	}
}