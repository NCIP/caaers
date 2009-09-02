<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<c:if test="${not empty studySummary }">
    <div id="study-summary-pane" style="width:100%; clear:both;">
        <chrome:box title="Summary">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="2">
                        <div class="row">
                            <div class="label">${studySummary[0].code}</div>
                            <div class="value">${empty studySummary[0].desc ? '<em class="none">None</em>' : studySummary[0].desc}</div>
                        </div>
                    </td>
                </tr>
                <tr>

                    <td>
                        <div class="row">
                            <div class="label">${studySummary[1].code} </div>
                            <div class="value">${empty studySummary[1].desc ? '<em class="none">None</em>' : studySummary[1].desc}</div>
                        </div>
                    </td>
                    <td>
                        <div class="row">
                            <div class="label">${studySummary[3].code}</div>
                            <div class="value">
                                <c:if test="${command.study.primaryFundingSponsorOrganization.externalId != null}">
                                    <img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>
                                </c:if>
                                ${empty studySummary[3].desc ? '<em class="none">None</em>' : studySummary[3].desc}
                            </div>
                        </div>
                    </td>

                </tr>
                <tr>
                    <td>
                        <div class="row">
                            <div class="label">${studySummary[2].code}</div>
                            <div class="value">${empty studySummary[2].desc ? '<em class="none">None</em>' : studySummary[2].desc}</div>
                        </div>
                    </td>
                    <td>
                        <div class="row">
                            <div class="label">${studySummary[4].code}</div>
                            <div class="value">
                                <c:if test="${command.study.studyCoordinatingCenter.organization.externalId != null}">
                                    <img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17"
                                         height="16" border="0" align="middle"/>
                                </c:if>
                                    ${empty studySummary[4].desc ? '<em class="none">None</em>' : studySummary[4].desc}
                            </div>
                        </div>
                    </td>

                </tr>
            </table>
        </chrome:box>
    </div>
</c:if>