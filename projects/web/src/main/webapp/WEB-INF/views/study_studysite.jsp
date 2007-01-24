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
<script type="text/javascript" src="/caaers/js/CalendarPopup.js"></script>
<script language="JavaScript" id="js1">
								var cal1 = new CalendarPopup();
							</script>
</head>
<body>
<!-- MAIN BODY STARTS HERE -->
<chrome:body title="${flow.name}: ${tab.longTitle}">	
				<form:form method="post">
				<chrome:division id="study-details">
				<tags:tabFields tab="${tab}"/>
				
					<table width="28%" border="0" cellspacing="3" cellpadding="3"
						id="table1">
					
					<tr>
				      <td class="label"> <form:label path="studySites[0].site"><span class="red">*</span><em></em>Site:</form:label> </td>
				      <td> <form:select path="studySites[0].site">
							<form:options items="${sitesRefData}" itemLabel="name"
									itemValue="id" />
						</form:select> </td>        			  
					</tr>
					
					<tr>
				      <td class="label"> <form:label path="studySites[0].statusCode"><span class="red">*</span><em></em>Status
							Code:</form:label> </td>
				      <td> <form:input path="studySites[0].statusCode"/> </td>        			  
					</tr>
						
					<tr>
				      <td class="label"> <form:label path="studySites[0].roleCode"><span class="red">*</span><em></em>Role
							Code:</form:label> </td>
				      <td> <form:input path="studySites[0].roleCode"/> </td>        			  
					</tr>

					<tr>
				      <td class="label"><form:label path="studySites[0].startDate"><span class="red">*</span><em></em>Start Date:</form:label> </td>
				      <td> <form:input path="studySites[0].startDate"/> <a href="#"
								onClick="cal1.select(document.getElementById('studySites[0].startDate'),'anchor1','MM/dd/yyyy');return false;" name="anchor1" id="anchor1"><img
								src="/caaers/images/b-calendar.gif" alt="Calendar" width="17"
								height="16" border="0" align="absmiddle"></a> </td>        			  
					</tr>

					<tr>
				      <td class="label"><form:label path="studySites[0].endDate">End Date:</form:label> </td>
				      <td><form:input
								path="studySites[0].endDate" />&nbsp;<a href="#"
								onClick="cal1.select(document.getElementById('studySites[0].endDate'),'anchor2','MM/dd/yyyy');return false;" name="anchor2" id="anchor2"><img
								src="/caaers/images/b-calendar.gif" alt="Calendar" width="17"
								height="16" border="0" align="absmiddle"></a> </td>        			  
					</tr>
					
					<tr>
				      <td class="label"><form:label path="studySites[0].irbApprovalDate"><span class="red">*</span><em></em>IRB
								Approval Date:</form:label> </td>
				      <td><form:input
								path="studySites[0].irbApprovalDate" />&nbsp;<a href="#"
								onClick="cal1.select(document.getElementById('studySites[0].irbApprovalDate'),'anchor3','MM/dd/yyyy');return false;" name="anchor3" id="anchor3"><img
								src="/caaers/images/b-calendar.gif" alt="Calendar" width="17"
								height="16" border="0" align="absmiddle"></a></td>        			  
					</tr>
																	
					</table>
				</chrome:division>
				</form:form>		
<!-- MAIN BODY ENDS HERE -->
</chrome:body>
</body>
</html>
