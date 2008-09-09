<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>${tab.longTitle}</title>
</head>
<body>

    <h1>PATIENT DETAILS (Patient Medical History)</h1>
	<tags:tabForm flow="${flow}" tab="${tab}">
	</tags:tabForm>
</body>
</html>