class TreatmentAssignmentStudyInterventionSeq extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

        execute('create sequence seq_ta_si_id')
        execute('create sequence seq_si_aes_id')
    }

    void down() {

        execute('drop sequence seq_ta_si_id')
        execute('drop sequence seq_si_aes_id')

    }
}
