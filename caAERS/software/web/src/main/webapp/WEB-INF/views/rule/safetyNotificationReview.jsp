<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
    <title>${tab.longTitle}</title>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false">
        <jsp:attribute name="repeatingFields">
            <p><tags:instructions code="notification.safety_notification.top"/></p>
            <ui:row path="notification.study">
                <jsp:attribute name="label"><ui:label path="notification.study" required="true" text="Study." /></jsp:attribute>
                <jsp:attribute name="value">
                  <c:out value="${command.notification.study.displayName}" escapeXml="true" />
                </jsp:attribute>
            </ui:row>

            <ui:row path="notification.name">
                <jsp:attribute name="label"><ui:label path="notification.name" required="true" text="Name." /></jsp:attribute>
                <jsp:attribute name="value">
                   <c:out value="${command.notification.name}" escapeXml="true" />
                </jsp:attribute>
            </ui:row>

            <ui:row path="notification.emails">
                <jsp:attribute name="label"><ui:label path="notification.emails" required="true" text="Recipients."/></jsp:attribute>
                <jsp:attribute name="value">
                    <table border="0" cellspacing="2" cellpadding="0" width="90%">
                        <tr align="middle">
                            <td width="49%"><ui:label path="notification.recipientEmails" text="Roles" /> </td>
                            <td rowspan="2" width="2%" class="divider">&nbsp;</td>
                            <td><ui:label path="notification.recipientRoles" text="Roles" /> </td>
                        </tr>
                        <tr>
                            <td width="49%" >
                                <c:forEach var="e" items="${command.notification.recipientEmails}">
                                    <div>
                                        <c:out value="${e}" escapeXml="true" />
                                    </div>

                                </c:forEach>

                                <span id="b-email" />
                            </td>
                            <td>
                                <c:forEach var="r" items="${command.notification.recipientRoles}">

                                    <div>
                                        <c:out value="${r}" escapeXml="true" />
                                    </div>
                                </c:forEach>

                                <span id="b-role" />
                            </td>
                        </tr>
                    </table>
                </jsp:attribute>

            </ui:row>

            <ui:row path="notification.subject">
                <jsp:attribute name="label"><ui:label path="notification.subject" required="true" text="Subject." /></jsp:attribute>
                <jsp:attribute name="value">
                    <c:out value="${command.notification.subject}" escapeXml="true" />
                </jsp:attribute>
            </ui:row>

            <ui:row path="notification.content">
                <jsp:attribute name="label"><ui:label path="notification.content" required="true" text="Mail content." /></jsp:attribute>
                <jsp:attribute name="value">
                    <c:out value="${command.notification.content}" escapeXml="true" />
                </jsp:attribute>
            </ui:row>

            <c:if test="${flow.tabCount eq (tab.number + 1) }">
                <input type="hidden" name="_finish" value="0" />
            </c:if>

        </jsp:attribute>
</tags:tabForm>
</body>
</html>