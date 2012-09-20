<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="ae1" required="false" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
<%@attribute name="ae2" required="false" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
<%@attribute name="ae3" required="false" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
    <c:set var="widgetId" value="${ae1.id}" />
    <c:set var= "dash" value="--" />
    <c:set var="t1" value="${ae1.term.displayName}" />
    <c:set var="t2" value="${ae2.term.displayName}" />
    <c:set var="t3" value="${ae3.term.displayName}" />

    <c:set var="g1" value="${ ae1.grade }" />
    <c:set var="g2" value="${  ae2.grade }" />
    <c:set var="g3" value="${  ae3.grade }" />

    <c:set var="sd1" value="${ ae1.startDate  }" />
    <c:set var="sd2" value="${  ae2.startDate  }" />
    <c:set var="sd3" value="${  ae3.startDate  }" />

    <c:set var="ed1" value="${  ae1.endDate  }" />
    <c:set var="ed2" value="${  ae2.endDate }" />
    <c:set var="ed3" value="${  ae3.endDate }" />

    <c:set var="v1" value="${  ae1.verbatim }" />
    <c:set var="v2" value="${  ae2.verbatim }" />
    <c:set var="v3" value="${  ae3.verbatim }" />

    <c:set var="s1" value="${  ae1.whySerious }" />
    <c:set var="s2" value="${  ae2.whySerious }" />
    <c:set var="s3" value="${  ae3.whySerious }" />

    <c:set var="a1" value="${  ae1.attribution }" />
    <c:set var="a2" value="${  ae2.attribution }" />
    <c:set var="a3" value="${  ae3.attribution }" />

    <c:set var="mmkey" value="${ae1.id}_${ae2.id}"  />

    <tr id="aemrg-${widgetId}-tr-iae" class="tr-ae1">
        <td width="${requestScope.widthSource}" class="td-ae1" >${ae1.source}</td>
        <td width="${requestScope.widthId}" class="td-ae1" >${empty ae1.externalID ? dash : ae1.externalID }</td>
        <td width="${requestScope.widthTerm}" class="td-ae1" >
            <c:if test="${t1 ne t2}"><input type="radio" name="t${widgetId}" value="${t1}"  ${t3 eq t1? 'checked="yes"':''}  onclick="updateCellValue('${widgetId}','term', this)" /></c:if>${empty t1 ? dash : t1}
        </td>
        <td width="${requestScope.widthGrade}" class="td-ae1" >
            <c:if test="${g1 ne g2}"><input type="radio" name="g${widgetId}" value="${g1}"  ${g3 eq g1? 'checked="yes"':'' }  onclick="updateCellValue('${widgetId}','grade', this)" /></c:if>${empty g1 ? dash : g1}
        </td>
        <td width="${requestScope.widthStartDate}" class="td-ae1" >
            <c:if test="${sd1 ne sd2}"><input type="radio" name="st${widgetId}" value="${sd1}"  ${sd3 eq sd1? 'checked="yes"':''}  onclick="updateCellValue('${widgetId}','startDate', this)" /></c:if>${empty sd1? dash:sd1}
        </td>
        <td width="${requestScope.widthEndDate}" class="td-ae1" >
            <c:if test="${ed1 ne ed2}"><input type="radio" name="en${widgetId}" value="${ed1}"  ${ed3 eq ed1? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','endDate', this)" /></c:if>${empty ed1 ? dash : ed1}
        </td>
        <td width="${requestScope.widthVerbatim}" class="td-ae1" >
            <c:if test="${v1 ne v2}"><input type="radio" name="v${widgetId}" value="${v1}"  ${v3 eq v1? 'checked="yes"':''}  onclick="updateCellValue('${widgetId}','verbatim', this)" /></c:if>${empty v1 ? dash : v1}
        </td>
        <td width="${requestScope.widthWhySerious}" class="td-ae1" >
            <c:if test="${s1 ne s2}"><input type="radio" name="s${widgetId}" value="${s1}"  ${s3 eq s1? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','whySerious', this)" /></c:if>${empty s1? dash : s1}
        </td>
        <td width="${requestScope.widthAttribution}" class="td-ae1" >
            <c:if test="${a1 ne a2}"><input type="radio" name="a${widgetId}" value="${a1}"  ${a3 eq a1? 'checked="yes"':''}    onclick="updateCellValue('${widgetId}','attribution', this)" /></c:if>${empty a1? dash : a1}
        </td>
        <td width="${requestScope.widthActions}" class="actionBtns" class="td-ae1" >
            &nbsp;
        </td>
    </tr>
    <tr id="aemrg-${widgetId}-tr-eae" class="tr-ae2">
        <td width="${requestScope.widthSource}" class="td-ae2" >${ae2.source}</td>
        <td width="${requestScope.widthId}" class="td-ae2" >${empty ae2.externalID ? dash : ae2.externalID }</td>
        <td width="${requestScope.widthTerm}" class="td-ae2" >
            <c:if test="${t1 ne t2}"><input type="radio" name="t${widgetId}" value="${t2}"  ${t3 eq t2? 'checked="yes"':''}  onclick="updateCellValue('${widgetId}','term', this)" /></c:if>${empty t2 ? dash : t2}
        </td>
        <td width="${requestScope.widthGrade}" class="td-ae2" >
            <c:if test="${g1 ne g2}"><input type="radio" name="g${widgetId}" value="${g2}" ${g3 eq g2? 'checked="yes"':''}  onclick="updateCellValue('${widgetId}','grade', this)" /></c:if>${empty g2 ? dash:g2}
        </td>
        <td width="${requestScope.widthStartDate}" class="td-ae2" >
            <c:if test="${sd1 ne sd2}"><input type="radio" name="st${widgetId}" value="${sd2}"  ${sd3 eq sd2? 'checked="yes"':''}    onclick="updateCellValue('${widgetId}','startDate', this)"  /></c:if>${empty sd2 ? dash : sd2}
        </td>
        <td width="${requestScope.widthEndDate}" class="td-ae2" >
            <c:if test="${ed1 ne ed2}"><input type="radio" name="en${widgetId}" value="${ed2}"  ${ed3 eq ed2? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','endDate', this)" /></c:if>${empty ed2 ? dash : ed2}
        </td>
        <td width="${requestScope.widthVerbatim}" class="td-ae2" >
            <c:if test="${v1 ne v2}"><input type="radio" name="v${widgetId}" value="${v2}"  ${v3 eq v2? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','verbatim', this)"  /></c:if>${empty v2 ? dash : v2}
        </td>
        <td width="${requestScope.widthWhySerious}" class="td-ae2" >
            <c:if test="${s1 ne s2}"><input type="radio" name="s${widgetId}" value="${s2}" ${s3 eq s2 ? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','whySerious', this)"  /></c:if>${empty s2 ? dash : s2}
        </td>
        <td width="${requestScope.widthAttribution}" class="td-ae2" >
            <c:if test="${a1 ne a2}"><input type="radio" name="a${widgetId}" value="${a2}" ${a3 eq a2 ? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','attribution', this)"  /></c:if>${empty a2 ? dash : a2}
        </td>
        <td width="${requestScope.widthActions}" class="actionBtns" class="td-ae2" >
            &nbsp;
        </td>
    </tr>
    <tr>
        <td colspan="10" class="fillerRow">
            <hr align="center" width="96%" size="1" color="blue">
        </td>
    </tr>
    <tr id="aemrg-${widgetId}-tr-mae"  class="tr-ae3" >
        <td width="${requestScope.widthSource}" class="td-ae3" >&nbsp;&nbsp;&nbsp;
            <form:hidden id="term${widgetId}-id" path="mergeMap[${mmkey}].term.id" />
            <form:hidden id="term${widgetId}-code" path="mergeMap[${mmkey}].term.code" />
            <form:hidden id="term${widgetId}-otherSpecify" path="mergeMap[${mmkey}].term.otherSpecify" />
            <form:hidden id="grade${widgetId}" path="mergeMap[${mmkey}].grade" />
            <form:hidden id="verbatim${widgetId}" path="mergeMap[${mmkey}].verbatim" />
            <form:hidden id="attribution${widgetId}" path="mergeMap[${mmkey}].attribution" />
            <form:hidden id="startDate${widgetId}" path="mergeMap[${mmkey}].startDate" />
            <form:hidden id="endDate${widgetId}" path="mergeMap[${mmkey}].endDate" />
            <form:hidden id="whySerious${widgetId}" path="mergeMap[${mmkey}].whySerious" />
        </td>
        <td width="${requestScope.widthId}"  class="td-ae3"  >${empty ae2.externalID ? dash : ae2.externalID }</td>
        <td width="${requestScope.widthTerm}" id="td-term${widgetId}" class="td-ae3" >
            ${( (t1 eq t3) || (t2 eq t3)) ? t3 : '<div class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthGrade}" id="td-grade${widgetId}"  class="td-ae3" >
                ${( (g1 eq g3) || (g2 eq g3)) ? g3 : '<div class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthStartDate}" id="td-startDate${widgetId}" class="td-ae3" >
                ${( (sd1 eq sd3) || (sd2 eq sd3)) ? sd3 : '<div class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthEndDate}" id="td-endDate${widgetId}"  class="td-ae3" >
                ${( (ed1 eq ed3) || (ed2 eq ed3)) ? ed3 : '<div class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthVerbatim}" id="td-verbatim${widgetId}" class="td-ae3" >
                ${( (v1 eq v3) || (v2 eq v3)) ? v3 : '<div class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthWhySerious}" id="td-whySerious${widgetId}"  class="td-ae3" >
                ${( (s1 eq s3) || (s2 eq s3)) ? s3 : '<div class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthAttribution}" id="td-attribution${widgetId}" class="td-ae3" >
                ${( (a1 eq a3) || (a2 eq a3)) ? a3 : '<div class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthActions}" class="actionBtns" class="td-ae3" >
           &nbsp;
        </td>
    </tr>
    <tr>
        <td colspan="10" class="fillerRow">
            &nbsp;
        </td>
    </tr>