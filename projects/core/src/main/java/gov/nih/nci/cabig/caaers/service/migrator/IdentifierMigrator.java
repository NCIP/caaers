package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

public class IdentifierMigrator<E extends AbstractIdentifiableDomainObject>
implements Migrator<E> {

	private OrganizationDao organizationDao;

	/**
	 * Will migrate the identifiers from source to destination
	 */

	public void migrate(E src, E dest,
			DomainObjectImportOutcome<E> outcome) {

		int protocolAuthorityIdentifierCount = 0;
		int coordinatingCenterIdentifierCount = 0;

		// Identifiers
		for (Identifier identifier : src.getIdentifiers()) {
			if (identifier instanceof OrganizationAssignedIdentifier) {
				OrganizationAssignedIdentifier orgIdentifier = (OrganizationAssignedIdentifier) identifier;
				Organization organization = null;

				if (orgIdentifier.getOrganization().getNciInstituteCode() != null
						&& orgIdentifier.getOrganization()
						.getNciInstituteCode().length() > 0) {

					String nciInstituteCode = orgIdentifier.getOrganization()
					.getNciInstituteCode();

					organization = fetchOrganization(nciInstituteCode);

				} else {
					String orgName = orgIdentifier.getOrganization().getName();
					organization = organizationDao.getByName(orgName);
				}

				outcome.ifNullObject(organization, Severity.ERROR,
						"The organization specified in identifier is invalid");
				orgIdentifier.setOrganization(organization);
				//orgIdentifier.setPrimaryIndicator(Boolean.FALSE);

				if (orgIdentifier.getType().equals(
				"Protocol Authority Identifier")) {
					protocolAuthorityIdentifierCount++;
				}
				if (orgIdentifier.getType().equals(
				"Coordinating Center Identifier")) {
					coordinatingCenterIdentifierCount++;
				}
			}
			dest.getIdentifiers().add(identifier);
		}

		outcome.ifNullOrEmptyList(dest.getIdentifiers(), Severity.ERROR,
		"Identifiers are either Empty or Not Valid");

		if (protocolAuthorityIdentifierCount >= 1) {
			StringBuilder sb = new StringBuilder();
			sb
			.append("Study cannot contain more than one \"Protocol Authority Identifier\" ");
			sb
			.append("(OrganizationAssignedIdentifier provided in FundingSponsor is considered \"Protocol Authority Identifier\")");
			outcome.addErrorMessage(sb.toString(), Severity.ERROR);
		}
		if (coordinatingCenterIdentifierCount >= 1) {
			StringBuilder sb = new StringBuilder();
			sb
			.append("Study cannot contain more than one \"Coordinating Center Identifier\" ");
			sb
			.append("(OrganizationAssignedIdentifier provided in CoordinatingCenter is considered \"Coordinating Center Identifier\")");

			outcome.addErrorMessage("", Severity.ERROR);
		}
	}

	/**
	 * Fetches the organization from the DB
	 * 
	 * @param nciCode
	 * @return
	 */
	private Organization fetchOrganization(String nciInstituteCode) {

		OrganizationQuery orgQuery = new OrganizationQuery();
		if (StringUtils.isNotEmpty(nciInstituteCode)) {
			orgQuery.filterByNciCodeExactMatch(nciInstituteCode);
		}
		List<Organization> orgList = organizationDao
		.searchOrganization(orgQuery);
		if (orgList == null || orgList.isEmpty()) {
			return null;
		}
		if (orgList.size() > 1) {
			//("Multiple organizations exist with same NCI Institute Code :" + nciInstituteCode);
		}
		return orgList.get(0);
	}

	///BEAN PROPERTIES

	@Required
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

}