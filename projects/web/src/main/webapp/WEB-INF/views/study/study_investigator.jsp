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

function validatePage(){
	return true;
}

function fireAction(action, selected){
	if(validatePage()){
		document.getElementsByName('_target4')[0].name='_target3';
		document.studySiteForm._action.value=action;
		document.studySiteForm._selected.value=selected;		
		document.studySiteForm.submit();
	}
}

function clearField(field){
field.value="";
}

function chooseSites(){
		//alert("inside choose sites");
		document.getElementsByName('_target4')[0].name='_target3';
		document.studySiteForm._action.value="siteChange";
		document.studySiteForm._selected.value=document.getElementById('site').value;		
		document.studySiteForm.submit();
}

function chooseSitesfromSummary(selected){
		//alert("inside chooseSitesfromSummary");
		//alert(selected);
		document.getElementsByName('_target4')[0].name='_target3';
		document.studySiteForm._action.value="siteChange";
		document.studySiteForm._selected.value=selected;		
		document.studySiteForm.submit();
}

function fireAction1(action, selected, studysiteindex){

	if(validatePage()){
		document.getElementsByName('_target4')[0].name='_target3';
		document.studySiteForm._action.value=action;
		document.studySiteForm._selected.value=selected;
		document.studySiteForm._studysiteindex.value=studysiteindex;
		document.studySiteForm.submit();
	}
}

	var investigatorAutocompleterProps = {
            basename: "investigator",
            populator: function(autocompleter, text) {
				 var site_index = document.getElementById('site').value;
				 createStudy.matchSiteInvestigator(text, site_index, function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {				
                return obj.investigator.firstName
            }
        }

		function acPostSelect(mode, selectedChoice) {			
            //Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
            $(mode.basename).value = selectedChoice.id;
            //$(mode.basename + '-selected').show()
            //new Effect.Highlight(mode.basename + "-selected")
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

		Event.observe(window, "load", function() {
	        index = 0;
	        autoCompleteId = 'investigator' + index ;
	        while( $(autoCompleteId)  )
	        { 
		     // change the basename property to agent0 ,agent1 ...
	         investigatorAutocompleterProps.basename=autoCompleteId
             acCreate(investigatorAutocompleterProps)
             index++
             autoCompleteId= 'investigator' + index  ; 
            }           
            Element.update("flow-next", "Continue &raquo;")
        }) 
				
</script>
</head>
<body>
				<form:form method="post" name="studySiteForm">
				<table border="0" id="table1" cellspacing="10" width="100%">
					<tr>
					<td valign="top" width="25%">
						<chrome:division id="Summary" title="Summary">
						<font size="2"><b> Study Sites </b> </font>
						<br><br>
						<table border="0" id="table1" cellspacing="0" cellpadding="0" width="100%">
						<c:forEach var="studySite" varStatus="status" items="${command.studySites}">					
							<tr>
								<td> 
									<a href="#" onclick="javascript:chooseSitesfromSummary(${status.index});" title="click here to edit investigator assigned to study"> <font size="2"> <b>  ${studySite.site.name}  </b> </font> </a>
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
						</chrome:division>
					</td>
					<td width="75%" valign="top">
					<chrome:division id="study-details">
					<tags:tabFields tab="${tab}"/>
					<div>		
						<input type="hidden" name="_action" value="">
						<input type="hidden" name="_selected" value="">
						<input type="hidden" name="_studysiteindex" value="">				
					</div>
					<p id="instructions">
						Please choose a study site and link investigators to that study site
					</p>
											
						<c:set var="index1" value="0"/>
						<c:if test="${!empty site_id}">
							<c:set var="index1" value="${site_id}"/>
						</c:if>

						<table border="0" id="table1" cellspacing="10" width="30%">
		
						   <tr>
								<td align="right"> <b> <span class="red">*</span><em></em>Site:</b> </td>
								<td align="left">  	
									<select id="site" name="site" onchange="javascript:chooseSites();">
										<c:forEach var="studySite" varStatus="status" items="${command.studySites}"> 
											
											<c:if test="${index1 == status.index }">				
												<option selected="true" value=${status.index}>${studySite.site.name}</option>
											</c:if>
											<c:if test="${index1 != status.index }">				
												<option value=${status.index}>${studySite.site.name}</option>
											</c:if>
										</c:forEach>
									</select>										                   		 
								</td>
						   </tr> 
						</table>

						<c:set var="index" value="0"/>
						<c:if test="${!empty site_id}">
							<c:set var="index" value="${site_id}"/>
						</c:if>

						<table border="0" id="table_inv" cellspacing="0" width="100%">
							<tr>
								<td align="left"> <b> <span class="red">*</span><em></em>Investigator:</b> </td>								
								<td align="left"> <b> <span class="red">*</span><em></em>Role:</b> </td>
								<td align="left"> <b> <span class="red">*</span><em></em>Status:</b> </td>
								<td align="left">										
									<b><a href="javascript:fireAction1('addInv','0', ${index});"><img
										src="/caaers/images/checkyes.gif" border="0" alt="Add"></a></b> 
								</td>
							</tr>							
							<c:forEach varStatus="status" items="${command.studySites[index].studyInvestigators}">							
								<tr id="bex-${status.index}">												
									<td align="left" width="50%">
										<form:hidden id="investigator${status.index}"			path="studySites[${index}].studyInvestigators[${status.index}].siteInvestigator"/>
										<input id="investigator${status.index}-input" name="studySites[${index}].studyInvestigators[${status.index}].siteInvestigator.investigator.firstName"	type="text" value="${command.studySites[index].studyInvestigators[status.index].siteInvestigator.investigator.firstName}" size="80"/>                   				
                    					<input type="button" id="investigator${status.index}-clear" value="Clear"/>
                    					<tags:indicator id="investigator${status.index}-indicator"/> 
										<div id="investigator${status.index}-choices" class="autocomplete" style="display:none"></div>		
									</td>
									
									<td align="left" width="20%"> 								
										<form:select path="studySites[${index}].studyInvestigators[${status.index}].roleCode">				
									 	<form:options items="${invRoleCodeRefData}" itemLabel="desc"
												itemValue="code" />  
										</form:select>																						
									</td>

									<td align="left" width="10%"> 
										<form:select path="studySites[${index}].studyInvestigators[${status.index}].statusCode">			
									 	<form:options items="${invStatusCodeRefData}" itemLabel="desc"
												itemValue="code" />  
										</form:select>																						
									</td>																
									<td align="left" width="20%">
										<a href="javascript:fireAction1('removeInv',${status.index}, ${index});"><img
												src="/caaers/images/checkno.gif" border="0" alt="delete"></a>										
									</td>
								</tr>								
							</c:forEach>																									
						</table>						
					  </chrome:division>
					  </td>
					  </tr>
					</table>
				</form:form>		
</body>
</html>
