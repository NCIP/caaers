class CreateStudyParticipantAssignments extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

  addColumn('participant_assignments','study_subject_identifier' , 'string' , nullable:true);


    }

    void down() {
    dropColumn('participant_assignments', 'study_subject_identifier');

    }

}