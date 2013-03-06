/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import org.apache.commons.lang.StringUtils;

public class StudySitesQuery extends AbstractQuery {

    public static final String SHORT_TITLE = "shortTitle";
    private static final String IDENTIFIER_EXACT_VALUE = "identifierValue";
    private static String RETIRED_INDICATOR = "retiredIndicator";
    
    public StudySitesQuery() {
        super("SELECT distinct ss FROM StudySite ss");
        andWhere("ss.retiredIndicator = :" + RETIRED_INDICATOR);
        setParameter(RETIRED_INDICATOR, new Boolean(false));
        joinStudies();
        leftJoin("study.identifiers as identifier");
    }

    public void joinStudies() {
        join("ss.study AS study");
    }


    public void joinOrganizations() {
        join("ss.organization AS o");
    }

    public void filterByOrganizationId(Integer orgId) {
        joinOrganizations();
        andWhere("o.id = :orgId");
        setParameter("orgId", orgId);
    }

    public void filterByDataEntryComplete(Boolean dataEntryCmplete) {
        andWhere("ss.study.dataEntryStatus = :dataEntryComplete");
        setParameter("dataEntryComplete", dataEntryCmplete);
    }

    public void filterStudiesWithMatchingShortTitleOnly(String text) {
        if (!StringUtils.isBlank(text)) {
            String searchString = text != null ? "%" + text.toLowerCase() + "%" : null;
            andWhere(String.format("(lower(ss.study.shortTitle) LIKE :%s )" , SHORT_TITLE));
            setParameter(SHORT_TITLE, searchString);
        }
    }

    public void filterStudiesWithMatchingIdentifierOnly(String text) {
        if (!StringUtils.isBlank(text)) {
            String searchString = text != null ? "%" + text.toLowerCase() + "%" : null;
            andWhere(String.format("(lower(identifier.value) LIKE :%s)", IDENTIFIER_EXACT_VALUE));
            setParameter(IDENTIFIER_EXACT_VALUE, searchString);
        }
    }

    public void filterByShortTitleOrIdentifiers(String text) {
        andWhere("(lower(study.shortTitle) LIKE :TITLE or lower(identifier.value) LIKE :IDENTIFIER)");
        setParameter("TITLE", "%" + text.toLowerCase() + "%");
        setParameter("IDENTIFIER", "%" + text.toLowerCase() + "%");
    }

}
