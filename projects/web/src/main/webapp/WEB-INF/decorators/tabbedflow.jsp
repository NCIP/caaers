<%--
    This is the decorator for all caAERS workflow pages.

    Controllers which use this page must include two special entries in
    their referenceData:
        - flow: a gov.nih.nci.cabig.caaers.web.Flow instance describing the flow
        - tab: a gov.nih.nci.cabig.caaers.web.Tab instance for the current page
--%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="blue" tagdir="/WEB-INF/tags/blue"%>	
<%@taglib prefix="standard" tagdir="/WEB-INF/tags/standard"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>caAERS || ${flow.name} || ${tab.longTitle}</title>
    <standard:head/>
    <tags:stylesheetLink name="tabbedflow"/>
    <tags:javascriptLink name="tabbedflow"/>
    <decorator:head/>
</head>
<body>
<div id="all">
<standard:header/>
<div class="tabpane">
    <blue:workflowTabs tab="${tab}" flow="${flow}"/>

    <blue:body title="${flow.name}: ${tab.longTitle}">
        <c:set var="hasSummary" value="${not empty summary}"/>
        <!-- TODO: Summary should be disabled for Overview Pages, need a better logic than this -->
        <c:if test="${hasSummary and tab.viewName != 'study/study_reviewsummary'}">
            <div id="summary-pane" class="pane">
                <blue:box title="Summary">
                    <c:forEach items="${summary}" var="summaryEntry">
                    <div class="row">
                        <div class="label">${summaryEntry.key}</div>
                        <div class="value">${empty summaryEntry.value ? '<em class="none">None</em>' : summaryEntry.value}</div>
                    </div>        
                    </c:forEach>
                </blue:box>
            </div>
        </c:if>
		<!-- AE summary  -->
		<c:if test="${not empty aesummary}">
		<div> 
			<div class="pane">
			  <div class="row">
			    <div class="summarylabel">Subject</div>
			    <div class="summaryvalue">${aesummary['Participant']}</div>
			  </div>
			  <div class="row">
			    <div class="summarylabel">Study</div>
			    <div class="summaryvalue">${aesummary['Study']}</div>
			  </div>
			</div>
		</div>
		</c:if>
		<!-- AE summary  -->
		<c:if test="${not empty routineAeSummary}">
		<div> 
			<div class="pane">
			  <div class="row">
			    <div class="summarylabel">Subject</div>
			    <div class="summaryvalue">${routineAeSummary['Participant']}</div>
			  </div>
			  <div class="row">
			    <div class="summarylabel">Study</div>
			    <div class="summaryvalue">${routineAeSummary['Study']}</div>
			  </div>
			</div>
		</div>
		</c:if>
        <div id="main${hasSummary ? '' : '-no-summary'}-pane" class="pane">
            <decorator:body/>
        </div>
    </blue:body>
</div>
<standard:footer/>
</div>
</body>
</html>
