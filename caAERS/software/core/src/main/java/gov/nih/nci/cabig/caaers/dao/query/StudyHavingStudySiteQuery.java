package gov.nih.nci.cabig.caaers.dao.query;

public class StudyHavingStudySiteQuery extends AbstractQuery {

    private static String queryString = "select distinct ss.study from StudySite ss";

    private static String ORGANIZATION_NAME = "organizationName";
    
    private static String NCI_INSTITUTE_CODE = "nciInstituteCode";

    private static String STIUDY_SHORT_TITLE = "shortTitle";

    private static String STUDY_IDENTIFIER_VALUE = "identifier";
    
    private static final String DATA_ENTRY_STATUS = "qcStatus";

    public StudyHavingStudySiteQuery() {

        super(queryString);
    }

    public void filterByStudySiteName(final String organizationName) {
        String searchString = "%" + organizationName.toLowerCase() + "%";
        andWhere("lower(ss.organization.name) LIKE :" + ORGANIZATION_NAME);
        setParameter(ORGANIZATION_NAME, searchString);
    }
    public void filterByStudySiteNciInstituteCode(final String nciInstituteCode) {
        String searchString = "%" + nciInstituteCode.toLowerCase() + "%";
        andWhere("lower(ss.organization.nciInstituteCode) LIKE :" + NCI_INSTITUTE_CODE);
        setParameter(NCI_INSTITUTE_CODE, searchString);
    }
    public void filterByStudyShortTile(final String shortTitle) {
        String searchString = "%" + shortTitle.toLowerCase() + "%";
        andWhere("lower(ss.study.shortTitle) LIKE :" + STIUDY_SHORT_TITLE);
        setParameter(STIUDY_SHORT_TITLE, searchString);
    }

    public void filterByIdentifierValue(final String Identifiervalue) {
        String searchString = "%" + Identifiervalue.toLowerCase() + "%";
        andWhere("lower(ss.study.identifiers.value) LIKE :" + STUDY_IDENTIFIER_VALUE);
        setParameter(STUDY_IDENTIFIER_VALUE, searchString);
    }

    public void filterByIdentifierValueExactMatch(final String Identifiervalue) {
        String searchString = Identifiervalue.toLowerCase();
        andWhere("lower(ss.study.identifiers.value) LIKE :" + STUDY_IDENTIFIER_VALUE);
        setParameter(STUDY_IDENTIFIER_VALUE, searchString);
    }
    
    /**
     * If true, will return only DATA ENTRY completed studies.
     * @param ignoreNonQCedStudy
     */
    public void filterByDataEntryStatus(boolean ignoreNonQCedStudy) {
        if (ignoreNonQCedStudy) {
            andWhere("ss.study.dataEntryStatus = :" + DATA_ENTRY_STATUS);
            setParameter(DATA_ENTRY_STATUS, true);
        }
    }

}