<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<tags:noform>

    <ec:table items="command.studies" var="study"
                action="${pageContext.request.contextPath}/pages/newParticipant"
                imagePath="${pageContext.request.contextPath}/images/table/*.gif"
                filterable="false"
                showPagination="false" form="command"
                cellspacing="0" cellpadding="2" border="0" width="100%" style=""
                styleClass=""
                autoIncludeParameters="false" >
        <ec:row highlightRow="true">
            <ec:column property="transient0" style="width:20px" filterable="false" sortable="false" title="&nbsp;">
                <form:radiobutton path="study" value="${study.id}" onclick="if ($('ids')) $('ids').show();"/>
            </ec:column>
            <ec:column property="primaryIdentifier" title="Primary ID" />
            <ec:column property="shortTitle" title="Short Title" />
            <ec:column property="primarySponsorCode" title="Funding Sponsor" >
										<c:if test ="${study.primaryFundingSponsorOrganization.externalId != null}">
											<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>
										</c:if>
										${study.primarySponsorCode} 
            </ec:column>
            <ec:column property="phaseCode" title="Phase" />
            <ec:column property="status" title="Status" />
        </ec:row>
    </ec:table>
    <input type="hidden" name="_action" value="" />

    </tags:noform>

<%--
<h3><c:out value="Found Items: ${fn:length(command.studies)}" /></h3>

<c:choose>
    <c:when test="${fn:length(command.studies) > 0}">
        <div class="eXtremeTable" id="_table_div">
        <table width="100%" cellspacing="0" cellpadding="4" border="0" class="tableRegion" id="_table">
            <tr>
                <td class="tableHeader"></td>
                <td class="tableHeader">Primary ID</td>
                <td class="tableHeader">Short Title</td>
                <td class="tableHeader">Funding Sponsor</td>
                <td class="tableHeader">Phase</td>
                <td class="tableHeader">Status</td>
            </tr>
            <c:forEach items="${command.studies}" var="study" varStatus="i">
                <c:set var="row" value="even" />
                <c:if test="${i.count % 2 == 1}"><c:set var="row" value="odd" /></c:if>

            <tr class="${row}" onmouseover="this.className='highlight'" onmouseout="this.className='${row}'">
                <td align="center"><input type="radio" id="studySiteArray${i.count}" name="studySiteArray" value="${study.id}"></td>
                <td>${study.primaryIdentifier}</td>
                <td>${study.shortTitle}</td>
                <td>${study.primarySponsorCode}</td>
                <td>${study.phaseCode}</td>
                <td>${study.status}</td>
            </tr>
            </c:forEach>
        </table>
        </div>
    </c:when>

    <c:otherwise></c:otherwise>
</c:choose>
--%>
