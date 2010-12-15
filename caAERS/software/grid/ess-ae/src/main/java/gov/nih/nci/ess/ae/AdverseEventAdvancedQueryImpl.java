package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.AdverseEventQuery;
import ess.caaers.nci.nih.gov.Criteria;
import ess.caaers.nci.nih.gov.LimitOffset;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.web.search.AdvancedSearchCriteriaParameter;
import gov.nih.nci.cabig.caaers.web.search.AdvancedSearchUiUtil;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.ess.ae.service.aeadvancedquery.common.AEAdvancedQueryI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import _21090.org.iso.ST;

/**
 * @author Denis G. Krylov
 * 
 */
public class AdverseEventAdvancedQueryImpl implements MessageSourceAware,
		AEAdvancedQueryI {

	private static final String INVALID_QUERY_CRITERIA = "WS_AEMS_041";
	private static final ISO21090Helper h = null;
	private GridToDomainObjectConverter gridToDomainConverter;
	private DomainToGridObjectConverter domainToGridConverter;
	private MessageSource messageSource;
	private AdverseEventDao adverseEventDao;

	/**
	 * @return the gridToDomainConverter
	 */
	public final GridToDomainObjectConverter getGridToDomainConverter() {
		return gridToDomainConverter;
	}

	/**
	 * @param gridToDomainConverter
	 *            the gridToDomainConverter to set
	 */
	public final void setGridToDomainConverter(
			GridToDomainObjectConverter gridToDomainObjectConverter) {
		this.gridToDomainConverter = gridToDomainObjectConverter;
	}

	/**
	 * @return the messageSource
	 */
	public final MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param messageSource
	 *            the messageSource to set
	 */
	public final void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @return the domainToGridConverter
	 */
	public final DomainToGridObjectConverter getDomainToGridConverter() {
		return domainToGridConverter;
	}

	/**
	 * @param domainToGridConverter
	 *            the domainToGridConverter to set
	 */
	public final void setDomainToGridConverter(
			DomainToGridObjectConverter domainToGridConverter) {
		this.domainToGridConverter = domainToGridConverter;
	}

	/**
	 * @return the adverseEventDao
	 */
	public final AdverseEventDao getAdverseEventDao() {
		return adverseEventDao;
	}

	/**
	 * @param adverseEventDao
	 *            the adverseEventDao to set
	 */
	public final void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.ess.ae.service.aeadvancedquery.common.AEAdvancedQueryI#
	 * queryAdverseEvents(ess.caaers.nci.nih.gov.AdverseEventQuery,
	 * ess.caaers.nci.nih.gov.LimitOffset)
	 */
	public AdverseEvent[] findAdverseEvents(AdverseEventQuery query,
			LimitOffset offset) throws RemoteException,
			AdverseEventServiceException {
		// basic parameters check first
		if (query.getSearchCriteria() == null
				|| query.getSearchCriteria().length == 0) {
			throw new AdverseEventServiceException(INVALID_QUERY_CRITERIA,
					getMessageSource().getMessage(INVALID_QUERY_CRITERIA,
							new Object[] {}, Locale.getDefault()));
		}

		AdvancedSearchUi advancedSearchUi = AdvancedSearchUiUtil
				.loadAdvancedSearchUi();
		SearchTargetObject targetObject = AdvancedSearchUiUtil
				.getSearchTargetObjectByName(advancedSearchUi,
						"gov.nih.nci.cabig.caaers.domain.AdverseEvent");
		List<AdvancedSearchCriteriaParameter> params = new ArrayList<AdvancedSearchCriteriaParameter>();
		for (Criteria criteria : query.getSearchCriteria()) {
			params.add(convert(criteria));
		}
		return null;
	}

	/**
	 * @param criteria
	 * @return
	 */
	private AdvancedSearchCriteriaParameter convert(Criteria criteria) {
		AdvancedSearchCriteriaParameter param = new AdvancedSearchCriteriaParameter();
		param.setAttributeName(getStringValue(criteria.getAttributeName()));
		param.setObjectName(getStringValue(criteria.getObjectName()));
		param.setPredicate(getStringValue(criteria.getPredicate()));
		param.setValue(getStringValue(criteria.getValue()));
		return param;
	}

	/**
	 * @param st
	 * @return
	 */
	private String getStringValue(ST st) {
		return (st != null && st.getValue() != null) ? st.getValue() : "";
	}

}
