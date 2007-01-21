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
</head>
<body>
<!-- MAIN BODY STARTS HERE -->
<chrome:body title="${flow.name}: ${tab.longTitle}">
				<form:form method="post">
				<tags:tabFields tab="${tab}"/>
				
					<table width="600" border="0" cellspacing="0" cellpadding="0"
						id="table1">
					
					<div class="row">
						<div class="label"><label for="studySites[0].site">Site:</label></div>
						<div class="value">
						<select id="studySites[0].site">							
							<c:forEach items="${sitesRefData}" var="temp">
								<option value="${temp.id}">${temp.name}</option>
							</c:forEach>
						</select>
					</div>
					</div>

						
					<div class="row">					
						 <div class="label" align="right"><form:label path="studySites[0].statusCode">Status
							Code:</form:label></div>			
						 <div class="value" align="left"><form:input path="studySites[0].statusCode"/></div>
				    </div>	

					<div class="row">					
						 <div class="label" align="right"><form:label path="studySites[0].roleCode">Role
							Code:</form:label></div>			
						 <div class="value" align="left"><form:input path="studySites[0].roleCode"/></div>
				    </div>	

					<div class="row">					
						 <div class="label" align="right"><form:label path="studySites[0].startDate">Start Date:</form:label></div>			
						 <div class="value" align="left"><form:input path="studySites[0].startDate"/><a href="#"
								onClick="parent.OpenWins('calendar.htm','calendar',200,236);return false;"><img
								src="images/b-calendar.gif" alt="Calendar" width="17"
								height="16" border="0" align="absmiddle"></a></div>
				    </div>
					
					<div class="row">					
						 <div class="label" align="right"><form:label path="studySites[0].endDate">End Date:</form:label></div>			
						 <div class="value" align="left"><form:input path="studySites[0].endDate"/><a href="#"
								onClick="parent.OpenWins('calendar.htm','calendar',200,236);return false;"><img
								src="images/b-calendar.gif" alt="Calendar" width="17"
								height="16" border="0" align="absmiddle"></a></div>
				    </div>
					
					<div class="row">					
						 <div class="label" align="right"><form:label path="studySites[0].irbApprovalDate">IRB
								Approval Date:</form:label></div>			
						 <div class="value" align="left"><form:input path="studySites[0].irbApprovalDate"/><a href="#"
								onClick="parent.OpenWins('calendar.htm','calendar',200,236);return false;"><img
								src="images/b-calendar.gif" alt="Calendar" width="17"
								height="16" border="0" align="absmiddle"></a></div>
				    </div>																						
												
					</table>
				</form:form>		
<!-- MAIN BODY ENDS HERE -->
</chrome:body>
</body>
</html>
