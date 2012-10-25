<%@include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<c:set var="widthSource" value="6%" scope="request" />
<c:set var="widthTerm" value="18%" scope="request" />
<c:set var="widthGrade" value="14%" scope="request" />
<c:set var="widthStartDate" value="12%" scope="request" />
<c:set var="widthEndDate" value="12%" scope="request" />
<c:set var="widthVerbatim" value="14%" scope="request" />
<c:set var="widthWhySerious" value="14%" scope="request" />
<c:set var="widthAttribution" value="10%" scope="request" />
<caaers:message var="unmergedAEAlert" code="reconciliation.empty.selectAbove" text="Unmerged adverse events on page." />
<head>
    <title>${tab.longTitle}</title>

    <script type="text/javascript">
        ValidationManager.submitPostProcess = function(formVar, flag, submit) {
            var selectAboves = $$('.selectAbove');
            if(selectAboves.length > 0) {
                alert( "${unmergedAEAlert}");
                AE.SUBMISSION_INPROGRESS = false;
                return false;
            }
            return true;

        }

        function updateCellValue(ae1, ae2, item, el){
            var td = $('td-' + item + ae1);
            var v = el.value;
            if(v == '') v = '--'
            td.innerHTML = v;
            td.addClassName('td-highlight');
            Element.removeClassName.delay(1, td, 'td-highlight');

            if(item == 'term'){
                 el.siblings().each(function(e){
                      if(e.type == 'hidden'){
                          var x = $('term' + ae1 + '-' +e.name);
                          if(x) x.value = e.value;
                      }
                 });
            } else {
                $(item + ae1).value = el.value;
            }

            $$('col-'+ item + ae1).each(function(e){
                e.removeClassName('empty');
            });

        }
    </script>
    <style type="text/css">
        td.td-ae3{
            background: #EFEFEF;
            color: #888;
            text-shadow: 0 1px white;
        }
        td.td-highlight {
           background: #ffff99;
        }
        td.empty{
            color: red;
        }
        td.empty input{
            border: 1px solid #900;
        }

    </style>

</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}"  hideTabControls="${command.noExternalAes}">
        <jsp:attribute name="singleFields">
        	<chrome:division title="Review AEs">
                <div class="eXtremeTable" >
                    <table class="tableRegion" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <thead>
                        <tr class="label" align="center">
                            <td class="tableHeader" width="${widthSource}">&nbsp;&nbsp;&nbsp;</td>
                            <td class="tableHeader" width="${widthTerm}"> Term</td>
                            <td class="tableHeader" width="${widthGrade}">Grade</td>
                            <td class="tableHeader" width="${widthStartDate}">Start</td>
                            <td class="tableHeader" width="${widthEndDate}">End</td>
                            <td class="tableHeader" width="${widthVerbatim}">Verbatim</td>
                            <td class="tableHeader" width="${widthWhySerious}">Why Serious?</td>
                            <td class="tableHeader" width="${widthAttribution}">Attribution</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="8" class="fillerRow">
                                &nbsp;
                            </td>
                        </tr>
                        <c:set var="cntr" value="0"/>
                        <c:forEach var="e" items="${command.matchedAeMapping}" varStatus="x">
                            <c:if test="${differingAeIdMap[e.key.id]}">
                                <c:set var="cntr" value="${cntr + 1}"/>
                                <c:set var="mmkey" value="${e.key.id}_${e.value.id}" />
                                <ae:mergeAERow ae1="${e.key}" ae2="${e.value}" ae3="${command.mergeMap[mmkey]}" cssClass="${cntr % 2 == 0 ? 'evenx' : 'oddx'}" />
                            </c:if>
                        </c:forEach>

                        </tbody>
                    </table>
            </chrome:division>
        </jsp:attribute>

</tags:tabForm>
</body>
</html>