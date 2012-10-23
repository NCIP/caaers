<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="ae1" required="true" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
<%@attribute name="rejected" required="true" type="java.lang.Boolean" %>
<%@attribute name="external" required="true" type="java.lang.Boolean" %>
<%@attribute name="cssClass" required="false"  %>

<c:set var="widgetId" value="${ae1.id}" />
<c:set var= "dash" value="--" />
<c:set var="t1" value="${ empty ae1.term.displayName ? dash : ae1.term.displayName }" />
<c:set var="src1" value="${ empty ae1.source ? dash : ae1.source }" />
<c:set var="e1" value="${ empty ae1.externalID ? dash : ae1.externalID }" />
<c:set var="g1" value="${empty ae1.grade ? dash : ae1.grade }" />
<c:set var="sd1" value="${empty ae1.startDate ? dash : ae1.startDate }" />
<c:set var="ed1" value="${empty  ae1.endDate  ? dash : ae1.endDate}" />
<c:set var="v1" value="${empty  ae1.verbatim ? dash : ae1.verbatim }" />
<c:set var="s1" value="${empty  ae1.whySerious ? dash : ae1.whySerious}" />
<c:set var="a1" value="${empty  ae1.attribution ? dash :  ae1.attribution }" />

<c:if test="${rejected}">
    <script type="text/javascript">
        <c:if test="${external}">AE.eRejected.push(${ae1.id});</c:if>
        <c:if test="${not external}">AE.iRejected.push(${ae1.id})</c:if>
    </script>
</c:if>

<tr id="${external ? 'eae' : 'iae'}-${widgetId}-tr" class="${rejected ? 'ae-rejected' : ''} ${cssClass}">
    <td width="${requestScope.widthTerm}"  class="${rejected ? 'ae-rejected' : ''} ${cssClass}"> ${t1}</td>
    <td width="${requestScope.widthGrade}"  class="${rejected ? 'ae-rejected' : ''} ${cssClass}">${g1}</td>
    <td width="${requestScope.widthStartDate}"  class="${rejected ? 'ae-rejected' : ''} ${cssClass}">${sd1}</td>
    <td width="${requestScope.widthEndDate}"  class="${rejected ? 'ae-rejected' : ''} ${cssClass}">${ed1}</td>
    <td width="${requestScope.widthVerbatim}"  class="${rejected ? 'ae-rejected' : ''} ${cssClass}">${v1}</td>
    <td width="${requestScope.widthWhySerious}"  class="${rejected ? 'ae-rejected' : ''} ${cssClass}">${s1}</td>
    <td width="${requestScope.widthAttribution}"  class="${rejected ? 'ae-rejected' : ''} ${cssClass}">${a1}</td>
    <td width="${requestScope.widthActions}" class=" actionBtns ${rejected ? 'ae-rejected' : ''} ${cssClass}">
        <div id="${external ? 'eae' : 'iae'}-${widgetId}-div-reject" style="${rejected ? 'display:none;' : ''}" class="wgtBtnDiv">
            <tags:button  id="${external ? 'eae' : 'iae'}-${widgetId}-btn-reject" value="Reject" color="red" size="small" cssClass="wgtBtn wgtBtnReject" type="button"
                          onclick="rejectAE(${widgetId} , ${external})" />
        </div>
        <div id="${external ? 'eae' : 'iae'}-${widgetId}-div-unreject" style="${rejected ?  '' : 'display:none;'}" class="wgtBtnDiv">
            <tags:button  id="${external ? 'eae' : 'iae'}-${widgetId}-btn-unreject" value="Unreject" color="green" size="small" cssClass="wgtBtn wgtBtnUnreject" type="button"
                          onclick="unrejectAE(${widgetId} , ${external})" />
        </div>

    </td>
</tr>
