package gov.nih.nci.cabig.caaers.dao.query;

public class StudyHavingStudySiteQuery extends AbstractQuery {

    private static String queryString = "select distinct s from Study s";

    private static String ORGANIZATION_NAME = "organizationName";
    
    private static String NCI_INSTITUTE_CODE = "nciInstituteCode";

    private static String STIUDY_SHORT_TITLE = "shortTitle";

    private static String STUDY_IDENTIFIER_VALUE = "identifier";
    
    private static final String DATA_ENTRY_STATUS = "qcStatus";

    public StudyHavingStudySiteQuery() {

        super(queryString);
        //join("s.studyOrganizations ss");
        //andWhere("ss.class = 'SST'");
    }
    public void joinIdentifier() {
        join("s.identifiers as identifier");
    }

    public void joinStudyOrganization() {
        join("s.studyOrganizations as ss");
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
        andWhere("lower(s.shortTitle) LIKE :" + STIUDY_SHORT_TITLE);
        setParameter(STIUDY_SHORT_TITLE, searchString);
    }

    public void filterByShortTitleOrIdentifiers(String text) {
        leftOuterJoin(STUDY_ALIAS + ".identifiers as identifier");
        andWhere("(lower(s.shortTitle) LIKE :TITLE or lower(identifier.value) LIKE :IDENTIFIER)");
        setParameter("TITLE", "%" + text.toLowerCase() + "%");
        setParameter("IDENTIFIER", "%" + text.toLowerCase() + "%");
    }

//  identifier
    public void filterByIdentifierValue(final String Identifiervalue) {
    	joinIdentifier();
        String searchString = "%" + Identifiervalue.toLowerCase() + "%";
        andWhere("lower(identifier.value) LIKE :" + STUDY_IDENTIFIER_VALUE);
        setParameter(STUDY_IDENTIFIER_VALUE, searchString);
    }
    
    
    public void filterBySST() {
        andWhere("ss.class = 'SST'");
    }

    public void filterByIdentifierValueExactMatch(final String Identifiervalue) {
        String searchString = Identifiervalue.toLowerCase();
        andWhere("lower(s.identifiers.value) LIKE :" + STUDY_IDENTIFIER_VALUE);
        setParameter(STUDY_IDENTIFIER_VALUE, searchString);
    }
    
    /**
     * If true, will return only DATA ENTRY completed studies.
     * @param ignoreNonQCedStudy
     */
    public void filterByDataEntryStatus(boolean ignoreNonQCedStudy) {
        if (ignoreNonQCedStudy) {
            andWhere("s.dataEntryStatus = :" + DATA_ENTRY_STATUS);
            setParameter(DATA_ENTRY_STATUS, true);
        }
    }

}