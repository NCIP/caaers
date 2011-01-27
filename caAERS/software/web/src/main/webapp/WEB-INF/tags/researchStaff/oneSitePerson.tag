<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="rs" tagdir="/WEB-INF/tags/researchStaff" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>
<%@attribute name="readOnly" type="java.lang.Boolean" %>

<%@attribute name="siteResearchStaff" type="gov.nih.nci.cabig.caaers.domain.SiteResearchStaff" %>

<c:set var="orgName" value="${command.sitePersonnel[index].organization}" />
<c:set var="editMode" value="${command.person.id > 0}" />

<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:CREATE" var="hasRSCreate"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole:READ" var="hasSRSRRead"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole:UPDATE" var="hasSRSRUpdate"/>

<chrome:division id="sitePerson_${index}" collapsable="true" collapsed="false" enableDelete="${command.sitePersonnel[index].id== null && !readOnly}" title="&nbsp;${orgName}" deleteParams="'removeSitePerson', ${index}">
    <jsp:body>
                <c:if test="${hasRSCreate}">
                <c:if test="${!readOnly && empty orgName}">
                        <div class="row">
                            <div class="label"><ui:label path="sitePersonnel[${index}].organization" labelProperty="organization" text="" required="true"/></div>
                            <div class="value">
                                <c:set var="initValue" value="${not empty orgName ? orgName : 'Begin typing here...'}"/>
                                        <ui:autocompleter path="sitePersonnel[${index}].organization" initialDisplayValue="${initValue}" required="true" title="Organization name">
                                            <jsp:attribute name="embededJS"></jsp:attribute>
                                            <jsp:attribute name="populatorJS">
                                                function(autocompleter, text) {
                                                    createStudy.restrictOrganizations(text, false, function(values) { autocompleter.setChoices(values);})
                                                }
                                            </jsp:attribute>
                                            <jsp:attribute name="selectorJS">
                                                function(organization){
                                                    var image;
                                                    if(organization.externalId != null){
                                                              image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>&nbsp;';
                                                    } else {
                                                              image = '';
                                                    }
                                                    return image + organization.name + " (" + organization.nciInstituteCode + ")";
                                                }
                                            </jsp:attribute>
                                        </ui:autocompleter>
                        </div>
                    </div>
                </c:if>
                </c:if>

                <caaers:message code="researchstaff.details.contact" var="contacts"/>
                <b>${contacts}</b>
                <div class="row">
                        <div class="leftpanel">
                            <div class="row">
                                <div class="label"><ui:label path="sitePersonnel[${index}].emailAddress" labelProperty="emailAddress" text=""/></div>
                                <div class="value"><ui:text path="sitePersonnel[${index}].emailAddress" readonly="${readOnly || !hasRSCreate}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="sitePersonnel[${index}].phoneNumber" labelProperty="phoneNumber" text=""/></div>
                                <div class="value"><ui:text path="sitePersonnel[${index}].phoneNumber" readonly="${readOnly || !hasRSCreate}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="sitePersonnel[${index}].faxNumber" labelProperty="faxNumber" text=""/></div>
                                <div class="value"><ui:text path="sitePersonnel[${index}].faxNumber" readonly="${readOnly || !hasRSCreate}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="sitePersonnel[${index}].startDate" labelProperty="start.date" text=""/></div>
                                <div class="value"><ui:date path="sitePersonnel[${index}].startDate" readonly="${readOnly || !hasRSCreate}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="sitePersonnel[${index}].endDate" labelProperty="end.date" text=""/></div>
                                <div class="value"><ui:date path="sitePersonnel[${index}].endDate" readonly="${readOnly || !hasRSCreate}"/></div>
                            </div>
                        </div>
                        <div class="rightpanel">
                            <div class="row">
                                <div class="label"><ui:label path="sitePersonnel[${index}].address.street" labelProperty="street" text=""/></div>
                                <div class="value"><ui:text path="sitePersonnel[${index}].address.street" readonly="${readOnly || !hasRSCreate}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="sitePersonnel[${index}].address.city" labelProperty="city" text=""/></div>
                                <div class="value"><ui:text path="sitePersonnel[${index}].address.city" readonly="${readOnly || !hasRSCreate}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="sitePersonnel[${index}].address.state" labelProperty="state" text=""/></div>
                                <div class="value"><ui:text path="sitePersonnel[${index}].address.state" readonly="${readOnly || !hasRSCreate}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="sitePersonnel[${index}].address.zip" labelProperty="zip" text=""/></div>
                                <div class="value"><ui:text path="sitePersonnel[${index}].address.zip" cssClass="validate-ZIPCODE" readonly="${readOnly || !hasRSCreate}"/></div>
                            </div>
                            <div class="row">
                                <div class="label"><ui:label path="sitePersonnel[${index}].address.country" labelProperty="country" text=""/></div>
                                <div class="value"><ui:text path="sitePersonnel[${index}].address.country" readonly="${readOnly || !hasRSCreate}"/></div>
                            </div>
                        </div>
                    </div>

    </jsp:body>
</chrome:division>
