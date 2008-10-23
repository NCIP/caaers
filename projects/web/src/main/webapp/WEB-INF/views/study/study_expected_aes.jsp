<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
	<title>${tab.longTitle}</title>

    <tags:javascriptLink name="hover-display" />
	<tags:dwrJavascriptLink objects="createStudy"/>

</head>
<body>

<!-- STUDY SUMMARY -->
    <study:summary />
<!-- STUDY SUMMARY -->

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false">
</tags:tabForm>

<hr style="color:green; size:1px;">
[Expected AEs]

</body>
</html>
