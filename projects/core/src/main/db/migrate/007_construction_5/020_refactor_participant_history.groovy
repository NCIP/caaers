class ParticipantHistory extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        renameTable('participant_history', 'participant_histories')

        renameColumn('participant_histories', 'height', 'old_height')
        addColumn('participant_histories', 'height', 'numeric')
        execute('UPDATE participant_histories SET height=old_height')
        dropColumn('participant_histories', 'old_height')

        renameColumn('participant_histories', 'weight', 'old_weight')
        addColumn('participant_histories', 'weight', 'numeric')
        execute('UPDATE participant_histories SET weight=old_weight')
        dropColumn('participant_histories', 'old_weight')

        renameColumn('participant_histories', 'height_unit_of_measure', 'height_unit')
        renameColumn('participant_histories', 'weight_unit_of_measure', 'weight_unit')
    }

    void down() {
        renameColumn('participant_histories', 'height_unit', 'height_unit_of_measure')
        renameColumn('participant_histories', 'weight_unit', 'weight_unit_of_measure')

        renameColumn('participant_histories', 'height', 'old_height')
        addColumn('participant_histories', 'height', 'float')
        execute('UPDATE participant_histories SET height=old_height')
        dropColumn('participant_histories', 'old_height')

        renameColumn('participant_histories', 'weight', 'old_weight')
        addColumn('participant_histories', 'weight', 'float')
        execute('UPDATE participant_histories SET weight=old_weight')
        dropColumn('participant_histories', 'old_weight')

        renameTable('participant_histories', 'participant_history')
    }
}