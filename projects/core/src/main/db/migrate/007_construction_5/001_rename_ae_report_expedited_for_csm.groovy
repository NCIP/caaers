class RenameAeReportExpeditedForCsm extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("UPDATE csm_protection_group "
            + "SET protection_group_name='gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport' "
            + "WHERE protection_group_id=-3")
        execute("UPDATE csm_protection_element "
            + "SET protection_element_name='gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport', "
            + "    object_id='gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport'"
            + "WHERE protection_element_id=-4")
        execute("UPDATE csm_role "
            + "SET role_name='gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport.CREATE' "
            + "WHERE role_id=-7")
        execute("UPDATE csm_role "
            + "SET role_name='gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport.UPDATE' "
            + "WHERE role_id=-8")
        execute("UPDATE csm_role "
            + "SET role_name='gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport.READ' "
            + "WHERE role_id=-9")
    }

    void down() {
        execute("UPDATE csm_protection_group "
            + "SET protection_group_name='gov.nih.nci.cabig.caaers.domain.AdverseEventReport' "
            + "WHERE protection_group_id=-3")
        execute("UPDATE csm_protection_element "
            + "SET protection_element_name='gov.nih.nci.cabig.caaers.domain.AdverseEventReport', "
            + "    object_id='gov.nih.nci.cabig.caaers.domain.AdverseEventReport'"
            + "WHERE protection_element_id=-4")
        execute("UPDATE csm_role "
            + "SET role_name='gov.nih.nci.cabig.caaers.domain.AdverseEventReport.CREATE' "
            + "WHERE role_id=-7")
        execute("UPDATE csm_role "
            + "SET role_name='gov.nih.nci.cabig.caaers.domain.AdverseEventReport.UPDATE' "
            + "WHERE role_id=-8")
        execute("UPDATE csm_role "
            + "SET role_name='gov.nih.nci.cabig.caaers.domain.AdverseEventReport.READ' "
            + "WHERE role_id=-9")
    }
}