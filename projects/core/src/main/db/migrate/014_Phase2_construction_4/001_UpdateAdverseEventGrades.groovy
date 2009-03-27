class UpdateAEGrades extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
		execute("update adverse_events set grade_code = -1 where grade_code = 1000")
    }

    void down(){
		execute("update adverse_events set grade_code = 1000 where grade_code = -1")
    }
}
