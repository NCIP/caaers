<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@attribute name="subjectFullName" type="java.lang.String" required="true" %>
<%@attribute name="studyShortTitle" type="java.lang.String" %>

    <div id="subject-summary-pane" class="summary">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="2">
                        <div class="row  instructions">
                            <div class="label" style="position: relative; left: 8px;">Subject</div>
                            <div class="value" style="margin-left: 70px;"s> ${subjectFullName}</div>
                        </div>
                        <c:if test="${not empty studyShortTitle}">
	                        <div class="row  instructions">
		                        <div class="label" style="position: relative; left: 8px;">Study</div>
		                        <div class="value" style="margin-left: 70px;"> ${studyShortTitle}</div>
	                        </div>
                        </c:if>
                    </td>
                </tr>
            </table>
    </div>
