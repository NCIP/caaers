/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class implements the Data access related operations for the {@link AdditionalInformationDocument } domain object.
 *
 * @author Saurabh Agrawal
 * @since 10/25/2012
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AdditionalInformationDocumentDao extends CaaersDao<AdditionalInformationDocument> {


    /**
     * Get the Class representation of the domain object that this DAO is representing.
     *
     * @return Class representation of the domain object that this DAO is representing.
     */
    public Class<AdditionalInformationDocument> domainClass() {
        return AdditionalInformationDocument.class;
    }

    public List<AdditionalInformationDocument> findByAdditionalInformationId(Integer additionalInformationId) {
        HQLQuery query = new HQLQuery("select o from AdditionalInformationDocument o where o.additionalInformation.id = :additionalInformationId");
        query.setParameter("additionalInformationId", additionalInformationId);
        return (List<AdditionalInformationDocument>) search(query);
    }

    public AdditionalInformationDocument findByFileId(String fileId) {
        HQLQuery query = new HQLQuery("select o from AdditionalInformationDocument o where o.fileId = :fileId");
        query.setParameter("fileId", fileId);
        return (AdditionalInformationDocument) CollectionUtils.firstElement(search(query));
    }


    /**
     * Delete a additionalInformationDocument
     *
     * @param additionalInformationDocument The AdditionalInformationDocument to be deleted.
     */
    @Transactional(readOnly = false)
    public void delete(AdditionalInformationDocument additionalInformationDocument) {
        getHibernateTemplate().delete(additionalInformationDocument);
    }

}
