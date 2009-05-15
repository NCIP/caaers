<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ruleTags" tagdir="/WEB-INF/tags/rule"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="authorRule"/>
    <title>Select Rule Set</title>

    <script type="text/javascript">
    
    	function setRuleSetName(newRuleSetElement)
    	{
    		if (newRuleSetElement.options[newRuleSetElement.selectedIndex].value =='Other')
    		{
    			Effect.Appear("otherRuleSetDiv");
    		}
    		else
    		{
	    		$("hiddenRuleSetName").value=newRuleSetElement.options[newRuleSetElement.selectedIndex].value;    		
	    		Effect.Fade("otherRuleSetDiv");
	    	}
	    	
    	}
    	
	    function disableCreateRuleSet(radioRuleSetElement)
	    {
	    	Effect.Fade("newRuleSetDiv");
	    	Effect.Fade("otherRuleSetDiv");
	    	
	    	$("hiddenRuleSetName").value=radioRuleSetElement.value;
	    }
    	
    	function setOtherRuleSetName(otherRuleSetName)
    	{
    		$("hiddenRuleSetName").value=otherRuleSetName.value;
    	}
    	
	</script>

</head>
<body>

<p>
<tags:instructions code="ruleset" />
</p>

<chrome:division>

    <tags:tabForm tab="${tab}" flow="${flow}" willSave="false" >
		<jsp:attribute name="singleFields">

		<div class="local-buttons">
            <tags:button id="createRuleSet" color="blue" type="button" value="Create Rule Set" size="small" icon="add"/>
        </div>
            <tags:errors path="*"/>
            
			<c:choose>
				<%-- Check whether RuleSets exist or not --%>
				<c:when test="${empty command.existingRuleSets}" >
	            	<div class="row">
    	            	Rule Sets do not exist. Please create a new Rule Set.
        	    	</div>
				</c:when>
				<%-- Display all the existing rulesets --%>
				<c:otherwise>
					<input type="radio" id="radioRuleSetName1" name="radioRuleSetName" style="visibility:hidden"/>
					<c:forEach items="${command.existingRuleSets}" var="ruleSet">
				        <div class="row">
    	        		    <label><input type="radio" id="radioRuleSetName" name="radioRuleSetName" value="${ruleSet.description}" onClick="disableCreateRuleSet(this)"/> &nbsp; <b>${ruleSet.description} </b></label>
			            </div>
    				</c:forEach>
        	    </c:otherwise>
            </c:choose>

        <div class="row"  id="newRuleSetDiv" style="display:none">
            <div class="label"><label for="newRuleSetName">Rule Set Name</label></div>
            <div class="value">
                <select id="newRuleSetName" onChange="setRuleSetName(this)">
                    <option value="Please select a RuleSet Name">Please select a Rule Set Name</option>
                    <!--
                    
                    <c:if test="${command.level == 'Sponsor' || command.level == 'SponsorDefinedStudy'}">
                    	<option value="AE Assesment Rules">AE Assesment Rules </option>
                    </c:if>
                    -->
                    
                    <option value="SAE Reporting Rules">SAE Reporting Rules </option>
                    <option value="Mandatory Sections Rules">Mandatory Sections Rules</option>
                    <!-- <option value="Other">Other </option>-->
                </select>
            </div>
        </div>
        <div class="row" id="otherRuleSetDiv" style="display:none">
            <div class="label"><label for="otherRuleSetName">Other Name</label></div>
            <div class="value">
		<input type="text" id="otherRuleSetName" name="otherRuleSetName" onKeyUp="setOtherRuleSetName(this)"/>
            </div>
        </div>
	
	<form:hidden id="hiddenRuleSetName" path="ruleSetName"/>
	</jsp:attribute>
		
	</tags:tabForm> 
</chrome:division>

    <script type="text/javascript">
    	Event.observe("createRuleSet", "click", 
    		function(e)
    		{
		    	Effect.Appear("newRuleSetDiv");
		    	
		    	if (document.getElementById("radioRuleSetName1") != null)
		    	{
		    	 	document.getElementById("radioRuleSetName1").checked=true;
		    	}
    		}
    	
    	)
    </script>
</body>
</html>
