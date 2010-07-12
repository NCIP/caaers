class ModifyIndex extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
      addColumn("study_index","role_code", "integer", defaultValue: 0)
      addColumn("researchstaff_index","role_code", "integer", defaultValue: 0)
      addColumn("reportingperiod_index","role_code", "integer", defaultValue: 0)
      addColumn("participant_index","role_code", "integer", defaultValue: 0)
      addColumn("organization_index","role_code", "integer", defaultValue: 0)
      addColumn("investigator_index","role_code", "integer", defaultValue: 0)
      addColumn("expedited_ae_index","role_code", "integer", defaultValue: 0)
      addColumn("adverseevent_index","role_code", "integer", defaultValue: 0)
    }

    void down() {
        dropColumn("study_index", "role_code")
        dropColumn("researchstaff_index", "role_code")
        dropColumn("reportingperiod_index", "role_code")
        dropColumn("participant_index", "role_code")
        dropColumn("organization_index", "role_code")
        dropColumn("investigator_index", "role_code")
        dropColumn("expedited_ae_index", "role_code")
        dropColumn("adverseevent_index", "role_code")
    }
}
