<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
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
	   <form:form method="post" cssClass="standard">
			<tags:tabFields tab="${tab}"/>
			<div>
                <table>
				  <tr>
				  <td>
                 	<div class="row">					
			            <div class="label" align="right"><form:label path="shortTitle">Short Title:</form:label></div>					
			            <div class="value" align="left"><form:textarea path="shortTitle" rows="1" cols="50"/></div>
			        </div>
			        <div class="row">
			            <div class="label"><form:label path="longTitle"><span class="red">*</span><em></em>Long
		        				Title:</form:label></div>
				        <div class="value"><form:textarea path="longTitle" rows="3" cols="50"/></div>
				    </div>
				    <div class="row">
				         <div class="label"><form:label path="precis">Precis Text:</form:label></div>
				         <div class="value"><form:textarea path="precis" rows="3" cols="50"/></div>
				    </div>
				    <div class="row">
				         <div class="label"><form:label path="description">Description Text:</form:label></div>
				         <div class="value"><form:textarea path="description" rows="3" cols="50"/></div>
				     </div>																								
		        
                	<div class="row">
				          <div class="label"><form:label path="targetAccrualNumber">Target Accrual Number:</form:label></div>
				          <div class="value"><form:input path="targetAccrualNumber"/></div>
				    </div>
					</td>
					<td>
				
					<div class="row">
						<div class="label"><label for="status">Status:</label></div>
						<div class="value">
						<select id="status">							
							<c:forEach items="${statusRefData}" var="temp">
								<option value="${temp.code}">${temp.desc}</option>
							</c:forEach>
						</select>
					</div>
					</div>

					<div class="row">
						<div class="label"><label for="diseaseCode">Disease Code:</label></div>
						<div class="value">
						<select id="diseaseCode">							
							<c:forEach items="${diseaseCodeRefData}" var="temp">
								<option value="${temp.code}">${temp.desc}</option>
							</c:forEach>
						</select>
					</div>
					</div>
					
					<div class="row">
						<div class="label"><label for="monitorCode">Monitor Code:</label></div>
						<div class="value">
						<select id="monitorCode">							
							<c:forEach items="${monitorCodeRefData}" var="temp">
								<option value="${temp.code}">${temp.desc}</option>
							</c:forEach>
						</select>
					</div>
					</div>

					<div class="row">
						<div class="label"><label for="phaseCode">Phase Code:</label></div>
						<div class="value">
						<select id="phaseCode">							
							<c:forEach items="${phaseCodeRefData}" var="temp">
								<option value="${temp.code}">${temp.desc}</option>
							</c:forEach>
						</select>
					</div>
					</div>
       													        														
					<div class="row">
					    <div class="label"><form:label path="randomizedIndicator">Randomized Indicator</form:label></div>
						<div class="value"><form:checkbox path="randomizedIndicator" /></div>
				    </div> 
								
								 <div class="row">
						            <div class="label"><form:label path="multiInstitutionIndicator">Multi
									Institution:</form:label></div>
						            <div class="value"><form:checkbox path="multiInstitutionIndicator" /></div>
						        </div> 
						        
								
								<div class="row">
						            <div class="label"><form:label path="blindedIndicator">Blinded Indicator:</form:label></div>
						            <div class="value"><form:checkbox path="blindedIndicator" /></div>
						        </div>
						        
					
<%-- 
								<div class="label"><label for="type">Type Code:</label></div>
						            <div class="value" align="right">
					                <form:select path="type">
										<form:options items="${typeRefData}" itemLabel="desc"
											itemValue="code"/>
									</form:select></td>
            					</div>
        						</div>
--%>
				</td>
				</tr>
				</table>
								
				</div>
        </form:form>
        </chrome:body>
<!-- MAIN BODY ENDS HERE -->
</body>
</html>
