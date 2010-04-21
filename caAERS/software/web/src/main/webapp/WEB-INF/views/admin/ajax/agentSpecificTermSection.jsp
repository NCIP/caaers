<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" tagdir="/WEB-INF/tags/admin"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="terms" value="${command.agentSpecificTerms}" />

<tags:noform>

                <%-- ADD --%>
                <c:if test="${param.isADD eq 'true'}">
                    <c:forEach begin="${param.firstIndex}" end="${param.lastIndex}" varStatus="status">
                        <tr class="agentSpecific-section ${status.index % 2 gt 0 ? 'odd' : 'even'}" id="AGENT_TERM_-${status.index}" bgcolor="white">
                            <admin:oneAgentSpecificAE isOtherSpecify="${agentTerm.otherRequired}" index="${status.index}" term="${terms[status.index]}"/>
                            <td style="text-align:center;" width="50px">
                                 <tags:button id="${status.index}" color="blue" type="button" value="" size="small" icon="x" onclick="removeTerm(${status.index})"/>
                            </td>
                        </tr>
                    </c:forEach>

                </c:if>

                <%-- DELETE --%>

                <c:if test="${not param.isADD eq 'true'}">
                        <table id="termsTable" width="100%" border="0" cellspacing="1" cellpadding="3">
                            <tr bgcolor="#E4E4E4">
                                <th scope="col" align="left" colspan="2"><b>Term</b></th>
                            </tr>

                            <c:forEach begin="${param.firstIndex}" end="${param.lastIndex}" varStatus="status">
                                <c:if test="${not terms[status.index].deleted}">
                                        <tr class="agentSpecific-section ${status.index % 2 gt 0 ? 'odd' : 'even'}" id="AGENT_TERM_-${status.index}" bgcolor="white">
                                            <admin:oneAgentSpecificAE isOtherSpecify="${terms[status.index].otherRequired}" index="${status.index}" term="${terms[status.index]}"/>
                                            <td style="text-align:center;" width="50px">
                                                <tags:button id="${status.index}" color="blue" type="button" value="" size="small" icon="x" onclick="removeTerm(${status.index})"/>
                                            </td>
                                        </tr>
                                </c:if>
<%--
                                <c:if test="${terms[status.index].deleted}">
                                    <tr class="agentSpecific-section ${status.index % 2 gt 0 ? 'odd' : 'even'}" id="AGENT_TERM_-${status.index}" bgcolor="white">
                                        <td>
                                        <td style="text-align:center;" width="50px">[X]</td>
                                    </tr>
                                </c:if>
--%>
                            </c:forEach>

                            <tr id="observedBlankRow" style="display:none;"><td></td></tr>
                        </table>
                    <%--<tr id="observedBlankRow" style="display:none;"><td></td></tr>--%>
                </c:if>
</tags:noform>

