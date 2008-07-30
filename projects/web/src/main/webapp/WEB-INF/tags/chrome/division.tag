<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="title"%>
<%@attribute name="id"%>
<%@attribute name="cssClass"%>
<%@attribute name="style"%>
<%@attribute name="enableDelete" type="java.lang.Boolean"%>
<%@attribute name="deleteParams" %>
<%@attribute name="collapsable" required="false" %>

<div class="division ${cssClass}" <tags:attribute name="id" value="${id}"/> <tags:attribute name="style" value="${style}"/>>

    <div class="header">
    <c:if test="${not empty title}">

        <c:if test="${enableDelete || collapsable}">
        	<h3>
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                    <tr>
                        <td width="100%">${title}</td>
                        <c:if test="${deleteParams}"><td align="right"><a href="javascript:fireAction(<c:out value="${deleteParams},'${id}','${cssClass}'" />);"><img src="/caaers/images/checkno.gif" border="0" alt="delete"></a></td></c:if>
                        <c:if test="${collapsable}"><td align="right"><a style="cursor:pointer;" onClick="SwitchCollapsableState('contentOf-<c:out value="${id}" />', '<c:out value="${id}" />')"><img id="image-${id}" src="/caaers/images/chrome/minimize.gif" border="0" height="16"/></a></td></c:if>
                    </tr>
                </table>
            </h3>
	   	</c:if>

        <c:if test="${!enableDelete && !collapsable}">
	   		<h3>${title}</h3>
	   	</c:if>

    </c:if>
    </div>

    <div class="content" id="contentOf-${id}" style="display:block; padding:0px; margin:10px;">
        <c:if test="${collapsable && (empty id)}"><h1 style="color:red; padding-bottom:20px;">Please give an unique ID to your Division Element.</h1></c:if>
        <jsp:doBody/>
    </div>
</div>
