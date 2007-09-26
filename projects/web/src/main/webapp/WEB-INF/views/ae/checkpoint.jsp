<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="ctms" uri="http://gforge.nci.nih.gov/projects/ctmscommons/taglibs/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <style type="text/css">
    </style>
    <script type="text/javascript">
    </script>
</head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}">
	<jsp:attribute name="instructions">
	 <tags:instructions code="instruction_ae_checkpoint" />
	</jsp:attribute>
	<jsp:attribute name="instructions">
	 <tags:instructions code="instruction_ae_checkpoint" />
	</jsp:attribute>
    <jsp:attribute name="singleFields">
        <c:choose>
            <c:when test="${command.aeReport.expeditedReportingRequired}">
               <br /> <strong>Reports Identified by caAERS</strong><br />
               <tags:instructions code="instruction_ae_checkpointReports" heading=" "/>
            </c:when>
        </c:choose>
        <div class="report-list">
            <!-- required reports -->
            <c:forEach items="${command.aeReport.reports}" var="report">
                <c:if test="${report.required}">
                    <div class="row">
                        <div class="label">
                            <tags:requiredIndicator/>&nbsp;<input type="checkbox" checked="checked" disabled="disabled"/>
                        </div>
                        <div class="value">
                            ${report.reportDefinition.name} (${report.reportDefinition.organization.name})
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <br>
            <c:choose>
            <c:when test="${command.aeReport.expeditedReportingRequired}">
                <c:if test="${not empty command.optionalReportDefinitionsMap}">
                    If you wish to submit optional Reports, please select them below.
                </c:if>
            </c:when>
       		</c:choose>
            
            <!-- optional reports -->
            <c:forEach items="${fieldGroups['optionalReports'].fields}" var="field">
                <div class="row">
                    <div class="label">
                        <tags:renderInputs field="${field}"/>
                    </div>
                    <div class="value">
                        <tags:renderLabel field="${field}"/>
                    </div>
                </div>
            </c:forEach>
        </div>
        <p>
        	If you agree with this assessment and wish to proceed, click Save & Continue. 
        	Once you click this button, the report will be initiated and the countdown to the due date will begin.
        	<br />
            Every report you select has a minimum set of fields which the target agency needs
            you to provide.  Once you pass this page, all those fields will be tagged with
            <tags:requiredIndicator />.  The tabs along the top of the flow will be similarly
            tagged with <tags:requiredIndicator /> if they are mandatory and <ae:requiredToSubmit />, 
            when there are unfilled fields that are required for submission.
        </p>

    </jsp:attribute>
</tags:tabForm>
</body>
</html>