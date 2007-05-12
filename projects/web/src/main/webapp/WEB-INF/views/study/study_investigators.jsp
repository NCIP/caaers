<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<style type="text/css">
        .label { width: 12em; text-align: right; padding: 4px; }
</style>

<tags:includeScriptaculous/>
<tags:dwrJavascriptLink objects="createStudy"/>

<script language="JavaScript" type="text/JavaScript">
function fireAction(action, selectedSite, selectedInvestigator){
	document.getElementById('command')._target.name='_noname';
	document.form._action.value=action;
	document.form._selectedSite.value=selectedSite;
	document.form._selectedInvestigator.value=selectedInvestigator;
	// need to disable validations while submitting
//	role = 'studySites['+selectedSite+'].studyInvestigators['+selectedInvestigator+'].roleCode';
//	$(role).className='none';
//	status = 'studySites['+selectedSite+'].studyInvestigators['+selectedInvestigator+'].statusCode';
//	$(status).className='none';
	document.form.submit();
	fireListeners(selected);
}

function chooseSites(){
	document.getElementById('command')._target.name='_noname';	
	document.form._action.value="siteChange";
	document.form._selectedSite.value=document.getElementById('site').value;
	document.form.submit();
}

function chooseSitesFromSummary(selected){
	document.getElementById('command')._target.name='_noname';
	document.form._action.value="siteChange";
	document.form._selectedSite.value=selected;
	document.form.submit();
}

/// AJAX
var investigatorAutocompleterProps = {
	basename: "investigator",
    populator: function(autocompleter, text) {
    createStudy.matchSiteInvestigator(text, document.getElementById('site').value, function(values) {
	    autocompleter.setChoices(values)
	})
    },
    valueSelector: function(obj) {
	return obj.investigator.fullName
    }
}

function acPostSelect(mode, selectedChoice) {
	$(mode.basename).value = selectedChoice.id;
}

function updateSelectedDisplay(mode) {

    if ($(mode.basename).value) {
	Element.update(mode.basename + "-selected-name", $(mode.basename + "-input").value)
	$(mode.basename + '-selected').show()
    }
}

function acCreate(mode) {
    new Autocompleter.DWR(mode.basename + "-input", mode.basename + "-choices",
	mode.populator, {
	valueSelector: mode.valueSelector,
	afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
	    acPostSelect(mode, selectedChoice)
	},
	indicator: mode.basename + "-indicator"
    })
    Event.observe(mode.basename + "-clear", "click", function() {
	//$(mode.basename + "-selected").hide()
	$(mode.basename).value = ""
	$(mode.basename + "-input").value = ""
    })
}

function fireListeners(count)
{
	index = 0;
	autoCompleteId = 'investigator' + index ;
	for (i=0;i<count;i++)
	{
	 // change the basename property to agent0 ,agent1 ...
	 investigatorAutocompleterProps.basename=autoCompleteId
	 acCreate(investigatorAutocompleterProps)
	 index++
	 autoCompleteId= 'investigator' + index  ;
	}

	investigatorAutocompleterProps.basename='investigator' + count ;
	acCreate(investigatorAutocompleterProps);
}

Event.observe(window, "load", function() {
	index = 0;
	autoCompleteId = 'investigator' + index ;
	while( $(autoCompleteId)  )
	{
	 // change the basename property to investigator0 ,investigator1 ...
	 investigatorAutocompleterProps.basename=autoCompleteId
	 acCreate(investigatorAutocompleterProps)
	 index++
	 autoCompleteId= 'investigator' + index  ;
	}
})

</script>
</head>
<body>

