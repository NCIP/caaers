<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        Element.observe(window, "load", function() {
            new ListEditor("courseAgent", createAE, "CourseAgent", {
                addParameters: [aeReportId],
                addFirstAfter: "treatmentInfo"
            })
        })
    </script>
    <style type="text/css">
        .label {
            width: 18em;
        }
    </style>
</head>
<body>
    <form:form cssClass="standard">
        <p id="instructions">
            You are entering treatment information for ${command.assignment.participant.fullName} on
            ${command.assignment.studySite.study.shortTitle}.
        </p>
        <tags:hasErrorsMessage/>

        <tags:tabFields tab="${tab}"/>
        <chrome:division id="treatmentInfo">
            <div class="row">
                <div class="label">
                    <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[0]}"/>
                </div>
                <div class="value">
                    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[0]}"/>
                    (course number 1)
                </div>
            </div>
            <div class="row">
                <div class="label">
                    <tags:renderLabel field="${fieldGroups.treatmentInfo.fields[2]}"/>
                </div>
                <div class="value">
                    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[2]}"/>
                    course number
                    <tags:renderInputs field="${fieldGroups.treatmentInfo.fields[1]}" size="4"/>
                </div>
            </div>
        </chrome:division>

        <c:forEach items="${command.aeReport.treatmentInformation.courseAgents}" varStatus="status">
            <ae:oneCourseAgent index="${status.index}"/>
        </c:forEach>
        <input type="button" value="Add a course agent" id="add-courseAgent-button"/>
        <tags:indicator id="add-courseAgent-indicator"/>
    </form:form>
</body>
</html>