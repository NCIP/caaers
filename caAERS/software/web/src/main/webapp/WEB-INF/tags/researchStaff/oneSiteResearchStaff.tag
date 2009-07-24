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

<chrome:division id="siteResearchStaff_${index}" collapsable="true" collapsed="false" enableDelete="true" title="Organization-name${index}">

    <table width="100%">
        <tr>
            <td>
                <caaers:message code="researchstaff.details.contact" var="contacts"/>
                <b>${contacts}</b>
                <chrome:division id="staff-details" title="">
                        <div class="leftpanel">
                            <div class="row">
                                <div class="label">Email address:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].emailAddress" cssClass="validate-EMAIL"/></div>
                            </div>
                            <div class="row">
                                <div class="label">Phone:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].phoneNumber"/></div>
                            </div>
                            <div class="row">
                                <div class="label">Fax:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].faxNumber"/></div>
                            </div>
                        </div>
                        <div class="rightpanel">
                            <div class="row">
                                <div class="label">Street:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.street"/></div>
                            </div>
                            <div class="row">
                                <div class="label">City:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.city"/></div>
                            </div>
                            <div class="row">
                                <div class="label">State:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.state"/></div>
                            </div>
                            <div class="row">
                                <div class="label">ZIP:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.zip" cssClass="validate-ZIPCODE"/></div>
                            </div>
                            <div class="row">
                                <div class="label">Country:</div>
                                <div class="value"><ui:text path="researchStaff.siteResearchStaffs[${index}].address.country"/></div>
                            </div>
                        </div>
                </chrome:division>
            </td>
            <td></td>
        </tr>
        <tr>
            <td>
                <div class="leftpanel">
                        <caaers:message code="researchstaff.details.rolesSection" var="roleSectionTitle"/>
                        <chrome:division id="roles-details" title="${roleSectionTitle}">
                            <c:forEach items="${allRoles}" var="role">
                                <input type="checkbox" name="srRoles[${index}]" value="${role.code}">&nbsp;${role.name}<br>
                            </c:forEach>
                        </chrome:division>
                </div>
                <div class="rightpanel">
                    <caaers:message code="researchstaff.details.studiesSection" var="studiesSectionTitle"/>
                    <chrome:division id="studies-details" title="${studiesSectionTitle}">
                        <div style="overflow:auto; height:150px;">
                            <c:forEach begin="0" end="20">
                                    <input type="checkbox" name="srsStudy" value="">&nbsp;Study short name<br>
                            </c:forEach>
                        </div>
                    </chrome:division>
                </div>
            </td>
            <td></td>
        </tr>
    </table>
</chrome:division>
