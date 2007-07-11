
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Rule Sets</title>
    <tags:stylesheetLink name="extremecomponents"/>
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
		
		function deployRule(name) {
			try {
				authorRule.deployRuleSet(name, function(values) {
							alert("Successfully Deployed");
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


<c:set var="ecImagePath"><c:url value="/images/table/*.gif"/></c:set>
<ec:table
    items="command.ruleSets"
    var="ruleSet" imagePath="${ecImagePath}"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="80%"
    style="" styleClass="">
    <ec:row>
        <ec:column property="name" title="Name" sortable="false" filterable="false">
            <a href="<c:url value="/pages/rule/edit?name=${ruleSet.name}"/>">${ruleSet.name}</a>
        </ec:column>
        <ec:column property="description" title="Description" sortable="false" filterable="false">
        </ec:column>
        <ec:column property="status" title="Status" sortable="false" filterable="false">
            <a id="deploy" href="javascript:deployRule('${ruleSet.name}')">Deploy</a>
        </ec:column>
    </ec:row>
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