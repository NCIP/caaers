<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
    <head>
    </head>
<body>

<tags:standardForm title="Create new report type" hideErrorDetails="true">

    <jsp:attribute name="singleFields">
        <ui:row path="code">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportType.fields[0]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:text path="code" field="${fieldGroups.ReportType.fields[0]}" required="true"/></jsp:attribute>
        </ui:row>
        <ui:row path="name">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportType.fields[1]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:text path="name" field="${fieldGroups.ReportType.fields[1]}" required="true"/></jsp:attribute>
        </ui:row>

        <ui:row path="description">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportType.fields[2]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:textarea path="description" field="${fieldGroups.ReportType.fields[2]}" required="false"/></jsp:attribute>
        </ui:row>
    </jsp:attribute>
    <jsp:attribute name="navButtons"><div align="right"><tags:button color="green" type="submit" id="flow-update" cssClass="tab${tabNumber}" value="Save" icon="save"/></div></jsp:attribute>
    <jsp:body>
    </jsp:body>

</tags:standardForm>

</body>
</html>