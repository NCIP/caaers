class UpdateIndHolders extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		execute('UPDATE ind_holders SET INV_ID=INV_ID+30000')
	}
	void down(){
		execute('UPDATE ind_holders SET INV_ID=INV_ID-30000')
	}
}