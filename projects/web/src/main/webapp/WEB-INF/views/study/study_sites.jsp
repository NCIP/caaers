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
<script language="JavaScript" type="text/JavaScript">

function fireAction(action, selected){
	document.getElementById('command')._target.name='_noname';
	document.studySiteForm._action.value=action;
	document.studySiteForm._selected.value=selected;		
	document.studySiteForm.submit();
}
</script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" formName="studySiteForm">
    <jsp:attribute name="singleFields">
    <div>
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
	</div>
		
		<p id="instructions">
			Add StudySites associated with the Study
			<a href="javascript:fireAction('addSite','0');"><img
					src="/caaers/images/checkyes.gif" border="0" alt="Add"></a><br>
		</p>
		<table class="tablecontent">
			<tr>
				<th scope="col"><span class="red">*</span><em></em>Site:</td>
				<th scope="col"><span class="red">*</span><em></em>Role:</td>										
				<th scope="col">IRB Approval Date:</td>														
				<th scope="col" class="specalt"></td>
			</tr>
	
			<c:forEach varStatus="status" items="${command.studySites}">					
				<tr>
					<td class="alt">
						<form:select path="studySites[${status.index}].site">
							<option value="">--please select --</option>
     						<form:options items="${sitesRefData}" itemLabel="name" itemValue="id" />
						</form:select> </td>									
					<td class="alt">
						<form:select path="studySites[${status.index}].roleCode">
							<option value="">--please select --</option>										
						 	<form:options items="${studySiteRoleCodeRefData}" itemLabel="desc" itemValue="code" />
						</form:select></td>																		   
    				 <td class="alt">
    				    	<tags:dateInput path="studySites[${status.index}].irbApprovalDate"/></td>   
    				<td class="alt">
						<a href="javascript:fireAction('removeSite',${status.index});"><img
						src="/caaers/images/checkno.gif" border="0" alt="delete"></a></td>
				</tr>
			</c:forEach>
																			
		</table>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
