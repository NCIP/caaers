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
    <jsp:attribute name="singleFields">
        <c:choose>
            <c:when test="${command.aeReport.expeditedReportingRequired}">
                The AEs you have entered require
                ${ctms:countString(command.aeReport.requiredReportCount, 'expedited report')} to
                be submitted.
            </c:when>
            <c:otherwise>
                <p>
                    The AEs you have entered <strong>do not</strong> seem to
                    require any expedited reporting.  If you wish override this decision,
                    please select the reports below.
                </p>
            </c:otherwise>
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
            Every report you select has a minimum set of fields which the target agency needs
            you to provide.  Once you pass this page, all those fields will be tagged with
            <ae:requiredToSubmit/>.  The tabs along the top of the flow will be similarly
            tagged if they are missing fields that are required for submission.
            (Note: this is not yet implemented.)
        </p>

    </jsp:attribute>
</tags:tabForm>

<c:if test="${not command.aeReport.expeditedReportingRequired}">
<form:form>
    <chrome:box title="Save as routine">
        <chrome:division>
            <p class="instructions">
                Alternatively, if you would like to save the adverse event information you entered
                <strong>without submitting</strong> any expedited reports, you may save it as a
                routine report.
            </p>
        </chrome:division>

        <div class="content buttons autoclear">
            <div class="flow-buttons">
                <span class="next">
                    Not implemented <input type="submit" value="Save as routine report &raquo;" disabled="disabled"/>
                </span>
            </div>
        </div>
    </chrome:box>
</form:form>
</c:if>

</body>
</html>