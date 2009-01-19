class AddUserIdSequence extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		execute('CREATE SEQUENCE seq_users_id START WITH 100000')
	}
	void down(){
		execute('DROP SEQUENCE seq_users_id')
	}
}