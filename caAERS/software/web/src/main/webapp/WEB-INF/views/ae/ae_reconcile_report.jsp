<%@include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <style type="text/css">
        tr.ae-rejected {
            text-decoration: line-through;
        }
        .wgtBtnDiv{
            width: 7em;
        }
    </style>
</head>
<body>

<chrome:division title="(${fn:length(report.forceAesToBeAdded)}) Records To be Added to Source System" collapsable="true" id="div1">
    <div class="eXtremeTable" >
        <table class="tableRegion" width="100%" border="0" cellspacing="1" cellpadding="1">
            	<ae:reconciledAeRowHeader/>
            <tbody>
            <c:forEach items="${report.forceAesToBeAdded}" varStatus="x" var="reconAe">
            	<ae:reconciledAeRow ae="${reconAe}" />
            </c:forEach>
            </tbody>
        </table>
    </div>
</chrome:division>

<chrome:division title="(${fn:length(report.forceAesToBeUpdated)}) Records To be Updated in the Source System" collapsable="true" id="div2">
    <div class="eXtremeTable" >
        <table class="tableRegion" width="100%" border="0" cellspacing="1" cellpadding="1">
            	<ae:reconciledAeRowHeader/>
            <tbody>
            <c:forEach items="${report.forceAesToBeUpdated}" varStatus="x" var="reconAe">
            	<ae:reconciledAeRow ae="${reconAe}" />
            </c:forEach>
            </tbody>
        </table>
    </div>
</chrome:division>

<chrome:division title="(${fn:length(report.forceAesToBeDeleted)}) Records To be Deleted from Source System" collapsable="true" id="div3">
    <div class="eXtremeTable" >
        <table class="tableRegion" width="100%" border="0" cellspacing="1" cellpadding="1">
            	<ae:reconciledAeRowHeader/>
            <tbody>
            <c:forEach items="${report.forceAesToBeDeleted}" varStatus="x" var="reconAe">
            	<ae:reconciledAeRow ae="${reconAe}" />
            </c:forEach>
            </tbody>
        </table>
    </div>
</chrome:division>

<chrome:division title="(${fn:length(report.aesWithErrors)}) Records with Integration Errors" collapsable="true" id="div4">
    <div class="eXtremeTable" >
        <table class="tableRegion" width="100%" border="0" cellspacing="1" cellpadding="1">
            	<ae:reconciledAeRowHeader/>
            <tbody>
            <c:forEach items="${report.aesWithErrors}" varStatus="x" var="reconAe">
            	<ae:reconciledAeRow ae="${reconAe}" displayError="true"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
</chrome:division>

<chrome:division title="(${fn:length(report.caaersAesToBeDeleted)}) Records Deleted from caAERS" collapsable="true" id="div5">
    <div class="eXtremeTable" >
        <table class="tableRegion" width="100%" border="0" cellspacing="1" cellpadding="1">
            	<ae:reconciledAeRowHeader/>
            <tbody>
            <c:forEach items="${report.caaersAesToBeDeleted}" varStatus="x" var="reconAe">
            	<ae:reconciledAeRow ae="${reconAe}" />
            </c:forEach>
            </tbody>
        </table>
    </div>
</chrome:division>

<chrome:division title="(${fn:length(report.caaersAesToBeUpdated)}) Records Updated in caAERS" collapsable="true" id="div6">
    <div class="eXtremeTable" >
        <table class="tableRegion" width="100%" border="0" cellspacing="1" cellpadding="1">
            	<ae:reconciledAeRowHeader/>
            <tbody>
            <c:forEach items="${report.caaersAesToBeUpdated}" varStatus="x" var="reconAe">
            	<ae:reconciledAeRow ae="${reconAe}" />
            </c:forEach>
            </tbody>
        </table>
    </div>
</chrome:division>

<chrome:division title="(${fn:length(report.caaersAesToBeAdded)}) Records Added to caAERS" collapsable="true" id="div7">
    <div class="eXtremeTable" >
        <table class="tableRegion" width="100%" border="0" cellspacing="1" cellpadding="1">
            	<ae:reconciledAeRowHeader/>
            <tbody>
            <c:forEach items="${report.caaersAesToBeAdded}" varStatus="x" var="reconAe">
            	<ae:reconciledAeRow ae="${reconAe}" />
            </c:forEach>
            </tbody>
        </table>
    </div>
</chrome:division>

</body>
</html>