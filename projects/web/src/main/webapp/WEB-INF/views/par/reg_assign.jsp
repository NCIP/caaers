<!-- TODO: This view needs to be refactored. -->
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<html>
<head>
<title>Search for a Study or Subject </title>
<script type="text/javascript">
	function navRollOver(obj, state) {
  		document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
	}

	
</script>
</head>
<body>
<p id="instructions">In order to assign a Subject to a Study, you need to first search for either a subject or a study.</p>
<form:form id="searchForm" name="searchForm" action="/caaers/pages/participant/assignStudy" method="post" cssClass="standard">
	<chrome:box autopad="true" cssClass="paired" title="Select Study"
		id="study-search">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr valign="top">
				<td width="99%"><strong>1. Search Study by:</strong> 
				<form:select path="studyType" cssClass="validate-NOTEMPTY">
					<form:options items="${studySearchType}" itemLabel="desc"
						itemValue="code" />
				</form:select> 
				<br>
				<br>
				<strong>2. Fill in the Fields:</strong><br>
				<br>
				<table border="0" cellspacing="0" cellpadding="0" id="search">

					<tr>
						<td align="left" class="labels">Search String:</td>
					</tr>
					<tr>
						<td><form:input path="studyText" size="25" cssClass="validate-NOTEMPTY"/></td>
					</tr>
					<tr>
						<td>
						<div id="errorStudy"></div>
						</td>
					</tr>
					</div>
				</table>
				<br>
				<input class="ibutton" type="submit" value="Search studies" />
				
			</tr>
		</table>

	</chrome:box>
</form:form>
<form:form id="searchForm2" name="searchForm2"
	action="/caaers/pages/participant/assignParticipant" method="post"
	cssClass="standard">
	<chrome:box autopad="true" cssClass="paired" title="Select Subject"
		id="participant-search">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr valign="top">
				<td width="99%"><strong>1. Search Subject by:</strong>
				 <form:select path="participantType" cssClass="validate-NOTEMPTY">
					<form:options items="${participantSearchType}" itemLabel="desc"	itemValue="code"  />
				</form:select> <br>
				<br>

				<strong>2. Fill in the Fields:</strong><br>
				<br>
				<table border="0" cellspacing="0" cellpadding="0" id="search">
					<tr>
						<td align="left" class="labels">Search String:</td>
					</tr>
					<tr>
						<td><form:input path="participantText" size="25" cssClass="validate-NOTEMPTY" /></td>
					</tr>
					<tr>
						<td>
						<div id="errorParticipant"></div>
						</td>
					</tr>
				</table>
				<br>
				<input class='ibutton' type='submit' value='Search Subjects' title='Search Subjects' /></td>
				</td>
			</tr>
		</table>
	</chrome:box>
</form:form>
</body>
</html>
