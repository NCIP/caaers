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

	<form:form method="post" cssClass="standard">
		<tags:tabFields tab="${tab}" />


		<table width="700" border="0" cellspacing="0" cellpadding="0"
			id="details">
			<tr>
				<td width="50%" valign="top">
				<table width="308" border="0" cellspacing="0" cellpadding="0"
					id="table1">

					<div class="row">
					<div class="label" align="right"><form:label
						path="identifiers[0].primaryIndicator">Primary Indentifier</form:label></div>
					<div class="value" align="left"><form:checkbox
						path="identifiers[0].primaryIndicator" /></div>
					</div>

					<div class="row">
					<div class="label" align="right"><form:label
						path="identifiers[0].source">Source:</form:label></div>
					<div class="value" align="left"><form:input
						path="identifiers[0].source" /></div>
					</div>

					<div class="row">
					<div class="label" align="right"><form:label
						path="identifiers[0].type">Type:</form:label></div>
					<div class="value" align="left"><form:input
						path="identifiers[0].type" /></div>
					</div>

					<div class="row">
					<div class="label" align="right"><form:label
						path="identifiers[0].value">Value:</form:label></div>
					<div class="value" align="left"><form:input
						path="identifiers[0].value" /></div>
					</div>


				</table>
				</td>
		</table>
	</form:form>
	<!-- LEFT CONTENT ENDS HERE -->

	<!-- MAIN BODY ENDS HERE -->
</chrome:body>
</body>
</html>
