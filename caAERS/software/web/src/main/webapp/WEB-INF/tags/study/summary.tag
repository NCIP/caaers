<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<c:if test="${not empty studySummary }">
    <div id="study-summary-pane" class="summary">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="2">
                        <div class="row">
                            <div class="label">${studySummary[0].code}</div>
                            <div class="value"><c:out value="${empty studySummary[0].desc ? '<em class=\"none\">None</em>' : studySummary[0].desc}" escapeXml="true" /></div>
                        </div>
                    </td>
                </tr>
            </table>
    </div>
</c:if>