<table border="0" id="table1" cellspacing="10" width="100%">
	<tr>
		<td width="75%" valign="top">
	    <tags:tabForm tab="${tab}" flow="${flow}" formName="form">
        <jsp:attribute name="singleFields">
	   	<div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selectedSite" value="">
			<input type="hidden" name="_selectedInvestigator" value="">
		</div>
		<p id="instructions">
			Please choose a study site and link investigators to that study site
		</p>							
		<c:set var="selected_site" value="0"/>
		<c:if test="${not empty selectedSite}">
			<c:set var="selected_site" value="${selectedSite}"/>
		</c:if>

		<table border="0" id="table1" cellspacing="10" width="30%">
		   <tr>
				<td align="right"> <b> <span class="red">*</span><em></em>Site:</b> </td>
				<td align="left">  	
					<select id="site" name="site" onchange="javascript:chooseSites();">
						<c:forEach  items="${command.studySites}" var="studySite" varStatus="status">
							<c:if test="${selected_site == status.index }">
								<option selected="true" value=${status.index}>${studySite.site.name}</option>
							</c:if>
							<c:if test="${selected_site != status.index }">
								<option value=${status.index}>${studySite.site.name}</option>
							</c:if>
						</c:forEach>
					</select>									                   		 
				</td>
		   </tr> 
		</table>
		<br>
		<hr>
		<p id="instructions">
			Add investigators <a href="javascript:fireAction('addInv',${selected_site}, '0');"><img
				src="<c:url value="/images/checkyes.gif"/>" border="0" alt="Add"></a>
		</p>
		<table id="tablecontent">
		<tr>
			<th scope="col" align="left"><b> <span class="red">*</span><em></em>Investigator:</b></th>
			<th scope="col" align="left"><b> <span class="red">*</span><em></em>Role:</b> </th>
			<th scope="col" align="left"><b> <span class="red">*</span><em></em>Status:</b> </th>
			<th scope="col" class="specalt" align="left"><b></b></th>
		</tr>
	
		<c:forEach varStatus="status" items="${command.studySites[selected_site].studyInvestigators}">
		<tr>
			<td class="alt">
				<form:hidden id="investigator${status.index}" path="studySites[${selected_site}].studyInvestigators[${status.index}].siteInvestigator"/>
				<input class="validate-notEmpty" type="text" id="investigator${status.index}-input" size="30" value="${command.studySites[selected_site].studyInvestigators[status.index].siteInvestigator.investigator.fullName}"/>
				<input type="button" id="investigator${status.index}-clear" value="Clear"/>
				<tags:indicator id="investigator${status.index}-indicator"/>
				<div id="investigator${status.index}-choices" class="autocomplete"></div></td>
			<td class="alt">
				<form:select path="studySites[${selected_site}].studyInvestigators[${status.index}].roleCode" cssClass="validate-notEmpty">
					<option value="">--Please Select--</option>
					<form:options items="${invRoleCodeRefData}" itemLabel="desc" itemValue="desc"/>
				</form:select></td>
			<td class="alt">
				<form:select path="studySites[${selected_site}].studyInvestigators[${status.index}].statusCode" cssClass="validate-notEmpty">
					<option value="">--Please Select--</option>
					<form:options items="${invStatusCodeRefData}" itemLabel="desc" itemValue="desc" />
				</form:select></td>
			<td class="alt">
				<a href="javascript:fireAction('removeInv', ${selected_site}, ${status.index});"><img
					src="<c:url value="/images/checkno.gif"/>" border="0" alt="delete"></a></td>
		</tr>
		</c:forEach>
		</table>					
	    </jsp:attribute>
	    </tags:tabForm>
	  </td>

      <td valign="top" width="25%">
		<chrome:box id="Summary" title="Summary">
		<font size="2"><b> Study Sites </b> </font>
		<br><br>
		<table border="0" id="table1" cellspacing="0" cellpadding="0" width="100%">
		<c:forEach var="studySite" varStatus="status" items="${command.studySites}">					
			<tr>
				<td> 
					<a href="#" onclick="javascript:chooseSitesfromSummary(${status.index});" 
					title="click here to edit investigator assigned to study"> <font size="2"> <b>  ${studySite.site.name} </b> </font> </a>
				</td>																									     
			</tr>
			<tr>
				<td> 
					Investigators Assigned: <b> ${fn:length(studySite.studyInvestigators)} </b>
				</td>																									            
			</tr>
			<tr>
				<td> 
					<br>	
				</td>																									            
			</tr>
		</c:forEach>
		<c:forEach begin="1" end="15">					

		<tr>
			<td> 
				<br>	
			</td>																						
		</tr>	
		</c:forEach>
		</table>				
		</chrome:box>
	</td>
	  </tr>
	</table>
</body>
</html>
