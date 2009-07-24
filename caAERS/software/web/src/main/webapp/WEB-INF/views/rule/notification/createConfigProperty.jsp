<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<page:applyDecorator name="standardNoHeader">
<html>
<head>
</head>
<body>
<tags:standardForm title="Create new report type" hideErrorDetails="true">

    <jsp:attribute name="singleFields">
     	<c:forEach items="${fieldGroups.ReportType.fields}" var="field">
        	<tags:renderRow field="${field}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="navButtons"><div align="right"><tags:button color="green" type="submit" id="flow-update" cssClass="tab${tabNumber}" value="Save" icon="save"/></div></jsp:attribute>
    <jsp:body>
    </jsp:body>

</tags:standardForm>
</body>
</html>
</page:applyDecorator>