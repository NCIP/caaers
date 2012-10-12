<%@include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <style type="text/css">
        tr.ae-rejected {
            color: gray
        }
        .wgtBtnDiv{
            width: 7em;
        }
    </style>
    <script type="text/javascript">
        function confirmAndSave(){
            if (confirm("<caaers:message code="reconciliation.report.confirm" />"))  {
                $('finishHidden').name = '_finish';
                $('command').submit();
            }
        }

    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" hideTabControls="${command.noExternalAes}">
<jsp:attribute name="singleFields">
    <c:set var= "dash" value="--" scope="request" />
    <c:set var="report" value="${requestScope.report}"  />
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
</jsp:attribute>
    <jsp:attribute name="tabControls">
            <div class="content buttons autoclear">
                <div class="flow-buttons">
            <span class="prev">
                <button id="flow-prev" type="submit" class="omnipotent-button blue tab1" title="">
                    <table>
                        <tbody>
                        <tr>
                            <td class="l"></td>
                            <td class="m">
                                <img alt="" src="/caaers/images/buttons/button_icons/back_icon.png">
                                <span id="flow-prev-value">Back</span>
                            </td>
                            <td class="r"></td>
                        </tr>
                        </tbody>
                    </table>
                </button>
            </span>
            <span class="next">

                <button id="confirm" type="button" class="omnipotent-button green" title="" onclick="confirmAndSave()">
                    <table>
                        <tbody>
                        <tr>
                            <td class="l"></td>
                            <td class="m">
                                <span id="flow-next-value">Save</span>
                                <img alt="" src="/caaers/images/buttons/button_icons/disk_icon.png">
                            </td>
                            <td class="r"></td>
                        </tr>
                        </tbody>
                    </table>
                </button>
            </span>
                </div>
            </div>
            <input type="hidden" name="_unwanted" value="true" id="finishHidden" />
        </jsp:attribute>
</tags:tabForm>
</body>
</html>