<%--
    This is the decorator for all caAERS workflow pages.

    Controllers which use this page must include two special entries in
    their referenceData:
        - flow: a gov.nih.nci.cabig.caaers.web.Flow instance describing the flow
        - tab: a gov.nih.nci.cabig.caaers.web.Tab instance for the current page
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>caAERS || ${flow.name} || ${tab.longTitle}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <standard:head/>
    <decorator:head/>
    <tags:js name="tabbedflow"/>
</head>
<body>
<standard:ajaxLoadingIndicator />
<tags:js name="wz_tooltip/wz_tooltip"/>
<div class="wide-header"></div>
<!-- ALL DIV start -->

<div id="all">
<standard:header/>
<div class="tabpane">

    <chrome:workflowTabs tab="${tab}" flow="${flow}"/>

    <chrome:body title="${flow.name}: ${tab.longTitle}">
        
		      
        <c:set var="hasSummary" value="${not empty summary}"/>
        <!-- TODO: Summary should be disabled for Overview Pages, need a better logic than this -->
        <c:if test="${hasSummary and tab.viewName != 'study/study_reviewsummary'}">
            <div id="summary-pane" class="pane summary">
                <chrome:box title="Summary">
                    <c:forEach items="${summary}" var="summaryEntry">
                    <div class="row">
                        <div class="label">${summaryEntry.key}</div>
                        <div class="value">${empty summaryEntry.value ? '<em class="none">None</em>' : summaryEntry.value}</div>
                    </div>        
                    </c:forEach>
                </chrome:box>
            </div>
        </c:if>

        <!-- AE summary  -->
		<c:if test="${not empty aesummary}">
			<div class="pane summary" id="reporter-summary">
			  <div class="row">
			    <div class="summarylabel" style="width:7em;"><caaers:message code="LBL_Subject" /></div>
			    <div class="summaryvalue" style="margin-left:8em;">${aesummary['Participant']}</div>
			  </div>
			  <div class="row">
			    <div class="summarylabel" style="width:7em;"><caaers:message code="LBL_Study" /></div>
			    <div class="summaryvalue" style="margin-left:8em;">${aesummary['Study']}</div>
			  </div>
			  <div class="row">
			    <div class="summarylabel" style="width:7em;"><caaers:message code="LBL_CourseCycle" /></div>
			    <div class="summaryvalue" style="margin-left:8em;">${aesummary['Course']}</div>
			  </div>
			  <c:if test="${aesummary['Report Name'] != null}">
			  	<div class="row">
				  	<div class="summarylabel" style="width:7em;"><caaers:message code="LBL_Report" /></div>
				  	<div class="summaryvalue" style="margin-left:8em;">${aesummary['Report Name'] }</div>
				</div>
			  </c:if>
			  <c:if test="${showReportContextMenu}">
                  <div class="row">
                      <div class="summarylabel">
                          Report(s)
                      </div>
                      <div class="summaryvalue">
                          <div>
                              <tags:noform>
                                  <ae:reportingContext checkBoxMode="true" allReportDefinitions="${command.applicableReportDefinitions}" selectedReportDefinitions="${command.selectedReportDefinitions}" />
                              </tags:noform>
                          </div>
                      </div>
                  </div>
              </c:if>
            </div>
		</c:if>
        
        <!-- AE summary  -->
		<c:if test="${not empty routineAeSummary}">
			<div class="pane summary" id="ae-summary">
			  <div class="row">
			    <div class="summarylabel" style="width:7em;"><caaers:message code="LBL_Subject" /></div>
			    <div class="summaryvalue" style="margin-left:8em;">${routineAeSummary['Participant']}</div>
			  </div>
			  <div class="row">
			    <div class="summarylabel" style="width:7em;"><caaers:message code="LBL_Study" /></div>
			    <div class="summaryvalue" style="margin-left:8em;">${routineAeSummary['Study']}</div>
			  </div>
			  <div class="row">
			    <div class="summarylabel" style="width:7em;"><caaers:message code="LBL_CourseCycle" /></div>
			    <div class="summaryvalue" style="margin-left:8em;">${routineAeSummary['Course']}</div>
			  </div>
			</div>
		</c:if>
        <div id="main${hasSummary ? '' : '-no-summary'}-pane" class="pane">
            <decorator:body/>
        </div>
    </chrome:body>

</div>
<standard:footer/>
</div>

<!-- ALL DIV end -->
<standard:logoutWarning />
</body>
</html>
<!-- END decorators\tabbedflow.jsp -->