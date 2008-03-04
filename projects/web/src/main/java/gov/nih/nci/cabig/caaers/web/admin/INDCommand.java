package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.INDHolder;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.InvestigatorHeldIND;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationHeldIND;

import org.apache.commons.lang.StringUtils;

public class INDCommand {

    private String strINDNumber;

    private String holderType;

    private String strSponsorId;

    private OrganizationDao organizationDao;

    private InvestigatorDao investigatorDao;

    public void reset() {
        strINDNumber = "";
        holderType = "";
        strSponsorId = "";
    }

    public void populate(InvestigationalNewDrug ind) {
        strINDNumber = "" + ind.getIndNumber();
        INDHolder holder = ind.getINDHolder();
        if (holder == null) return;
        if (holder instanceof OrganizationHeldIND) {
            OrganizationHeldIND oIND = (OrganizationHeldIND) holder;
            holderType = "org";
            strSponsorId = oIND.getOrganization().getId().toString();
        } else if (holder instanceof InvestigatorHeldIND) {
            InvestigatorHeldIND iInd = (InvestigatorHeldIND) holder;
            holderType = "inv";
            strSponsorId = iInd.getInvestigator().getId().toString();
        }
    }

    public InvestigationalNewDrug createInvestigationalNewDrug() {
        InvestigationalNewDrug ind = new InvestigationalNewDrug();
        ind.setIndNumber(Integer.parseInt(strINDNumber));
        int sponsorId = Integer.parseInt(strSponsorId);
        if (StringUtils.equals("org", holderType)) {
            Organization org = organizationDao.getById(sponsorId);
            OrganizationHeldIND holder = new OrganizationHeldIND();
            holder.setOrganization(org);
            holder.setInvestigationalNewDrug(ind);
            ind.setINDHolder(holder);
        } else if (StringUtils.equals("inv", holderType)) {
            Investigator inv = investigatorDao.getById(sponsorId);
            InvestigatorHeldIND holder = new InvestigatorHeldIND();
            holder.setInvestigator(inv);
            holder.setInvestigationalNewDrug(ind);
            ind.setINDHolder(holder);
        }
        return ind;
    }

    /**
     * @return the strINDNumber
     */
    public String getStrINDNumber() {
        return strINDNumber;
    }

    /**
     * @param strINDNumber
     *                the strINDNumber to set
     */
    public void setStrINDNumber(String strINDNumber) {
        this.strINDNumber = strINDNumber;
    }

    /**
     * @return the holderType
     */
    public String getHolderType() {
        return holderType;
    }

    /**
     * @param holderType
     *                the holderType to set
     */
    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    /**
     * @return the strSponsorId
     */
    public String getStrSponsorId() {
        return strSponsorId;
    }

    /**
     * @param strSponsorId
     *                the strSponsorId to set
     */
    public void setStrSponsorId(String strSponsorId) {
        this.strSponsorId = strSponsorId;
    }

    public void setInvestigatorDao(InvestigatorDao investigatorDao) {
        this.investigatorDao = investigatorDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
}
