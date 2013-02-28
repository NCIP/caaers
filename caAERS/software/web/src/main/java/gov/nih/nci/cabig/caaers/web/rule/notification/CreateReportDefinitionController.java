/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Sujith Vellat Thayyilthodi
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
public class CreateReportDefinitionController extends AbstractReportDefinitionController {
    private ExpeditedReportTree expeditedReportTree;

    @Override
    public String getFlowName() {
        return "Create Report Definition";
    }

    @Override
    protected boolean shouldSave(HttpServletRequest request, ReportDefinitionCommand command, Tab<ReportDefinitionCommand> tab) {
        return false;
    }

    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        // supress validation when target page is less than current page.
        int curPage = getCurrentPage(request);
        int targetPage = getTargetPage(request, curPage);
        if (targetPage < curPage) return true;
        return super.suppressValidation(request, command);
    }

    /**
     * In the create flow of report definition, we should make sure that there exists at least one
     * ReportDeliveryDefinition.
     */
    @Override
    public Object formBackingObject(HttpServletRequest request) {
        ReportDefinition reportDefinition = new ReportDefinition();
        reportDefinition.setAmendable(true);
        List<ReportMandatoryFieldDefinition> mandatoryFields = new ArrayList<ReportMandatoryFieldDefinition>();
        populateMandatoryFields(mandatoryFields, expeditedReportTree);
        reportDefinition.setMandatoryFields(mandatoryFields);
        ReportDefinitionCommand command = new ReportDefinitionCommand(reportDefinition, reportDefinitionDao, configPropertyRepository);
        command.refreshParentOptions(null);
        command.refreshGroupOptions();
        populateFieldRuleSet(command);
        command.setRuleManager(SecurityUtils.checkAuthorization(UserGroupType.ae_rule_and_report_manager));
        return command;
    }



    @Override
    public FlowFactory<ReportDefinitionCommand> getFlowFactory() {
        return new FlowFactory<ReportDefinitionCommand>(){
            public Flow<ReportDefinitionCommand> createFlow(ReportDefinitionCommand command) {
                Flow<ReportDefinitionCommand> flow = new Flow<ReportDefinitionCommand>(getFlowName());
                BasicsTab basicsTab = new BasicsTab();
                ReportDeliveryDefinitionTab deliveryDefTab = new ReportDeliveryDefinitionTab();
                ReportMandatoryFieldDefinitionTab mandatoryFieldTab = new ReportMandatoryFieldDefinitionTab();
                NotificationsTab notificationsTab = new NotificationsTab();
                ReviewTab reviewTab = new ReviewTab();

                flow.addTab(basicsTab);
                flow.addTab(deliveryDefTab);
                flow.addTab(mandatoryFieldTab);
                flow.addTab(notificationsTab);
                flow.addTab(reviewTab);

                return flow;
            }
        };
    }

    @Required
    public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree) {
        this.expeditedReportTree = expeditedReportTree;
    }
}
