<%@ attribute name="canDeleteFiles" type="java.lang.Boolean" %>
<%@ attribute name="showAttachmentsCount" type="java.lang.Boolean" %>
<%@ attribute name="fileType" %>
<%@ attribute name="listElementIndex" %>
<%@ attribute name="fileUploadButtonId" %>
<%@ attribute name="documents" required="true" type="java.util.HashMap" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="show" type="java.lang.Boolean" %>

<%@attribute name="inputField" fragment="true" %>
<%@attribute name="fileUpload" type="java.lang.Boolean" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${show || not empty  documents[property]}">
    <c:if test="${showAttachmentsCount}">
        <c:set var="attachments" value="${fn:length(documents[property])>1?'attachments':'attachment'}"/>
        <c:set var="attachmentLabel" value="(${fn:length(documents[property])} ${attachments} )"/>

    </c:if>
    <c:if test="${!showAttachmentsCount}">
        <c:set var="attachmentCount" value=""/>

    </c:if>


    <chrome:division title="${title} ${attachmentLabel}"
                     cssClass="lab" id="${property}" collapsable="true"
                     collapsed="true">


        <jsp:body>

            <c:if test="${not empty inputField}">
                <jsp:invoke fragment="inputField"/>
            </c:if>


            <div class="row">
                <div class="label">Attachments</div>
                <div class="value">
                    <table border="0" cellpadding="0" cellspacing="0" class="tablecontent" width="99%"
                           id="${listElementIndex}_files">
                        <tr class="taskTitleRow">
                            <th nowrap><spring:message code="file.name"  /></th>
                            <th><spring:message code="file.size"  /></th>
                            <c:if test="${canDeleteFiles}">
                                <th><spring:message code="file.delete"/></th>
                            </c:if>
                        </tr>
                        <c:forEach
                                items="${documents[property]}"
                                var="additionalInformationDocument" varStatus="index">


                            <c:set var="ALT" value="${index.count % 2 == 0 ? 'alt' : ''}"></c:set>
                            <tr class="results" id="document_${additionalInformationDocument.id}">
                                <td valign="top"><a target="_blank"
                                                    href="additionalInformationDocumentDownload?fileId=${additionalInformationDocument.fileId}">
                                        ${additionalInformationDocument.originalFileName}
                                </a>
                                </td>
                                <td valign="top">${additionalInformationDocument.byteCountToDisplaySize}</td>

                                <c:if test="${canDeleteFiles}">
                                    <td valign="top">
                                        <a href="javascript:deleteDocument('${additionalInformationDocument.fileId}','document_${additionalInformationDocument.id}');">
                                            <img src='<c:url value="/images/buttons/button_icons/small/x_icon_small.png" />'
                                                 alt="Delete attachment">
                                        </a>

                                    </td>
                                </c:if>

                            </tr>


                        </c:forEach>


                        <c:if test="${fn:length(documents[property]) == 0}">
                            <tr id="${listElementIndex}_no_attachment">
                                <td colspan="5"><spring:message code="no.attachments"/></td>
                            </tr>
                        </c:if>
                        <tr id="${listElementIndex}_files_item" style="display: none"></tr>
                    </table>
                </div>
            </div>
            <c:if test="${not empty fileUpload}">
                <div class="row">
                    <div class="value">
                        <input type="hidden" id="${listElementIndex}_file_type"
                               value="${fileType}">

                        <div id="${listElementIndex}" class="file_upload_form">
                            <span id="${listElementIndex}_button"
                                  class="qq-upload-button submitter fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all">
                                <spring:message code="upload.attachments"/>
                                </span>

                        </div>
                    </div>
                </div>

            </c:if>

        </jsp:body>
    </chrome:division>

</c:if>