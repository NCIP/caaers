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
    <c:set var="t1" value="${caaers:escapeJS(ae1.term.displayName)}" />
    <c:set var="t2" value="${caaers:escapeJS(ae2.term.displayName)}" />
    <c:set var="t3" value="${caaers:escapeJS(ae3.term.displayName)}" />

    <c:set var="g1" value="${caaers:escapeJS(ae1.grade)}" />
    <c:set var="g2" value="${caaers:escapeJS(ae2.grade)}" />
    <c:set var="g3" value="${caaers:escapeJS(ae3.grade)}" />

    <c:set var="sd1" value="${ ae1.startDate  }" />
    <c:set var="sd2" value="${  ae2.startDate  }" />
    <c:set var="sd3" value="${  ae3.startDate  }" />

    <c:set var="ed1" value="${  ae1.endDate  }" />
    <c:set var="ed2" value="${  ae2.endDate }" />
    <c:set var="ed3" value="${  ae3.endDate }" />

    <c:set var="v1" value="${caaers:escapeJS(ae1.verbatim)}" />
    <c:set var="v2" value="${caaers:escapeJS(ae2.verbatim)}" />
    <c:set var="v3" value="${caaers:escapeJS(ae3.verbatim)}" />

    <c:set var="s1" value="${caaers:escapeJS(ae1.whySerious)}" />
    <c:set var="s2" value="${caaers:escapeJS(ae2.whySerious)}" />
    <c:set var="s3" value="${caaers:escapeJS(ae3.whySerious)}" />

    <c:set var="a1" value="${caaers:escapeJS(ae1.attribution)}" />
    <c:set var="a2" value="${caaers:escapeJS(ae2.attribution)}" />
    <c:set var="a3" value="${caaers:escapeJS(ae3.attribution)}" />

    <c:set var="mmkey" value="${ae1.id}_${ae2.id}"  />

    <tr id="aemrg-<c:out value="${widgetId}" escapeXml="true"/>-tr-iae" class="tr-ae1 <c:out value="${cssClass}" escapeXml="true"/>">
        <td width="${requestScope.widthSource}" class="td-ae1 tr-ae1-c1 <c:out value="${cssClass}" escapeXml="true"/>" >${ae1.source}</td>
        <td width="${requestScope.widthTerm}" class="td-ae1  tr-ae1-c3  col-term-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${t1 ne t2 ? '' : 'tdGray'}" >
            <c:if test="${t1 ne t2}">
                <input type="hidden" id="t1<c:out value="${widgetId}" escapeXml="true"/>-id" name="id" value="${ae1.term.id}" />
                <input type="hidden" id="t1<c:out value="${widgetId}" escapeXml="true"/>-code" name="code" value="${ae1.term.code}" />
                <input type="hidden" id="t1<c:out value="${widgetId}" escapeXml="true"/>-name" name="name" value="${ae1.term.name}" />
                <input type="hidden" id="t1<c:out value="${widgetId}" escapeXml="true"/>-otherSpecify" name="otherSpecify" value="${ae1.term.otherSpecify}" />
                <input id="rb1-t<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="t<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${t1}" escapeXml="true"/>"  ${t3 eq t1? 'checked="yes"':''}  onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','term', this)" />
            </c:if>
            <label id="rb2-t<c:out value="${widgetId}" escapeXml="true"/>" >${empty t1 ? dash : t1} </label>

        </td>
        <td width="${requestScope.widthGrade}" class="td-ae1  tr-ae1-c4  col-grade-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${g1 ne g2 ? '' : 'tdGray'}" >
            <c:if test="${g1 ne g2}"><input  id="rb1-g<c:out value="${widgetId}" escapeXml="true"/>"  type="radio" name="g<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${g1}" escapeXml="true"/>"  ${g3 eq g1? 'checked="yes"':'' }  onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','grade', this)" /></c:if>
            <label for="rb1-g<c:out value="${widgetId}" escapeXml="true"/>" >${empty g1 ? dash : g1}  </label>
        </td>
        <td width="${requestScope.widthStartDate}" class="td-ae1 tr-ae1-c5 col-startDate-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${sd1 ne sd2 ? '' : 'tdGray'}" >
            <c:if test="${sd1 ne sd2}"><input id="rb1-st<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="st<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${sd1}" escapeXml="true"/>"  ${sd3 eq sd1? 'checked="yes"':''}  onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','startDate', this)" /></c:if>
            <label for="rb1-st<c:out value="${widgetId}" escapeXml="true"/>" >${empty sd1? dash:sd1} </label>
        </td>
        <td width="${requestScope.widthEndDate}" class="td-ae1  tr-ae1-c6 col-endDate-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${ed1 ne ed2 ? '' : 'tdGray'}" >
            <c:if test="${ed1 ne ed2}"><input id="rb1-ed<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="en<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${ed1}" escapeXml="true"/>"  ${ed3 eq ed1? 'checked="yes"':''}   onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','endDate', this)" /></c:if>
            <label for="rb1-ed<c:out value="${widgetId}" escapeXml="true"/>" >${empty ed1 ? dash : ed1}</label>
        </td>
        <td width="${requestScope.widthVerbatim}" class="td-ae1  tr-ae1-c7 col-v-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${v1 ne v2 ? '' : 'tdGray'}" >
            <c:if test="${v1 ne v2}"><input  id="rb1-v<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="v<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${v1}" escapeXml="true"/>"  ${v3 eq v1? 'checked="yes"':''}  onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','verbatim', this)" /></c:if>
            <label for="rb1-v<c:out value="${widgetId}" escapeXml="true"/>">${empty v1 ? dash : v1}</label>
        </td>
        <td width="${requestScope.widthWhySerious}" class="td-ae1  tr-ae1-c8 col-whySerious-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${s1 ne s2 ? '' : 'tdGray'}" >
            <c:if test="${s1 ne s2}"><input id="rb1-s<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="s<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${s1}" escapeXml="true"/>"  ${s3 eq s1? 'checked="yes"':''}   onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','whySerious', this)" /></c:if>
            <label for="rb1-s<c:out value="${widgetId}" escapeXml="true"/>">${empty s1? dash : s1}</label>
        </td>
        <td width="${requestScope.widthAttribution}" class="td-ae1  tr-ae1-c9  col-attribution-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${a1 ne a2 ? '' : 'tdGray'}" >
            <c:if test="${a1 ne a2}"><input id="rb1-a<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="a<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${a1}" escapeXml="true"/>"  ${a3 eq a1? 'checked="yes"':''}    onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','attribution', this)" /></c:if>
            <label for="rb1-a<c:out value="${widgetId}" escapeXml="true"/>">${empty a1? dash : a1}</label>
        </td>

    </tr>
    <tr id="aemrg-<c:out value="${widgetId}" escapeXml="true"/>-tr-eae" class="tr-ae2 <c:out value="${cssClass}" escapeXml="true"/>">
        <td width="${requestScope.widthSource}" class="td-ae2  tr-ae2-c1 <c:out value="${cssClass}" escapeXml="true"/>" >${ae2.source}</td>
        <td width="${requestScope.widthTerm}" class="td-ae2   tr-ae2-c3 col-term-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${t1 ne t2 ? '' : 'tdGray'}" >
            <c:if test="${t1 ne t2}">
                <input type="hidden" id="t2<c:out value="${widgetId}" escapeXml="true"/>-id" name="id" value="${ae2.term.id}" />
                <input type="hidden" id="t2<c:out value="${widgetId}" escapeXml="true"/>-code" name="code" value="${ae2.term.code}" />
                <input type="hidden" id="t2<c:out value="${widgetId}" escapeXml="true"/>-name" name="name" value="${ae2.term.name}" />
                <input type="hidden" id="t2<c:out value="${widgetId}" escapeXml="true"/>-otherSpecify" name="otherSpecify" value="${ae2.term.otherSpecify}" />
                <input id="rb2-t<c:out value="${widgetId}" escapeXml="true"/>"  type="radio" name="t<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${t2}" escapeXml="true"/>"  ${t3 eq t2? 'checked="yes"':''}  onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','term', this)" />
            </c:if>
            <label for="rb2-t<c:out value="${widgetId}" escapeXml="true"/>" >${empty t2 ? dash : t2}</label>

        </td>
        <td width="${requestScope.widthGrade}" class="td-ae2   tr-ae2-c4 col-grade-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${g1 ne g2 ? '' : 'tdGray'}" >
            <c:if test="${g1 ne g2}"><input  id="rb2-g<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="g<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${g2}" escapeXml="true"/>" ${g3 eq g2? 'checked="yes"':''}  onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','grade', this)" /></c:if>
            <label for="rb2-g<c:out value="${widgetId}" escapeXml="true"/>" >${empty g2 ? dash:g2}</label>
        </td>
        <td width="${requestScope.widthStartDate}" class="td-ae2   tr-ae2-c5 col-startDate-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${sd1 ne sd2 ? '' : 'tdGray'}" >
            <c:if test="${sd1 ne sd2}"><input id="rb2-sd<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="st<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${sd2}" escapeXml="true"/>"  ${sd3 eq sd2? 'checked="yes"':''}    onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','startDate', this)"  /></c:if>
            <label for="rb2-sd<c:out value="${widgetId}" escapeXml="true"/>" >${empty sd2 ? dash : sd2}</label>
        </td>
        <td width="${requestScope.widthEndDate}" class="td-ae2   tr-ae2-c6 col-endDate-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${ed1 ne ed2 ? '' : 'tdGray'}" >
            <c:if test="${ed1 ne ed2}"><input id="rb2-ed<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="en<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${ed2}" escapeXml="true"/>"  ${ed3 eq ed2? 'checked="yes"':''}   onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','endDate', this)" /></c:if>
            <label for="rb2-ed<c:out value="${widgetId}" escapeXml="true"/>" >${empty ed2 ? dash : ed2}</label>
        </td>
        <td width="${requestScope.widthVerbatim}" class="td-ae2   tr-ae2-c7 col-v-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${v1 ne v2 ? '' : 'tdGray'}" >
            <c:if test="${v1 ne v2}"><input id="rb2-v<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="v<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${v2}" escapeXml="true"/>"  ${v3 eq v2? 'checked="yes"':''}   onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','verbatim', this)"  /></c:if>
            <label for="rb2-v<c:out value="${widgetId}" escapeXml="true"/>" >${empty v2 ? dash : v2}</label>
        </td>
        <td width="${requestScope.widthWhySerious}" class="td-ae2   tr-ae2-c8 col-whySerious-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${s1 ne s2 ? '' : 'tdGray'}" >
            <c:if test="${s1 ne s2}"><input id="rb2-s<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="s<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${s2}" escapeXml="true"/>" ${s3 eq s2 ? 'checked="yes"':''}   onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','whySerious', this)"  /></c:if>
            <label for="rb2-s<c:out value="${widgetId}" escapeXml="true"/>" >${empty s2 ? dash : s2}</label>
        </td>
        <td width="${requestScope.widthAttribution}" class="td-ae2   tr-ae2-c9 col-attribution-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/> ${a1 ne a2 ? '' : 'tdGray'}" >
            <c:if test="${a1 ne a2}"><input id="rb2-a<c:out value="${widgetId}" escapeXml="true"/>" type="radio" name="a<c:out value="${widgetId}" escapeXml="true"/>" value="<c:out value="${a2}" escapeXml="true"/>" ${a3 eq a2 ? 'checked="yes"':''}   onclick="updateCellValue('<c:out value="${widgetId}" escapeXml="true"/>','${ae2.id}','attribution', this)"  /></c:if>
            <label for="rb2-a<c:out value="${widgetId}" escapeXml="true"/>" >${empty a2 ? dash : a2}</label>
        </td>

    </tr>
    <tr class="tr-filler tr-filler1">
        <td colspan="8" class="fillerRow">
        </td>
    </tr>
    <tr id="aemrg-<c:out value="${widgetId}" escapeXml="true"/>-tr-mae"  class="tr-ae3 <c:out value="${cssClass}" escapeXml="true"/>" >
        <td width="${requestScope.widthSource}" class="td-ae3  tr-ae3-c1 <c:out value="${cssClass}" escapeXml="true"/>" >&nbsp;&nbsp;&nbsp;
            <form:hidden id="term<c:out value="${widgetId}" escapeXml="true"/>-id" path="mergeMap[<c:out value="${mmkey}" escapeXml="true"/>].term.id" />
            <form:hidden id="term<c:out value="${widgetId}" escapeXml="true"/>-name" path="mergeMap[<c:out value="${mmkey}" escapeXml="true"/>].term.name" />
            <form:hidden id="term<c:out value="${widgetId}" escapeXml="true"/>-code" path="mergeMap[<c:out value="${mmkey}" escapeXml="true"/>].term.code" />
            <form:hidden id="term<c:out value="${widgetId}" escapeXml="true"/>-otherSpecify" path="mergeMap[<c:out value="${mmkey}" escapeXml="true"/>].term.otherSpecify" />
            <form:hidden id="grade<c:out value="${widgetId}" escapeXml="true"/>" path="mergeMap[<c:out value="${mmkey}" escapeXml="true"/>].grade" />
            <form:hidden id="verbatim<c:out value="${widgetId}" escapeXml="true"/>" path="mergeMap[<c:out value="${mmkey}" escapeXml="true"/>].verbatim" />
            <form:hidden id="attribution<c:out value="${widgetId}" escapeXml="true"/>" path="mergeMap[<c:out value="${mmkey}" escapeXml="true"/>].attribution" />
            <form:hidden id="startDate<c:out value="${widgetId}" escapeXml="true"/>" path="mergeMap[<c:out value="${mmkey}" escapeXml="true"/>].startDate" />
            <form:hidden id="endDate<c:out value="${widgetId}" escapeXml="true"/>" path="mergeMap[<c:out value="${mmkey}" escapeXml="true"/>].endDate" />
            <form:hidden id="whySerious<c:out value="${widgetId}" escapeXml="true"/>" path="mergeMap[<c:out value="${mmkey}" escapeXml="true"/>].whySerious" />
        </td>
        <td width="${requestScope.widthTerm}" id="td-term<c:out value="${widgetId}" escapeXml="true"/>" class="td-ae3 col-term-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/>" >
            ${( (t1 eq t3) || (t2 eq t3)) ? (empty t3 ? dash : t3) : (empty t1 and empty t2) ? dash : '<div id="col-term-<c:out value="${widgetId}" escapeXml="true"/>" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthGrade}" id="td-grade<c:out value="${widgetId}" escapeXml="true"/>"  class="td-ae3  tr-ae3-c3 col-grade-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/>" >
                ${( (g1 eq g3) || (g2 eq g3)) ? (empty g3 ? dash : g3) : (empty g1 and empty g2) ? dash : '<div id="col-grade-<c:out value="${widgetId}" escapeXml="true"/>" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthStartDate}" id="td-startDate<c:out value="${widgetId}" escapeXml="true"/>" class="td-ae3  tr-ae3-c4 col-startDate-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/>" >
                ${( (sd1 eq sd3) || (sd2 eq sd3)) ? (empty sd3 ? dash : sd3) : (empty sd1 and empty sd2) ? dash : '<div id="col-startDate-<c:out value="${widgetId}" escapeXml="true"/>" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthEndDate}" id="td-endDate<c:out value="${widgetId}" escapeXml="true"/>"  class="td-ae3  tr-ae3-c5 col-endDate-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/>" >
                ${( (ed1 eq ed3) || (ed2 eq ed3)) ? (empty ed3 ? dash: ed3) : (empty ed1 and empty ed2) ? dash : '<div id="col-endDate-<c:out value="${widgetId}" escapeXml="true"/>" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthVerbatim}" id="td-verbatim<c:out value="${widgetId}" escapeXml="true"/>" class="td-ae3  tr-ae3-c6 col-v-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/>" >
                ${( (v1 eq v3) || (v2 eq v3)) ? (empty v3 ? dash : v3) : (empty v1 and empty v2) ? dash : '<div id="col-v-<c:out value="${widgetId}" escapeXml="true"/>" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthWhySerious}" id="td-whySerious<c:out value="${widgetId}" escapeXml="true"/>"  class="td-ae3  tr-ae3-c7 col-whySerious-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/>" >
                ${( (s1 eq s3) || (s2 eq s3)) ? (empty s3 ? dash : s3) :  (empty s1 and empty s2) ? dash : '<div id="col-whySerious-<c:out value="${widgetId}" escapeXml="true"/>" class="selectAbove">Select above </div>'}
        </td>
        <td width="${requestScope.widthAttribution}" id="td-attribution<c:out value="${widgetId}" escapeXml="true"/>" class="td-ae3  tr-ae3-c8 col-attribution-<c:out value="${widgetId}" escapeXml="true"/> <c:out value="${cssClass}" escapeXml="true"/>" >
                ${( (a1 eq a3) || (a2 eq a3)) ? (empty a3 ? dash : a3) : (empty a1 and empty a2) ? dash : '<div id="col-attribution-<c:out value="${widgetId}" escapeXml="true"/>" class="selectAbove">Select above </div>'}
        </td>

    </tr>
    <tr class="tr-filler tr-filler2">
        <td colspan="8" class="fillerRow">
            <hr class="hrfiller"  />
        </td>
    </tr>