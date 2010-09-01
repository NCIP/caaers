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
                <form:radiobutton path="study" value="${study.id}" onclick="$('selectedStudyID').value = 123; if ($('ids')) $('ids').show();"/>
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
