<%@taglib prefix="par" tagdir="/WEB-INF/tags/par"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<chrome:box title="Prior Therapy Details">
<div id="ptDetails">
<form:form id="priorTherapyCommand">

<script type="text/javascript">
var agentAdder;
var agentAdderClass = Class.create();
Object.extend(agentAdderClass.prototype, {
	initialize: function(){
	},
	addAgent: function(){
		var paramHash = new Hash(); //setup parameters
		paramHash.set('task', 'create');
		mHistory.populateDeafultParameters('priorTherapyAgent', paramHash);
		mHistory.insertContent('ptAgentDetails', 'priorTherapyCommand', paramHash);
	}

});

 function initializePriorTherapy(){
	 AE.registerCalendarPopups("ptDetails");
	 agentAdder = new agentAdderClass();

	 Element.observe('addpt-agent-btn', 'click', function(){
		 	this.addAgent();
	 }.bind(agentAdder));
	 
 }
 initializePriorTherapy.defer();
</script>


	<%-- Note: Every page should transmit the index--%>
	<input type="hidden" name="index" value="${index}" />
	<c:set var="fieldGroupName">priorTherapy${index}</c:set>
	<c:set var="fieldGroup" value="${fieldGroups[fieldGroupName]}"/>
	
	<%--<tags:errors path="priorTherapies[${index}]" />--%>
	<tags:errors path="*" />
	<tags:renderRow field="${fieldGroup.fields[0]}" />
	<tags:renderRow field="${fieldGroup.fields[1]}" />
	<tags:renderRow field="${fieldGroup.fields[2]}"/>
	<tags:renderRow field="${fieldGroup.fields[3]}"/>
	<%--<chrome:division id="agents-division" collapsable="true" title="Agents"> --%>
	<table width="80%" class="eXtremeTable">
		<tr><th class="tableHeader"><tags:requiredIndicator />Agents</th></tr>
		<tr>
			<td align="left">
			
				<div id="ptAgentDetails">
					<c:if test="${agentCount gt 0}">
		  				<c:forEach begin="1" end="${agentCount}" var="i">
							<c:set var="agentFieldGroupName">priorTherapyAgent${i-1}</c:set>
							<c:set var="agentFieldGroup" value="${fieldGroups[agentFieldGroupName]}"/>
							<tags:renderRow field="${agentFieldGroup.fields[0]}" />
		  				</c:forEach>
					</c:if>
					<hr />
					<div style="text-align: center;">
						<input id="addpt-agent-btn" type="button" value="Add Agent" />
					</div>
				</div>

			</td>
		</tr>
	</table>
	
	<%-- </chrome:division> --%>

	<hr />	
	<div style="text-align: center;">
		<input id="addpt-btn" type="button" value="Add" onclick="mHistory.savePopup('priorTherapyCommand', 'priorTherapy')" />
	</div>
</form:form>
</div>
</chrome:box>
