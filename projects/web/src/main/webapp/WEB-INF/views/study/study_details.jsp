
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
</head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}">
	<jsp:attribute name="singleFields">
		<div class="content">
			<div class="section">
				<div class="row">
					<div class="label"><span class="red">*</span>Short Title:</div>
					<div class="value"><form:input path="shortTitle" maxlength="30" size="40" /></div>
				</div>
				<div class="row">
					<div class="label"><span class="red">*</span>Long Title:</div>
					<div class="value"><form:textarea path="longTitle" rows="3" cols="50"/></div>
				</div>
				<div class="row">
					<div class="label">Precis:</div>	
					<div class="value"><form:textarea path="precis" rows="3" cols="50"/> </div>
				</div>
				<div class="row">
					<div class="label">Description:</div>	
					<div class="value"><form:textarea path="description" rows="3" cols="50"/> </div>
				</div>
				<div class="row">
					<div class="label"><span class="red">*</span><em></em>Phase:</div>
					<div class="value"><form:select path="primarySponsorCode">
						<option value="">--please select --</option>
						<form:options items="${phaseCodeRefData}" itemLabel="desc"
							itemValue="code"/>
						</form:select>
					</div>
				</div>
				<div class="row">
					<div class="label"><span class="red">*</span><em></em>Sponsor:</div>
					<div class="value"><form:select path="primarySponsorCode">
						<option value="">--please select --</option>
						<form:options items="${sponsorCodeRefData}" itemLabel="desc"
							itemValue="code"/>
						</form:select>		
					</div>
				</div>
				
			</div>
		</div>
	</jsp:attribute>
</tags:tabForm>
</body>
</html>
