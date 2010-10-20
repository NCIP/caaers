<%@attribute name="index" type="java.lang.Integer" required="true" %>
<%@attribute name="sd" type="gov.nih.nci.cabig.caaers.domain.StudyDevice" required="true" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<chrome:division collapsable="true" collapsed="${collapsed}" id="StudyDevice_${sd.id}_${index}" title="Study Device #${index}" enableDelete="true" deleteParams="'deleteStudyAgent', '${index}'">
<c:if test="${empty empty sd.device}">
    <div class="row">
        <div class="label">Device</div>
        <div class="value">
<%--
            <ui:autocompleter path="command.study.studyDevices[${index}].device" initialDisplayValue="..." size="50" title="Study Device">
                          <jsp:attribute name="populatorJS">
                              function(autocompleter, text) {
                              }
                          </jsp:attribute>
                          <jsp:attribute name="selectorJS">
                              function (obj) {
                                  return "name";
                              }
                          </jsp:attribute>
                          <jsp:attribute name="optionsJS"> {
                            	afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                            	}
                            }
                         </jsp:attribute>
                      </ui:autocompleter>
--%>
        </div>
    </div>
    <div class="row">
        <div class="label">Common name</div>
        <div class="value"><ui:text path="study.studyDevices[${index}].device.commonName" size="40"/></div>
    </div>
    <div class="row">
        <div class="label">Brand name</div>
        <div class="value"><ui:text path="study.studyDevices[${index}].device.brandName" size="40"/></div>
    </div>
    <div class="row">
        <div class="label">Device type</div>
        <div class="value"><ui:text path="study.studyDevices[${index}].device.type" size="40"/></div>
    </div>
</c:if>
<%--
<c:if test="${not empty sd.id}">
    <div class="row">
        <div class="label">Other common name</div>
        <div class="value"><ui:text path="study.studyDevices[${index}].otherCommonName" size="40"/></div>
    </div>
    <div class="row">
        <div class="label">Other brand name</div>
        <div class="value"><ui:text path="study.studyDevices[${index}].otherBrandName" size="40"/></div>
    </div>
    <div class="row">
        <div class="label">Other device type</div>
        <div class="value"><ui:text path="study.studyDevices[${index}].otherDeviceType" size="40"/></div>
    </div>
</c:if>    
--%>

<div class="row">
    <div class="label">Deleted</div>
    <div class="value">${sd.retired}</div>
</div>
</chrome:division>