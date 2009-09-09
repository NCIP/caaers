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
    <jsp:attribute name="titleFragment">
        <c:if test="${not empty orgName}">
            <c:if test="${editMode}">
                <span style="margin-left:100px;">Active date:</span> <tags:formatDate value="${command.researchStaff.siteResearchStaffs[index].activeDate}"/>&nbsp;
                    <c:if test="${!readOnly}">
                        <c:if test="${command.researchStaff.siteResearchStaffs[index].active}">
                            <tags:button type="button"
                                         color="${true ? 'red' : 'green'}"
                                         cssClass=""
                                         value="${true ? 'Deactivate' : 'Activate'}"
                                         size="small"
                                         onclick="${true ? 'de' : ''}activate(${command.researchStaff.id}, ${command.researchStaff.siteResearchStaffs[index].id}, '${role.roleCode}', ${index}, -1)"
                                         icon="${true ? 'x' : 'check'}"/>
                        </c:if>
                    </c:if>
                </c:if>
        </c:if>
    </jsp:attribute>

    <jsp:body>
                <c:if test="${!readOnly && empty orgName}">
                        <div class="row">
                            <div class="label"><ui:label path="researchStaff.siteResearchStaffs[${index}].organization" labelProperty="organization" text=""/></div>
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
                                <div class="label"><ui:label path="researchStaff.siteResearchStaffs[${index}].emailAddress" labelProperty="emailAddress" text=""/></div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].emailAddress" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="researchStaff.siteResearchStaffs[${index}].phoneNumber" labelProperty="phoneNumber" text=""/></div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].phoneNumber" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="researchStaff.siteResearchStaffs[${index}].faxNumber" labelProperty="faxNumber" text=""/></div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].faxNumber" readonly="${readOnly}"/></div>
                            </div>
                        </div>
                        <div class="rightpanel">
                            <div class="row">
                                <div class="label"><ui:label path="researchStaff.siteResearchStaffs[${index}].address.street" labelProperty="street" text=""/></div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.street" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="researchStaff.siteResearchStaffs[${index}].address.city" labelProperty="city" text=""/></div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.city" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="researchStaff.siteResearchStaffs[${index}].address.state" labelProperty="state" text=""/></div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.state" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="researchStaff.siteResearchStaffs[${index}].address.zip" labelProperty="zip" text=""/></div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.zip" cssClass="validate-ZIPCODE" readonly="${readOnly}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="researchStaff.siteResearchStaffs[${index}].address.country" labelProperty="country" text=""/></div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.country" readonly="${readOnly}"/></div>
                            </div>
                        </div>
                    </div>

    <div class="row">

<%--
        <caaers:message code="researchstaff.details.studiesSection" var="studiesSectionTitle"/>
        <chrome:division id="studies-details" title="${studiesSectionTitle}">
            <div>
                <div id="_studies_${index}">
                    <div class="row">
                        <div class="label">Associate to all studies:</div>
                        <div class="value">
                            <ui:checkbox path="researchStaff.siteResearchStaffs[${index}].associateAllStudies" disabled="${readOnly || command.researchStaff.siteResearchStaffs[index].associateAllStudies}"/>
                        </div>
                    </div>
                </div>
            </div>
        </chrome:division>
--%>

                <div>
                    <c:if test="${!readOnly}">
                        <caaers:message code="researchstaff.details.rolesSection" var="roleSectionTitle"/>
                        <chrome:division id="roles-details" title="${roleSectionTitle}">
                            <br>
                            Associate to all studies: <ui:checkbox path="researchStaff.siteResearchStaffs[${index}].associateAllStudies" disabled="${readOnly || command.researchStaff.siteResearchStaffs[index].associateAllStudies}"/>  
                            <tags:table bgColor="#cccccc" contentID="Role_${command.researchStaff.id}_${command.researchStaff.siteResearchStaffs[index].id}">
                            <table cellpadding="3" cellspacing="1" width="100%">
                                <tr bgcolor="#eeeeee">
                                <th>Role name
                                <th>Start date
                                <c:if test="${editMode}">
                                    <th>End date
                                    <th>
                                </c:if>

                            <c:forEach items="${command.siteResearchStaffCommandHelper[index].rsRoles}" var="role" varStatus="j">
                                <c:set var="isActive" value="${command.siteResearchStaffCommandHelper[index].rsRoles[j.index].active}" />
                                <tr bgcolor="white">
                                    <td>
                                        <c:if test="${!role.checked}"><ui:checkbox path="siteResearchStaffCommandHelper[${index}].rsRoles[${j.index}].checked" onclick="applyRole(${index}, ${j.index})"/>&nbsp;${allRoles[j.index].name}</c:if>
                                        <c:if test="${role.checked}"><ui:checkbox path="siteResearchStaffCommandHelper[${index}].rsRoles[${j.index}].checked" disabled="true"/>&nbsp;${allRoles[j.index].name}</c:if>
                                    <td>
                                        <ui:date path="siteResearchStaffCommandHelper[${index}].rsRoles[${j.index}].startDate" cssClass="CSSDate SiteResearchStaffRoleStartDateCSS${index}" />
                                        <c:if test="${!role.checked}">
                                            <script type="text/javascript">
                                                $('siteResearchStaffCommandHelper[${index}].rsRoles[${j.index}].startDate').value = '';
                                            </script>
                                        </c:if>
                                <c:if test="${editMode}">
                                    <td><ui:date path="siteResearchStaffCommandHelper[${index}].rsRoles[${j.index}].endDate" cssClass="CSSDate ${role.checked ? 'CSSEndDate SiteResearchStaffRoleEndDateCSS' : ''}${role.checked ? index : ''}" />
                                    <td>
                                        <c:if test="${role.checked}">
                                                <tags:button type="button" 
                                                             color="${isActive ? 'red' : 'green'}"
                                                             cssClass=""
                                                             value="${isActive ? 'Deactivate' : 'Activate'}"
                                                             size="small"
                                                             onclick="${isActive ? 'de' : ''}activate(${command.researchStaff.id}, ${command.researchStaff.siteResearchStaffs[index].id}, '${role.roleCode}', ${index}, ${j.index})"
                                                             icon="${isActive ? 'x' : 'check'}"/>
                                        </c:if>
                                </c:if>
                                </tr>
                            </c:forEach>
                            </table>
                            </tags:table>
                        </chrome:division>
                    </c:if>
                    <c:if test="${readOnly}">
                        <caaers:message code="researchstaff.details.roles" var="roles"/>
                        <chrome:division id="roles-details" title="${roles}">
                            <br>
                            <table cellpadding="2" cellspacing="0">
                                <tr>
                                <th>Role name
                                <th>Start date
                                <th>End date
                                <c:forEach items="${command.siteResearchStaffCommandHelper[index].rsRoles}" var="role" varStatus="j">
                                        <c:if test="${role.checked}">
                                            <tr>
                                                <td style="border-bottom:1px #eeeeee solid;"><c:if test="${role.checked}">&nbsp;&nbsp;${command.allRoles[j.index].name}</c:if>
                                                <td style="border-bottom:1px #eeeeee solid;"><c:if test="${role.checked}">&nbsp;&nbsp;<tags:formatDate value="${role.startDate}" /><br></c:if>
                                                <td style="border-bottom:1px #eeeeee solid;"><c:if test="${role.checked}">&nbsp;&nbsp;<tags:formatDate value="${role.endDate}" /><br></c:if>
                                        </c:if>
                                </c:forEach>
                            </table>
                        </chrome:division>
                    </c:if>
                </div>
    </div>
    </jsp:body>
</chrome:division>
