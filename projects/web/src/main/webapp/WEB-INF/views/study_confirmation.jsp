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
<title>Confirmation</title>
</head>
<body>
<p id="instructions">
   You have successfully created Study
</p>
<form:form name="searchDetailsForm" method="post">
	<tags:tabFields tab="${tab}"/>
				<div>
			<chrome:division id="study-details">

	<div><input type="hidden" name="_page" value="1"></div>
					
<h3> <font color="green"> You have successfully created study with short title : ${param.studyName} </font></h3>
		</chrome:division>
</div>							
</form:form>
</body>
</html>
