<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Temporary Create Study page</title>
    </head>
    <body>
        <h1>Create Study</h1>

	<!--_____ main content begins _____-->
		<c:url value="/pages/createStudy" var="formAction" />
	<form:form method="post" action="${formAction}">
		<table>
				<tr>
					<td>
						<form:label	path="shortTitle">Short Title</form:label>
					</td>
					<td>
						<form:input path="shortTitle" />
					</td>
				</tr>

				<tr>
					<td>
						<form:label	path="longTitle">Long Title</form:label>
					</td>
					<td>
						<form:input path="longTitle" />
					</td>
				</tr>
				
				<tr>
					<td>
						<form:label	path="description">Description</form:label>
					</td>
					<td>
						<form:input path="description" />
					</td>
				</tr>				

				<tr>
					<td>
						<form:label	path="principalInvestigatorCode">Investigator Code</form:label>
					</td>
					<td>
						<form:input path="principalInvestigatorCode" />
					</td>
				</tr>
				
				<tr>
					<td>
						<form:label	path="principalInvestigatorName">Investigator Name</form:label>
					</td>
					<td>
						<form:input path="principalInvestigatorName" />
					</td>
				</tr>				

				<tr>
					<td>
						<form:label	path="primarySponsorCode">Sponsor Code</form:label>
					</td>
					<td>
						<form:input path="primarySponsorCode" />
					</td>
				</tr>


				<tr>
					<td>
						<form:label	path="primarySponsorName">Sponsor Name</form:label>
					</td>
					<td>
						<form:input path="primarySponsorName" />
					</td>
				</tr>

				<tr>
					<td>
						<form:label	path="studySites[0].site.name">Default Site Name</form:label>
					</td>
					<td>
						<form:input path="studySites[0].site.name" />
					</td>
				</tr>
				
				<tr>
					<td>
						<input type="submit" value="Create Study" />
					</td>
				</tr>
		</table>

	</form:form>
	


    </body>
</html>

