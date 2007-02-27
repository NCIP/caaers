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
<script language="JavaScript" type="text/JavaScript">

function validatePage(){
	return true;
}
function fireAction(action, selected){
	if(validatePage()){
		document.getElementsByName('_target3')[0].name='_target2';
		document.studySiteForm._action.value=action;
		document.studySiteForm._selected.value=selected;		
		document.studySiteForm.submit();
	}
}
function clearField(field){
field.value="";
}

</script>
</head>
<body>
<!-- MAIN BODY STARTS HERE -->
<chrome:body title="${flow.name}: ${tab.longTitle}">	
				<form:form method="post" name="studySiteForm">
				<chrome:division id="study-details">
				<tags:tabFields tab="${tab}"/>
				<div>		
					<input type="hidden" name="_action" value="">
					<input type="hidden" name="_selected" value="">
				</div>
				
					<table border="0" id="table1" cellspacing="10" width="100%">
	
						<tr>
							<td align="center"> <b> <span class="red">*</span><em></em>Site:</b> </td>
							<td align="center"> <b> <span class="red">*</span><em></em>Status Code:</b> </td>
							<td align="center"> <b> <span class="red">*</span><em></em>Role Code:</b> </td>
							<td align="center"> <b> <span class="red">*</span><em></em>Start Date:</b> </td>
							<td align="center"> <b> End Date:</b> </td>														
							<td align="center"> <b> <span class="red">*</span><em></em>IRB Approval Date:</b> </td>														
							<td align="center">										
								<b><a href="javascript:fireAction('addSite','0');"><img
									src="/caaers/images/checkyes.gif" border="0" alt="Add"></a></b> 
							</td>
						</tr>
				
						<c:forEach varStatus="status" items="${command.studySites}">					
							<tr>
								<td align="center" width="15%"> 
									<form:select path="studySites[${status.index}].site">
        						  	 	<form:options items="${sitesRefData}" itemLabel="name" itemValue="id" />
									</form:select> 
								</td>
								
								<td align="center" width="15%">
								    <form:select path="studySites[${status.index}].statusCode">										
									 	<form:options items="${studySiteStatusRefData}" itemLabel="desc"
												itemValue="code" />
									</form:select>
								</td>	
									
								<td align="center" width="15%">
									<form:select path="studySites[${status.index}].roleCode">										
									 	<form:options items="${studySiteRoleCodeRefData}" itemLabel="desc"
												itemValue="code" />
									</form:select>									
								</td>
												
							    <td align="center" width="15%">
							    	<tags:dateInput path="studySites[${status.index}].startDate"/>							    
							    </td>				
							    
							    <td align="center" width="15%">
							        <tags:dateInput path="studySites[${status.index}].endDate"/>
							    </td>

    	    				    <td align="center" width="15%">
    	    				    	<tags:dateInput path="studySites[${status.index}].irbApprovalDate"/>
    	    				    </td>   
    	    				    
    	    				    <td align="center" width="10%">
									<a href="javascript:fireAction('removeSite',${status.index});"><img
											src="/caaers/images/checkno.gif" border="0" alt="delete"></a>										
								</td>
							</tr>
						</c:forEach>
																						
					</table>
				</chrome:division>
				</form:form>		
<!-- MAIN BODY ENDS HERE -->
</chrome:body>
</body>
</html>
