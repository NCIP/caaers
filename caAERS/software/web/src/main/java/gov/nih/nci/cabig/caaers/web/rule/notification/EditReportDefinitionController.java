package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This controller is used during the NotificatonDefinition edit flow.
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : Jun 12, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Transactional
public class EditReportDefinitionController extends AbstractReportDefinitionController {

    private ExpeditedReportTree expeditedReportTree;

    @Override
    public String getFlowName() {
        return "Edit Report Definition";
    }

    @Override
    protected boolean shouldSave(HttpServletRequest request, ReportDefinitionCommand command, Tab<ReportDefinitionCommand> tab) {
        return !isAjaxAddRequest(request);
    }

    /**
     * The request parameter should contain <code>repDefId</code>, which is used to obtain the
     * {@link ReportDefinition} from the DB.
     */
    @Override
    public Object formBackingObject(HttpServletRequest req) throws Exception {
        req.getSession().removeAttribute(getReplacedCommandSessionAttributeName(req));
        req.getSession().removeAttribute(ReportDefinitionAjaxFacade.CREATE_FLOW_COMMAND_KEY);

        // fetch report definition Id
        Integer rpDefId = Integer.valueOf(req.getParameter("repDefId"));
        // feth the ReportDefinition by Id
        ReportDefinition rpDef = reportDefinitionDao.getById(rpDefId);
        // initialize all the lazy collections in rpDef
        reportDefinitionDao.initialize(rpDef);
        reconcileMandatoryFields(rpDef.getMandatoryFields(), expeditedReportTree);
        ReportDefinitionCommand rpDefCmd = new ReportDefinitionCommand(rpDef, reportDefinitionDao, getConfigurationProperty(), configPropertyRepository);

        super.populateOptions(rpDefCmd);

        // find the index of the first planned notificaiton
        List<PlannedNotification> pnfList = rpDef.getPlannedNotifications();
        PlannedNotification pnf = (pnfList.size() > 0) ? pnfList.get(0) : null;
        if (pnf != null) rpDefCmd.setPointOnScale("" + pnf.getIndexOnTimeScale());
        // TODO: uncomment this
        // rpDefCmd.populate();
        return rpDefCmd;

    }

    /**
     * Does the following:- Remove orphaned fields from existing mandatory field list. Add newly
     * added fields to existing mandatory field list.
     */
    public void reconcileMandatoryFields(List<ReportMandatoryFieldDefinition> mfList, TreeNode node) {
        // map consisting values from existing mandatory fields
        HashMap<String, ReportMandatoryFieldDefinition> existingFieldMap = new HashMap<String, ReportMandatoryFieldDefinition>();
        for (ReportMandatoryFieldDefinition mf : mfList) {
            existingFieldMap.put(mf.getFieldPath(), mf);
        }
        // newly calculated list
        List<ReportMandatoryFieldDefinition> mfListNew = new ArrayList<ReportMandatoryFieldDefinition>();

        super.populateMandatoryFields(mfListNew, node);
        for (ReportMandatoryFieldDefinition mf : mfListNew) {
            if (existingFieldMap.remove(mf.getFieldPath()) == null) {
                mfList.add(mf); // add this, this is a new field (may be a new release, not existed
                // while saving report definition)
            }
        }
        for (ReportMandatoryFieldDefinition mf : existingFieldMap.values()) {
            // these fields got removed (in new release)
            mfList.remove(mf);
        }
    }

    @Required
    public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree) {
        this.expeditedReportTree = expeditedReportTree;
    }

}
