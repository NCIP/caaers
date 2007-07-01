<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="rd" tagdir="/WEB-INF/tags/report" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="reportDef"/>
    
    <title>Report Delivery Configuration</title>
    <script>
    	Event.observe(window, "load", function() {

			//This is added for Add Recipient button
            new ListEditor("rdd-section", reportDef, "ReportDeliveryDefinition", {
            	addParameters: [1],
            	addFirstAfter: "single-fields",
                addCallback: function(nextIndex) {
                	
                }
            });
            //This is added for add role recipient buttion
             new ListEditor("rdd-section", reportDef, "ReportDeliveryDefinition", {
                addButton: "add-rdd-section-role-button",
                addIndicator: "add-rdd-section-role-indicator",
            	addParameters: [2],
            	addFirstAfter: "single-fields",
                addCallback: function(nextIndex) {
                	
                }
            });
        });
    </script>
</head>
<body>
    <chrome:division>
	    <tags:tabForm tab="${tab}" flow="${flow}" willSave="false">
	     <jsp:attribute name="instructions">
        	You are entering final report delivery information for <b>${command.name}</b>.
   		 </jsp:attribute>
			<jsp:attribute name="singleFields">
				<div id="rdd-tab-fields">
            	   <input type="hidden" name="lastPointOnScale" value=""/>
               	   <input type="hidden" name="notificationType" value="EMAIL_NOTIFICATION" />
            	</div>
			</jsp:attribute>
			<jsp:attribute name="repeatingFields">
            	<c:forEach items="${command.reportDefinition.deliveryDefinitions}" var="rdd" varStatus="status">
             		<rd:oneReportDeliveryDefinition index="${status.index}" rdd="${rdd}"/>
            	</c:forEach>
        	</jsp:attribute>
        	<jsp:attribute name="localButtons">
            	<chrome:division title="">
            	<tags:listEditorAddButton divisionClass="rdd-section" label="Add Recipient (System/Person)"/>   
            	<tags:listEditorAddButton divisionClass="rdd-section-role" label="Add Recipient (Person-Role)"/> 
            	</chrome:division>                                                                                                                                                                                                                                                             
	        </jsp:attribute>
		</tags:tabForm> 
	</chrome:division>
</body>
</html>