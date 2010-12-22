package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.AdverseEventQuery;
import ess.caaers.nci.nih.gov.AuditTrail;
import ess.caaers.nci.nih.gov.Criteria;
import ess.caaers.nci.nih.gov.Id;
import ess.caaers.nci.nih.gov.LimitOffset;
import ess.caaers.nci.nih.gov.TsDateTime;
import gov.nih.nci.cabig.caaers.dao.AdvancedSearchDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.web.search.AdvancedSearchCriteriaParameter;
import gov.nih.nci.cabig.caaers.web.search.AdvancedSearchUiUtil;
import gov.nih.nci.cabig.caaers.web.search.CommandToSQL;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.Operator;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.UiAttribute;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryDao;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;
import gov.nih.nci.cabig.ctms.audit.dao.query.DataAuditEventQuery;
import gov.nih.nci.cabig.ctms.audit.domain.AuditHistory;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditEvent;
import gov.nih.nci.ess.ae.service.aeadvancedquery.common.AEAdvancedQueryI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import _21090.org.iso.ST;

/**
 * @author Denis G. Krylov
 * 
 */
public class AdverseEventAdvancedQueryImpl implements MessageSourceAware,
		AEAdvancedQueryI {

	private static final Log log = LogFactory
			.getLog(AdverseEventAdvancedQueryImpl.class);
	private static final ISO21090Helper h = null;
	private static final String INCOMPLETE_CRITERIA = "WS_AEMS_045";
	private static final String INVALID_ATTR_NAME = "WS_AEMS_043";
	private static final String INVALID_OBJ_NAME = "WS_AEMS_042";
	private static final String INVALID_PREDICATE = "WS_AEMS_044";
	private static final String INVALID_QUERY_CRITERIA = "WS_AEMS_041";
	public static final String INVALID_AE_ID = AdverseEventManagementImpl.INVALID_AE_ID;
	private AdverseEventDao adverseEventDao;
	private AdvancedSearchDao advancedSearchDao;
	private DomainToGridObjectConverter domainToGridConverter;
	private GridToDomainObjectConverter gridToDomainConverter;
	private MessageSource messageSource;
	private AuditHistoryRepository auditHistoryRepository;

	/**
	 * @param criteria
	 * @param tgtObj
	 * @return
	 */
	private AdvancedSearchCriteriaParameter convert(Criteria criteria,
			SearchTargetObject tgtObj) {
		AdvancedSearchCriteriaParameter param = new AdvancedSearchCriteriaParameter();
		final String attrName = getStringValue(criteria.getAttributeName());
		final String objName = getStringValue(criteria.getObjectName());
		final String predicate = getStringValue(criteria.getPredicate());
		final String value = getStringValue(criteria.getValue());

		// check whether the object name is right
		DependentObject depObj = AdvancedSearchUiUtil.getDependentObjectByName(
				tgtObj, objName);
		if (depObj == null) {
			raiseError(INVALID_OBJ_NAME);
		}

		// check the attribute name
		UiAttribute uiAttr = AdvancedSearchUiUtil.getUiAttributeByName(depObj,
				attrName);
		if (uiAttr == null) {
			raiseError(INVALID_ATTR_NAME);
		}

		// is the predicate supported for this attribute?
		Operator op = AdvancedSearchUiUtil.getOperator(uiAttr, predicate);
		if (op == null) {
			raiseError(INVALID_PREDICATE);
		}

		param.setAttributeName(attrName);
		param.setObjectName(objName);
		param.setPredicate(predicate);
		param.setValue(value);
		param.setDataType(uiAttr.getDataType());
		param.setDependentObjectName(depObj.getClassName());
		param.setFilterByMethodInQueryClass(uiAttr.getFilterMethod());
		param.setDeleted(false);

		if (!param.isFilled()) {
			raiseError(INCOMPLETE_CRITERIA);
		}
		return param;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.ess.ae.service.aeadvancedquery.common.AEAdvancedQueryI#
	 * queryAdverseEvents(ess.caaers.nci.nih.gov.AdverseEventQuery,
	 * ess.caaers.nci.nih.gov.LimitOffset)
	 */
	public AdverseEvent[] findAdverseEvents(AdverseEventQuery query,
			LimitOffset limitAndOffset) throws RemoteException,
			AdverseEventServiceException {
		// basic parameters check first
		if (query.getSearchCriteria() == null
				|| query.getSearchCriteria().length == 0) {
			raiseError(INVALID_QUERY_CRITERIA);
		}

		AdvancedSearchUi advancedSearchUi = AdvancedSearchUiUtil
				.loadAdvancedSearchUi();
		SearchTargetObject targetObject = AdvancedSearchUiUtil
				.getSearchTargetObjectByName(advancedSearchUi,
						"gov.nih.nci.cabig.caaers.domain.AdverseEvent");
		List<AdvancedSearchCriteriaParameter> params = new ArrayList<AdvancedSearchCriteriaParameter>();
		for (Criteria criteria : query.getSearchCriteria()) {
			params.add(convert(criteria, targetObject));
		}
		if (params.isEmpty()) {
			raiseError(INCOMPLETE_CRITERIA);
		}
		Integer firstResult = limitAndOffset != null
				&& limitAndOffset.getOffset() != null ? limitAndOffset
				.getOffset().getValue() : null;
		Integer maxResults = limitAndOffset != null
				&& limitAndOffset.getLimit() != null ? limitAndOffset
				.getLimit().getValue() : null;
		try {
			AbstractQuery hqlQuery = CommandToSQL.transform(targetObject,
					params);
			log.info(hqlQuery.getQueryString());
			List<gov.nih.nci.cabig.caaers.domain.AdverseEvent> list = (List<gov.nih.nci.cabig.caaers.domain.AdverseEvent>) advancedSearchDao
					.search(hqlQuery, firstResult, maxResults);
			List<AdverseEvent> gridList = new ArrayList();
			for (gov.nih.nci.cabig.caaers.domain.AdverseEvent ae : list) {
				gridList.add(domainToGridConverter.convertAdverseEvent(ae));
			}
			return gridList.toArray(new AdverseEvent[0]);
		} catch (Exception e) {
			log.error(e, e);
			throw new AdverseEventServiceException(e);
		}
	}

	/**
	 * @return the adverseEventDao
	 */
	public final AdverseEventDao getAdverseEventDao() {
		return adverseEventDao;
	}

	/**
	 * @return the domainToGridConverter
	 */
	public final DomainToGridObjectConverter getDomainToGridConverter() {
		return domainToGridConverter;
	}

	/**
	 * @return the gridToDomainConverter
	 */
	public final GridToDomainObjectConverter getGridToDomainConverter() {
		return gridToDomainConverter;
	}

	/**
	 * @return the messageSource
	 */
	public final MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param st
	 * @return
	 */
	private String getStringValue(ST st) {
		return (st != null && st.getValue() != null) ? st.getValue() : "";
	}

	private void raiseError(String code) {
		throw new AdverseEventServiceException(code, getMessageSource()
				.getMessage(code, new Object[] {}, Locale.getDefault()));
	}

	/**
	 * @param adverseEventDao
	 *            the adverseEventDao to set
	 */
	public final void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
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
	 * @param gridToDomainConverter
	 *            the gridToDomainConverter to set
	 */
	public final void setGridToDomainConverter(
			GridToDomainObjectConverter gridToDomainObjectConverter) {
		this.gridToDomainConverter = gridToDomainObjectConverter;
	}

	/**
	 * @param messageSource
	 *            the messageSource to set
	 */
	public final void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @return the advancedSearchDao
	 */
	public final AdvancedSearchDao getAdvancedSearchDao() {
		return advancedSearchDao;
	}

	/**
	 * @param advancedSearchDao
	 *            the advancedSearchDao to set
	 */
	public final void setAdvancedSearchDao(AdvancedSearchDao advancedSearchDao) {
		this.advancedSearchDao = advancedSearchDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.ess.ae.service.aeadvancedquery.common.AEAdvancedQueryI#
	 * getAuditTrailOfAdverseEvent(ess.caaers.nci.nih.gov.Id,
	 * ess.caaers.nci.nih.gov.TsDateTime)
	 */
	public AuditTrail[] getAuditTrailOfAdverseEvent(Id aeId, TsDateTime minDate)
			throws RemoteException,
			gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
		Date startDate = gridToDomainConverter.convertToDate(minDate);
		if (aeId == null || !NumberUtils.isNumber(aeId.getExtension())) {
			raiseError(INVALID_AE_ID);
		}
		if (startDate == null) {
			startDate = new Date();
		}
		int id = NumberUtils.toInt(aeId.getExtension());
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);

		List<AuditHistory> list = auditHistoryRepository.getAuditDetailsForEntity(
				gov.nih.nci.cabig.caaers.domain.AdverseEvent.class, id, cal);

		return null;
	}

	/**
	 * @return the auditHistoryRepository
	 */
	public final AuditHistoryRepository getAuditHistoryRepository() {
		return auditHistoryRepository;
	}

	/**
	 * @param auditHistoryRepository
	 *            the auditHistoryRepository to set
	 */
	public final void setAuditHistoryRepository(
			AuditHistoryRepository auditHistoryRepository) {
		this.auditHistoryRepository = auditHistoryRepository;
	}

}
