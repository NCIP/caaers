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
		document.studyIdentifiersForm._action.value=action;
		document.studyIdentifiersForm._selected.value=selected;		
		document.studyIdentifiersForm.submit();
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
	
	<form:form method="post" name="a">	
	    <tags:tabFields tab="${tab}" />
	</form:form>

	<form:form method="post" name="studyIdentifiersForm" cssClass="standard">

		<div>		
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
		</div>

		<table width="700" border="0" cellspacing="0" cellpadding="0"
			id="details">			

				<tr>
					<table width="50%" border="0" cellspacing="10" cellpadding="0"
									id="table1">								
								
								<tr>
									<td>
										<div class="row">
											<div class="label" align="center"><a href="javascript:fireAction('addIdentifier','0');"><img
										src="images/checkyes.gif" border="0" alt="Add another Identifier"></a> </div>
										</div>
									</td>
									<td>
										<div class="row">
											<div class="label" align="center">Source<span class="red">*</span> </div>
										</div>
									</td>
									<td>
										<div class="row">
											<div class="label" align="center">Identifier Type<span class="red">*</span> </div>
										</div>
									</td>
									<td>
										<div class="row">
											<div class="label" align="center">Identifier<span class="red">*</span> </div>
										</div>
									</td>
									<td>
										<div class="row">
											<div class="label" align="center">Primary Indicator</div>
										</div>
									</td>
								</tr>
								<c:forEach items="${command.identifiers}" varStatus="status">
									<tr align="center" class="results">
										<td>
										<div class="row">
										<div class="label"></div>
										<div class="value">
										<a href="javascript:fireAction('removeIdentifier',${status.index});"><img
											src="images/checkno.gif" border="0" alt="remove"></a>
											</div></div> 
											</td>
										<td> <div class="row"> <div class="label"></div> <div class="value"> <form:select path="identifiers[${status.index}].source">
											<option value="">--Please Select--									
											<form:options items="${identifiersSourceRefData}" itemLabel="desc"
												itemValue="code" /></form:select>
											</div></div>
										</td>

										<td> <div class="row"> <div class="label"></div><div class="value"> <form:select  path="identifiers[${status.index}].type">
											<option value="">--Please Select--									
											<form:options items="${identifiersTypeRefData}" itemLabel="desc"
												itemValue="code" /></form:select> </div></div></td>
										<td> <div class="row"> <div class="label"></div><div class="value"><form:input size="40" path="identifiers[${status.index}].value" onclick="javascript:clearField(this)();"/> </div> </div></td>
										<td><div class="row"><form:radiobutton path="identifiers[${status.index}].primaryIndicator"/> </div></td> 
									</tr> 
								</c:forEach> 
								</table>

				</tr>
		</table>
	</form:form>
	<!-- LEFT CONTENT ENDS HERE -->

	<!-- MAIN BODY ENDS HERE -->
</chrome:body>
</body>
</html>
