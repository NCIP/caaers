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
    <link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
    <%-- <tags:slider renderComments="${command.workflowEnabled}" renderAlerts="false" display="">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments domainObjectType="aeReport"/>
    		</div>
    	</jsp:attribute>
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider> --%>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        Element.observe(window, "load", function() {
            new ListEditor("otherCause", createAE, "OtherCause", {
                addParameters: [aeReportId],
                addFirstAfter: "single-fields",
                deletable: true
            }, 'aeReport.otherCauses')
        })
    </script>
    <style type="text/css">
        textarea {
            width: 30em;
        }
    </style>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section10othercauses">
    <jsp:attribute name="instructions">
     <tags:instructions code="instruction_ae_otherCause" />
    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
        <c:forEach items="${command.aeReport.otherCauses}" varStatus="status">
            <ae:oneOtherCause index="${status.index}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="localButtons">
        <tags:listEditorAddButton divisionClass="otherCause" label="Add a cause" buttonCssClass="ae-list-editor-button"/>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>