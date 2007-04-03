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
            new ListEditor("lab", createAE, "Lab", {
                addParameters: [aeReportId],
                addFirstAfter: "instructions"
            })
        })
    </script>
</head>
<body>
    <form:form cssClass="standard">
        <p id="instructions">
            You are entering labs for ${command.assignment.participant.fullName} on
            ${command.assignment.studySite.study.shortTitle}.
        </p>
        <tags:hasErrorsMessage/>

        <tags:tabFields tab="${tab}"/>
        <c:forEach items="${command.aeReport.labs}" varStatus="status">
            <ae:oneLab index="${status.index}"/>
        </c:forEach>
        <input type="button" value="Add a lab" id="add-lab-button"/>
        <tags:indicator id="add-lab-indicator"/>
    </form:form>
</body>
</html>