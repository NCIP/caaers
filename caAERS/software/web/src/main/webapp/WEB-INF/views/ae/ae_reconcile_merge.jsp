<%@include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<c:set var="widthSource" value="6%" scope="request" />
<c:set var="widthId" value="2%" scope="request" />
<c:set var="widthTerm" value="18%" scope="request" />
<c:set var="widthGrade" value="14%" scope="request" />
<c:set var="widthStartDate" value="12%" scope="request" />
<c:set var="widthEndDate" value="12%" scope="request" />
<c:set var="widthVerbatim" value="14%" scope="request" />
<c:set var="widthWhySerious" value="10%" scope="request" />
<c:set var="widthAttribution" value="8%" scope="request" />
<c:set var="widthActions" value="4%" scope="request" />
<head>
    <title>${tab.longTitle}</title>
    <tags:dwrJavascriptLink objects="createAE"/>

    <script type="text/javascript">

    </script>
    <style type="text/css">
        .td-ae3{
            background: gray;
        }
    </style>

</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section11courseandagent">
        <jsp:attribute name="singleFields">
        	<chrome:division title="Review AEs">
                <div class="eXtremeTable" >
                    <table class="tableRegion" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <thead>
                        <tr class="label" align="center">
                            <td class="tableHeader" width="${widthSource}">&nbsp;&nbsp;&nbsp;</td>
                            <td class="tableHeader" width="${widthId}">ID</td>
                            <td class="tableHeader" width="${widthTerm}"> Term</td>
                            <td class="tableHeader" width="${widthGrade}">Grade</td>
                            <td class="tableHeader" width="${widthStartDate}">Start</td>
                            <td class="tableHeader" width="${widthEndDate}">End</td>
                            <td class="tableHeader" width="${widthVerbatim}">Verbatim</td>
                            <td class="tableHeader" width="${widthWhySerious}">Why Serious?</td>
                            <td class="tableHeader" width="${widthAttribution}">Attribution</td>
                            <td class="tableHeader" width="${widthActions}"></td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="10" class="fillerRow">
                                &nbsp;
                            </td>
                        </tr>

                        <c:set var="_addFiller" value="false" />
                        <c:forEach var="e" items="${command.matchedAeMapping}" varStatus="x">
                            <c:if test="${differingAeIdMap[e.key.id]}">

                                <c:if test="${_addFiller}">
                                    <ae:mergeAERow fillerRow="true" />
                                </c:if>

                                <c:set var="mmkey" value="${e.key.id}_${e.value.id}" />
                                <ae:mergeAERow ae1="${e.key}" ae2="${e.value}" ae3="${command.mergeMap[mmkey]}" />



                                <c:set var="_addFiller" value="true" />
                            </c:if>


                        </c:forEach>

                        </tbody>
                    </table>
            </chrome:division>
        </jsp:attribute>
</tags:tabForm>
</body>
</html>