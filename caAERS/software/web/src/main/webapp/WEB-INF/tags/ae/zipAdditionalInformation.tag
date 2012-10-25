<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="additionalInformationId" required="true" %>
<div class="row">
    <div class="rightpanel">
        <a id="additionalInformationDocumentZipDownload" style="float: right"
           target="_blank"
           href="additionalInformationDocumentZipDownload?additionalInformationId=${additionalInformationId}"
           class="submitter fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all">
            <spring:message code="download.all.attachments"/>
            </a>
    </div>
</div>