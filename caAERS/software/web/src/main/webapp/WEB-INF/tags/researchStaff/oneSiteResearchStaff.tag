<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="rs" tagdir="/WEB-INF/tags/researchStaff" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="collapsed" type="java.lang.Boolean" %>
${index}=${researchStaff.siteResearchStaffs[index]}
<chrome:division id="abc" collapsable="true" collapsed="false" enableDelete="true" title="Organization-name">
    <table width="100%">
        <tr><td colspan="2">Contact Information</td></tr>
        <tr>
            <td>
                <div class="row">
                    <div class="label">Email address:</div>
                    <div class="value"><%--<ui:text path="command">--%></div>
                </div>
            </td>
            <td></td>
        </tr>
    </table>
</chrome:division>
