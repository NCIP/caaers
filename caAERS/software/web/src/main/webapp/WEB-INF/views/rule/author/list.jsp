
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>List Rules</title>
    <tags:dwrJavascriptLink objects="authorRule"/>
    <style type="text/css">
        .notify-unit.success {
            color: #090;
        }

        .notify-unit.failure {
            color: #900;
        }
    </style>
    <script type="text/javascript">
    
    Event.observe(window, "load", function() {
    


		});
		
		function deployRule(name , divId) {
			try {
				authorRule.deployRuleSet(name, function(values) {
							alert("Successfully Enabled");
							document.getElementById(divId).innerHTML = "<font color='green'>Enabled</font>";
					});
			} catch(e) {alert(e)}
			
		}
		function deleteRule(name , divId) {
			try {
				authorRule.deleteRuleSet(name, function(values) {
							alert("Successfully Deleted");
							document.getElementById(divId).innerHTML = "<font color='green'>Enabled</font>";
					});
			} catch(e) {alert(e)}
			
		}
		
		function unDeployRule(name , divId) {
			try {
				authorRule.unDeployRuleSet(name, function(values) {
							alert("Successfully Disabled");
							document.getElementById(divId).innerHTML = "<font color='red'>Not Enabled</font>";
					});
			} catch(e) {alert(e)}
		}
		
		function exportRule(name) {
			try {
				authorRule.exportRuleSet(name, function(values) {
							alert("Successfully Exported");
					});
			} catch(e) {alert(e)}
		}
				
		function fireRulesNow(mode) {
			try {
				authorRule.fireRules("gov.nih.nci.cabig.caaers.rule.study", mode, function(values) {
							alert("rule fired ");
					});
			} catch(e) {alert(e)}
		}
		
    </script>
</head>
<body>
<p>
<tags:instructions code="listrules" />
</p>
<c:set var="ecImagePath"><c:url value="/images/table/*.gif"/></c:set>
<ec:table
    items="command.ruleSets"
    var="ruleSet" imagePath="${ecImagePath}"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="80%"
    style="margin-left:30px; width:90%;" styleClass="">
    <c:if test="${ruleSet.name != 'default' }">
    <ec:row>
    
    	<ec:column property="level" title="Rule Level" sortable="false" filterable="false">
    		${ruleSet.level}
    	</ec:column>
    	
    	<ec:column property="description" title="Rule Set" sortable="false" filterable="false"/>
    	
    	<ec:column property="organization" title="Organization" sortable="false" filterable="false">
    	    		${ruleSet.organization}
    	</ec:column>
    	
    	<ec:column property="study" title="Study" sortable="false" filterable="false">
    	    		${ruleSet.study}
    	</ec:column>    	
    	
       <ec:column property="status" title="Status" sortable="false" filterable="false">
		  <div id="status-${ruleSet.id}">
		   <c:choose>
            <c:when test="${ruleSet.coverage == 'Not Enabled'}">
            	<font color="red">${ruleSet.coverage}</font>
            </c:when>
            <c:otherwise>
				<font color="green">${ruleSet.coverage}</font>
			</c:otherwise>
		   </c:choose>
		  </div>
		  
        </ec:column>
        <ec:column property="action" title="Action" sortable="false" filterable="false">
            	<a id="deploy" href="javascript:deployRule('${ruleSet.name}' , 'status-${ruleSet.id}')">Enable</a>&nbsp;&nbsp;
            	<a id="deploy" href="javascript:unDeployRule('${ruleSet.name}' , 'status-${ruleSet.id}')">Disable</a>&nbsp;&nbsp;
            	
            	
            	<!-- <a id="export" href="javascript:exportRule('${ruleSet.name}')">Export</a>-->
            	<a href="<c:url value="/pages/rule/export?ruleSetName=${ruleSet.name}"/>">Export/Download</a>&nbsp;&nbsp;
            	<a href="<c:url value="/pages/rule/util?ruleSetName=${ruleSet.name}"/>"><font color="red">Delete</font></a>&nbsp;&nbsp;
            	
        </ec:column>
    </ec:row>
    </c:if>
</ec:table>


<p>


<%--
<a href="javascript:fireRulesNow('3')">Successful Execution</a>

<br/>

<a href="javascript:fireRulesNow('2')">Non - Successful Execution</a>
</p>

<a href="javascript:fireRulesNow('1')">Successful Execution 2</a>
--%>


</body>
</html>