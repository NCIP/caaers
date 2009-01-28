<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

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
    
    <style type="text/css">
        
        div.row div.label { width: 18em;} 
		div.row div.value, div.row div.extra { margin-left: 19em; }
    </style>
    
    <script language="JavaScript">
		Event.observe(window, "load", function() {
			if($("aeReport.responseDescription.studyDrugInterrupted")){
				Event.observe("aeReport.responseDescription.studyDrugInterrupted", "change", function() { viewSelection() })
				viewSelection();
			}
			
		});
		
		function viewSelection(){
			if ($('aeReport.responseDescription.studyDrugInterrupted').options[1].selected){
                $('aeReport.responseDescription.reducedDose').removeAttribute('readOnly')
                $('aeReport.responseDescription.daysNotGiven').removeAttribute('readOnly')
                $('aeReport.responseDescription.reducedDate').removeAttribute('readOnly')
			}else{
				$('aeReport.responseDescription.reducedDose').setAttribute('readOnly',true);
                $('aeReport.responseDescription.daysNotGiven').setAttribute('readOnly',true);
                $('aeReport.responseDescription.reducedDate').setAttribute('readOnly',true);
                
                $('aeReport.responseDescription.reducedDose').value=""
                $('aeReport.responseDescription.daysNotGiven').value=""
                $('aeReport.responseDescription.reducedDate').value=""
                
			}
		}
		
	</script>

    
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section5describeevent">
    <jsp:attribute name="instructions">
    <tags:instructions code="instruction_ae_description" />
    </jsp:attribute>
    <jsp:attribute name="singleFields">
        <c:forEach items="${fieldGroups.desc.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>

       	<c:forEach items="${fieldGroups.DCP_INFO.fields}" var="field">
           	<tags:renderRow field="${field}"/>
       	</c:forEach>
		
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
