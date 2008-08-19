<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<html>
	<head>
	</head>
	<body>
		This is AeReport Summary Page.
		<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enteraes" formName="aeReportForm">
			
		</tags:tabForm>
	</body>
</html>