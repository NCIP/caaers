<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="rs" tagdir="/WEB-INF/tags/researchStaff" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>
<%@attribute name="readOnly" type="java.lang.Boolean" %>

<%@attribute name="siteResearchStaff" type="gov.nih.nci.cabig.caaers.domain.SiteResearchStaff" %>

<c:set var="orgName" value="${command.researchStaff.siteResearchStaffs[index].organization}" />
<c:set var="editMode" value="${command.researchStaff.id > 0}" />

<chrome:division id="siteResearchStaff_${index}" collapsable="true" collapsed="false" enableDelete="true" title="&nbsp;${orgName}">

                <c:if test="${!readOnly || empty orgName}">
                        <div class="row">
                            <div class="label">Site:</div>
                            <div class="value">
                                <c:set var="initValue" value="${not empty orgName ? orgName : 'Begin typing here...'}"/>
                                        <ui:autocompleter path="researchStaff.siteResearchStaffs[${index}].organization" initialDisplayValue="${initValue}">
                                            <jsp:attribute name="embededJS"></jsp:attribute>
                                            <jsp:attribute name="populatorJS">
                                                function(autocompleter, text) {
                                                    createStudy.restrictOrganizations(text, function(values) { autocompleter.setChoices(values);})
                                                }
                                            </jsp:attribute>
                                            <jsp:attribute name="selectorJS">
                                                function(obj){
                                                    return obj.name;
                                                }
                                            </jsp:attribute>
                                        </ui:autocompleter>
                        </div>
                    </div>
                </c:if>

<%--
--%>

                <br>
                <caaers:message code="researchstaff.details.contact" var="contacts"/>
                <b>${contacts}</b>

                <div class="row">
                        <div class="leftpanel">
                            <div class="row">
                                <div class="label">Email address:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].emailAddress" cssClass="validate-EMAIL" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label">Phone:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].phoneNumber" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label">Fax:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].faxNumber" readonly="${readOnly}"/></div>
                            </div>
                        </div>
                        <div class="rightpanel">
                            <div class="row">
                                <div class="label">Street:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.street" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label">City:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.city" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label">State:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.state" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label">ZIP:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.zip" cssClass="validate-ZIPCODE" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label">Country:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.country" readonly="${readOnly}"/></div>
                            </div>
                        </div>
                    </div>

    <div class="row">
                <div class="leftpanel">
                    <c:if test="${!readOnly}">
                        <caaers:message code="researchstaff.details.rolesSection" var="roleSectionTitle"/>
                        <chrome:division id="roles-details" title="${roleSectionTitle}  Roles Size:${fn:length(command.researchStaff.siteResearchStaffs[index].siteResearchStaffRoles)}">
                            <table cellpadding="0" cellspacing="0">
                            <c:forEach items="${command.srs[index].rsRoles}" var="role" varStatus="j">
                                <tr>
                                    <td>
                                        <c:if test="${!role.checked}"><ui:checkbox path="srs[${index}].rsRoles[${j.index}].checked" />&nbsp;${allRoles[j.index].name}</c:if>
                                        <c:if test="${role.checked}"><ui:checkbox path="srs[${index}].rsRoles[${j.index}].checked" disabled="true"/>&nbsp;${allRoles[j.index].name}</c:if>
                                    <td>
                                        <c:if test="${role.checked}"><tags:formatDate value="${role.startDate}" />&nbsp;<img src="<c:url value="/images/checkno.gif" />" alt="Deactivate" title="Deactivate"></c:if>
                                        <c:if test="${!role.checked}"><ui:date path="srs[${index}].rsRoles[${j.index}].startDate" /></c:if>
                                <tr>
                            </c:forEach>
                            </table>
                        </chrome:division>
                    </c:if>
                    <c:if test="${readOnly}">
                        <caaers:message code="researchstaff.details.roles" var="roles"/>
                        <chrome:division id="roles-details" title="${roles} Roles Size:${fn:length(command.researchStaff.siteResearchStaffs[index].siteResearchStaffRoles)}">
                            <br>
                            <c:forEach items="${command.srs[index].rsRoles}" var="role" varStatus="j">
                                <c:if test="${role.checked}">&nbsp;&nbsp;<ui:checkbox path="srs[${index}].rsRoles[${j.index}].checked" disabled="true"/>&nbsp;${command.allRoles[j.index].name}</c:if>
                                <c:if test="${role.checked}">&nbsp;&nbsp;<tags:formatDate value="${role.startDate}" /><br></c:if>
                            </c:forEach>
                        </chrome:division>
                    </c:if>
                </div>
                <div class="rightpanel">
                    <caaers:message code="researchstaff.details.studiesSection" var="studiesSectionTitle"/>
                    <chrome:division id="studies-details" title="${studiesSectionTitle}">
                        <div style="overflow:auto; height:150px;">
                            <div id="_studies_${index}">
                                <div class="row">
                                    <div class="label">Associate to all studies:</div>
                                    <div class="value"><ui:checkbox path="researchStaff.siteResearchStaffs[${index}].associateAllStudies"/></div>
                                </div>
                            </div>
                        </div>
                    </chrome:division>
                </div>
    </div>

</chrome:division>
