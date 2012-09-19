<%@include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:dwrJavascriptLink objects="createAE"/>

    <script type="text/javascript">

    </script>

</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section11courseandagent">
        <jsp:attribute name="singleFields">
        	<chrome:division title="Review AEs">
                blah
            </chrome:division>
        </jsp:attribute>
</tags:tabForm>
</body>
</html>