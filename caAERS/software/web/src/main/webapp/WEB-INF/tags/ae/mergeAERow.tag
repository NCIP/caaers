<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="ae1" required="false" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
<%@attribute name="ae2" required="false" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
<%@attribute name="ae3" required="false" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
<%@attribute name="cssClass"  %>
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

    <tr id="aemrg-${widgetId}-tr-iae" class="tr-ae1 ${cssClass}">
        <td width="${requestScope.widthSource}" class="td-ae1 tr-ae1-c1 ${cssClass}" >${ae1.source}</td>
        <td width="${requestScope.widthId}" class="td-ae1  tr-ae1-c2 ${cssClass}" >${empty ae1.externalID ? dash : ae1.externalID }</td>
        <td width="${requestScope.widthTerm}" class="td-ae1  tr-ae1-c3  col-term-${widgetId} ${cssClass}" >
            <c:if test="${t1 ne t2}">
                <input type="hidden" id="t1${widgetId}-id" name="id" value="${ae1.term.id}" />
                <input type="hidden" id="t1${widgetId}-code" name="code" value="${ae1.term.code}" />
                <input type="hidden" id="t1${widgetId}-name" name="name" value="${ae1.term.name}" />
                <input type="hidden" id="t1${widgetId}-otherSpecify" name="otherSpecify" value="${ae1.term.otherSpecify}" />
                <input id="rb1-t${widgetId}" type="radio" name="t${widgetId}" value="${t1}"  ${t3 eq t1? 'checked="yes"':''}  onclick="updateCellValue('${widgetId}','${ae2.id}','term', this)" />
            </c:if>
            <label id="rb2-t${widgetId}" >${empty t1 ? dash : t1} </label>

        </td>
        <td width="${requestScope.widthGrade}" class="td-ae1  tr-ae1-c4  col-grade-${widgetId} ${cssClass}" >
            <c:if test="${g1 ne g2}"><input  id="rb1-g${widgetId}"  type="radio" name="g${widgetId}" value="${g1}"  ${g3 eq g1? 'checked="yes"':'' }  onclick="updateCellValue('${widgetId}','${ae2.id}','grade', this)" /></c:if>
            <label for="rb1-g${widgetId}" >${empty g1 ? dash : g1}  </label>
        </td>
        <td width="${requestScope.widthStartDate}" class="td-ae1 tr-ae1-c5 col-startDate-${widgetId} ${cssClass}" >
            <c:if test="${sd1 ne sd2}"><input id="rb1-st${widgetId}" type="radio" name="st${widgetId}" value="${sd1}"  ${sd3 eq sd1? 'checked="yes"':''}  onclick="updateCellValue('${widgetId}','${ae2.id}','startDate', this)" /></c:if>
            <label for="rb1-st${widgetId}" >${empty sd1? dash:sd1} </label>
        </td>
        <td width="${requestScope.widthEndDate}" class="td-ae1  tr-ae1-c6 col-endDate-${widgetId} ${cssClass}" >
            <c:if test="${ed1 ne ed2}"><input id="rb1-ed${widgetId}" type="radio" name="en${widgetId}" value="${ed1}"  ${ed3 eq ed1? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','${ae2.id}','endDate', this)" /></c:if>
            <label for="rb1-ed${widgetId}" >${empty ed1 ? dash : ed1}</label>
        </td>
        <td width="${requestScope.widthVerbatim}" class="td-ae1  tr-ae1-c7 col-v-${widgetId} ${cssClass}" >
            <c:if test="${v1 ne v2}"><input  id="rb1-v${widgetId}" type="radio" name="v${widgetId}" value="${v1}"  ${v3 eq v1? 'checked="yes"':''}  onclick="updateCellValue('${widgetId}','${ae2.id}','verbatim', this)" /></c:if>
            <label for="rb1-v${widgetId}">${empty v1 ? dash : v1}</label>
        </td>
        <td width="${requestScope.widthWhySerious}" class="td-ae1  tr-ae1-c8 col-whySerious-${widgetId} ${cssClass}" >
            <c:if test="${s1 ne s2}"><input id="rb1-s${widgetId}" type="radio" name="s${widgetId}" value="${s1}"  ${s3 eq s1? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','${ae2.id}','whySerious', this)" /></c:if>
            <label for="rb1-s${widgetId}">${empty s1? dash : s1}</label>
        </td>
        <td width="${requestScope.widthAttribution}" class="td-ae1  tr-ae1-c9  col-attribution-${widgetId} ${cssClass}" >
            <c:if test="${a1 ne a2}"><input id="rb1-a${widgetId}" type="radio" name="a${widgetId}" value="${a1}"  ${a3 eq a1? 'checked="yes"':''}    onclick="updateCellValue('${widgetId}','${ae2.id}','attribution', this)" /></c:if>
            <label for="rb1-a${widgetId}">${empty a1? dash : a1}</label>
        </td>

    </tr>
    <tr id="aemrg-${widgetId}-tr-eae" class="tr-ae2 ${cssClass}">
        <td width="${requestScope.widthSource}" class="td-ae2  tr-ae2-c1 ${cssClass}" >${ae2.source}</td>
        <td width="${requestScope.widthId}" class="td-ae2   tr-ae2-c2 ${cssClass}" >${empty ae2.externalID ? dash : ae2.externalID }</td>
        <td width="${requestScope.widthTerm}" class="td-ae2   tr-ae2-c3 col-term-${widgetId} ${cssClass}" >
            <c:if test="${t1 ne t2}">
                <input type="hidden" id="t2${widgetId}-id" name="id" value="${ae2.term.id}" />
                <input type="hidden" id="t2${widgetId}-code" name="code" value="${ae2.term.code}" />
                <input type="hidden" id="t2${widgetId}-name" name="name" value="${ae2.term.name}" />
                <input type="hidden" id="t2${widgetId}-otherSpecify" name="otherSpecify" value="${ae2.term.otherSpecify}" />
                <input id="rb2-t${widgetId}"  type="radio" name="t${widgetId}" value="${t2}"  ${t3 eq t2? 'checked="yes"':''}  onclick="updateCellValue('${widgetId}','${ae2.id}','term', this)" />
            </c:if>
            <label for="rb2-t${widgetId}" >${empty t2 ? dash : t2}</label>

        </td>
        <td width="${requestScope.widthGrade}" class="td-ae2   tr-ae2-c4 col-grade-${widgetId} ${cssClass}" >
            <c:if test="${g1 ne g2}"><input  id="rb2-g${widgetId}" type="radio" name="g${widgetId}" value="${g2}" ${g3 eq g2? 'checked="yes"':''}  onclick="updateCellValue('${widgetId}','${ae2.id}','grade', this)" /></c:if>
            <label for="rb2-g${widgetId}" >${empty g2 ? dash:g2}</label>
        </td>
        <td width="${requestScope.widthStartDate}" class="td-ae2   tr-ae2-c5 col-startDate-${widgetId} ${cssClass}" >
            <c:if test="${sd1 ne sd2}"><input id="rb2-sd${widgetId}" type="radio" name="st${widgetId}" value="${sd2}"  ${sd3 eq sd2? 'checked="yes"':''}    onclick="updateCellValue('${widgetId}','${ae2.id}','startDate', this)"  /></c:if>
            <label for="rb2-sd${widgetId}" >${empty sd2 ? dash : sd2}</label>
        </td>
        <td width="${requestScope.widthEndDate}" class="td-ae2   tr-ae2-c6 col-endDate-${widgetId} ${cssClass}" >
            <c:if test="${ed1 ne ed2}"><input id="rb2-ed${widgetId}" type="radio" name="en${widgetId}" value="${ed2}"  ${ed3 eq ed2? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','${ae2.id}','endDate', this)" /></c:if>
            <label for="rb2-ed${widgetId}" >${empty ed2 ? dash : ed2}</label>
        </td>
        <td width="${requestScope.widthVerbatim}" class="td-ae2   tr-ae2-c7 col-v-${widgetId} ${cssClass}" >
            <c:if test="${v1 ne v2}"><input id="rb2-v${widgetId}" type="radio" name="v${widgetId}" value="${v2}"  ${v3 eq v2? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','${ae2.id}','verbatim', this)"  /></c:if>
            <label for="rb2-v${widgetId}" >${empty v2 ? dash : v2}</label>
        </td>
        <td width="${requestScope.widthWhySerious}" class="td-ae2   tr-ae2-c8 col-whySerious-${widgetId} ${cssClass}" >
            <c:if test="${s1 ne s2}"><input id="rb2-s${widgetId}" type="radio" name="s${widgetId}" value="${s2}" ${s3 eq s2 ? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','${ae2.id}','whySerious', this)"  /></c:if>
            <label for="rb2-s${widgetId}" >${empty s2 ? dash : s2}</label>
        </td>
        <td width="${requestScope.widthAttribution}" class="td-ae2   tr-ae2-c9 col-attribution-${widgetId} ${cssClass}" >
            <c:if test="${a1 ne a2}"><input id="rb2-a${widgetId}" type="radio" name="a${widgetId}" value="${a2}" ${a3 eq a2 ? 'checked="yes"':''}   onclick="updateCellValue('${widgetId}','${ae2.id}','attribution', this)"  /></c:if>
            <label for="rb2-a${widgetId}" >${empty a2 ? dash : a2}</label>
        </td>

    </tr>
    <tr class="tr-filler tr-filler1">
        <td colspan="9" class="fillerRow">
            <hr align="center" width="96%" size="1" color="blue">
        </td>
    </tr>
    <tr id="aemrg-${widgetId}-tr-mae"  class="tr-ae3 ${cssClass}" >
        <td width="${requestScope.widthSource}" class="td-ae3  tr-ae3-c1 ${cssClass}" >&nbsp;&nbsp;&nbsp;
            <form:hidden id="term${widgetId}-id" path="mergeMap[${mmkey}].term.id" />
            <form:hidden id="term${widgetId}-name" path="mergeMap[${mmkey}].term.name" />
            <form:hidden id="term${widgetId}-code" path="mergeMap[${mmkey}].term.code" />
            <form:hidden id="term${widgetId}-otherSpecify" path="mergeMap[${mmkey}].term.otherSpecify" />
            <form:hidden id="grade${widgetId}" path="mergeMap[${mmkey}].grade" />
            <form:hidden id="verbatim${widgetId}" path="mergeMap[${mmkey}].verbatim" />
            <form:hidden id="attribution${widgetId}" path="mergeMap[${mmkey}].attribution" />
            <form:hidden id="startDate${widgetId}" path="mergeMap[${mmkey}].startDate" />
            <form:hidden id="endDate${widgetId}" path="mergeMap[${mmkey}].endDate" />
            <form:hidden id="whySerious${widgetId}" path="mergeMap[${mmkey}].whySerious" />
        </td>
        <td width="${requestScope.widthId}"  class="td-ae3 tr-ae3-c2 ${cssClass}"  >${empty ae2.externalID ? dash : ae2.externalID }</td>
        <td width="${requestScope.widthTerm}" id="td-term${widgetId}" class="td-ae3 col-term-${widgetId} ${cssClass}" >
            ${( (t1 eq t3) || (t2 eq t3)) ? (empty t3 ? dash : t3) : (empty t1 and empty t2) ? dash : '<div id="col-term-${widgetId}" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthGrade}" id="td-grade${widgetId}"  class="td-ae3  tr-ae3-c3 col-grade-${widgetId} ${cssClass}" >
                ${( (g1 eq g3) || (g2 eq g3)) ? (empty g3 ? dash : g3) : (empty g1 and empty g2) ? dash : '<div id="col-grade-${widgetId}" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthStartDate}" id="td-startDate${widgetId}" class="td-ae3  tr-ae3-c4 col-startDate-${widgetId} ${cssClass}" >
                ${( (sd1 eq sd3) || (sd2 eq sd3)) ? (empty sd3 ? dash : sd3) : (empty sd1 and empty sd2) ? dash : '<div id="col-startDate-${widgetId}" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthEndDate}" id="td-endDate${widgetId}"  class="td-ae3  tr-ae3-c5 col-endDate-${widgetId} ${cssClass}" >
                ${( (ed1 eq ed3) || (ed2 eq ed3)) ? (empty ed3 ? dash: ed3) : (empty ed1 and empty ed2) ? dash : '<div id="col-endDate-${widgetId}" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthVerbatim}" id="td-verbatim${widgetId}" class="td-ae3  tr-ae3-c6 col-v-${widgetId} ${cssClass}" >
                ${( (v1 eq v3) || (v2 eq v3)) ? (empty v3 ? dash : v3) : (empty v1 and empty v2) ? dash : '<div id="col-v-${widgetId}" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthWhySerious}" id="td-whySerious${widgetId}"  class="td-ae3  tr-ae3-c7 col-whySerious-${widgetId} ${cssClass}" >
                ${( (s1 eq s3) || (s2 eq s3)) ? (empty s3 ? dash : s3) :  (empty s1 and empty s2) ? dash : '<div id="col-whySerious-${widgetId}" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthAttribution}" id="td-attribution${widgetId}" class="td-ae3  tr-ae3-c8 col-attribution-${widgetId} ${cssClass}" >
                ${( (a1 eq a3) || (a2 eq a3)) ? (empty a3 ? dash : a3) : (empty a1 and empty a2) ? dash : '<div id="col-attribution-${widgetId}" class="selectAbove">Select above </div>'}
        </td>

    </tr>
    <tr class="tr-filler tr-filler2">
        <td colspan="9" class="fillerRow">
            &nbsp;
        </td>
    </tr>