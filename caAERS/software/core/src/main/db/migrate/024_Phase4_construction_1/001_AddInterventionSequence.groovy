class InterventionSequence extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
        execute("create sequence seq_ae_behavioral_interv_id");
    }

    void down() {
        //not possible.

    }
}
