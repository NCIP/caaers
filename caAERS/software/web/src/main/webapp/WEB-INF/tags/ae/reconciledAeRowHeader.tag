<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@attribute name="showExternalID" type="java.lang.Boolean" %>

<c:set var="widthId" value="2%" scope="request" />
<c:set var="widthTerm" value="22%" scope="request" />
<c:set var="widthGrade" value="14%" scope="request" />
<c:set var="widthStartDate" value="6%" scope="request" />
<c:set var="widthEndDate" value="6%" scope="request" />
<c:set var="widthVerbatim" value="21%" scope="request" />
<c:set var="widthWhySerious" value="${showExternalID ? '10%' : '12%'}" scope="request" />
<c:set var="widthAttribution" value="9%" scope="request" />
<c:set var="widthActions" value="10%" scope="request" />

 <thead>
    <tr class="label" align="center">
        <c:if test="${showExternalID}">
            <td class="tableHeader" width="${widthId}">ID</td>
        </c:if>

        <td class="tableHeader" width="${widthTerm}"> Term</td>
        <td class="tableHeader" width="${widthGrade}">Grade</td>
        <td class="tableHeader" width="${widthStartDate}">Start</td>
        <td class="tableHeader" width="${widthEndDate}">End</td>
        <td class="tableHeader" width="${widthVerbatim}">Verbatim</td>
        <td class="tableHeader" width="${widthWhySerious}">Serious?</td>
        <td class="tableHeader" width="${widthAttribution}">Attribution</td>
    </tr>
 </thead>
