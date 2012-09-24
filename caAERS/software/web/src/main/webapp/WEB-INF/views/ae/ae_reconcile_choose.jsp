<%@include file="/WEB-INF/views/taglibs.jsp" %>
<c:set var="widthId" value="2%" scope="request" />
<c:set var="widthTerm" value="22%" scope="request" />
<c:set var="widthGrade" value="14%" scope="request" />
<c:set var="widthStartDate" value="6%" scope="request" />
<c:set var="widthEndDate" value="6%" scope="request" />
<c:set var="widthVerbatim" value="21%" scope="request" />
<c:set var="widthWhySerious" value="10%" scope="request" />
<c:set var="widthAttribution" value="9%" scope="request" />
<c:set var="widthActions" value="10%" scope="request" />
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
    <script type="text/javascript">
        AE.eRejected = [];
        AE.iRejected = [];
        function rejectAE(ae, external){
             var trId = (external ? 'eae-' : 'iae-') + ae + '-tr';
             var reject_div =  (external ? 'eae' : 'iae') + '-' + ae + '-div-reject';
             var unreject_div =  (external ? 'eae' : 'iae') + '-' + ae + '-div-unreject';

             if(external){
                 AE.eRejected.push(ae);
             } else {
                 AE.iRejected.push(ae);
             }
            $(reject_div).hide();
            $(unreject_div).show();

             $(trId).addClassName('ae-rejected');
        }
        function unrejectAE(ae, external){
             var trId = (external ? 'eae-' : 'iae-') + ae + '-tr';
             var reject_div =  (external ? 'eae' : 'iae') + '-' + ae + '-div-reject';
             var unreject_div =  (external ? 'eae' : 'iae') + '-' + ae + '-div-unreject';

             if(external){
                 var j =  AE.eRejected.indexOf(ae);
                 if(j > -1)AE.eRejected.splice(j, 1);
             } else {
                 var k =  AE.iRejected.indexOf(ae);
                 if(k > -1)AE.iRejected.splice(j, 1);
             }
             $(unreject_div).hide();
             $(reject_div).show();
             $(trId).removeClassName('ae-rejected');
        }
        ValidationManager.submitPreProcess = function(){
        $('rejectedExternalAeStr').value = AE.eRejected.join(',');
        $('rejectedInternalAeStr').value = AE.iRejected.join(',');
        return false;
        }
    </script>

</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section11courseandagent">
        <jsp:attribute name="singleFields">
        	<chrome:division title="New External Adverse Events">
                <div class="eXtremeTable" >
                    <table class="tableRegion" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <thead>
                        <tr class="label" align="center">
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
                            <td colspan="9" class="fillerRow">
                                &nbsp;
                            </td>
                        </tr>
                        <c:forEach var="e" items="${command.unMappedExternalAeList}" varStatus="x">
                              <ae:chooseAERow ae1="${e}" rejected="${rejectedExternalAeMap[e.id]}" external="true" />
                        </c:forEach>
                        <tr>
                            <td colspan="9" class="fillerRow">
                               &nbsp;
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <form:hidden id="rejectedExternalAeStr" path="rejectedExternalAeStr"  />
                </div>
            </chrome:division>

            <chrome:division title="Unmapped caAERS Adverse Events">
                <div class="eXtremeTable" >
                    <table class="tableRegion" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <thead>
                        <tr class="label" align="center">
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
                            <td colspan="9" class="fillerRow">
                                &nbsp;
                            </td>
                        </tr>
                        <c:forEach var="e" items="${command.unMappedInternalAeList}" varStatus="x">
                            <ae:chooseAERow ae1="${e}" rejected="${rejectedInternalAeMap[e.id]}" external="false" />
                        </c:forEach>
                        <tr>
                            <td colspan="9" class="fillerRow">
                                &nbsp;
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <form:hidden id="rejectedExternalAeStr" path="rejectedExternalAeStr"  />
                    <form:hidden id="rejectedInternalAeStr" path="rejectedInternalAeStr"  />
                </div>
            </chrome:division>

        </jsp:attribute>
</tags:tabForm>
</body>
</html>