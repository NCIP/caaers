<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:if test="${not empty studySummary }">
    <div id="study-summary-pane" class="summary">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="2">
                        <div class="row  instructions">
                            <div class="label">${studySummary[0].code}</div>
                            <div class="value" style="margin-left: 70px;">
                                <c:choose>
                                    <c:when test="${not empty studySummary[0].desc}">
                                        <tags:substring s="${studySummary[0].desc}" l="100" />
                                    </c:when>
                                    <c:otherwise><em class="none">None</em></c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
    </div>
</c:if>