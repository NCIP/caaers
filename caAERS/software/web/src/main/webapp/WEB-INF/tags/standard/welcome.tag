<%@ tag import = "org.acegisecurity.context.SecurityContext" %>
<%@ tag import = "gov.nih.nci.cabig.caaers.security.CaaersUser" %>
<%@ tag import = "gov.nih.nci.cabig.caaers.security.WebSSOUser" %>
<%@ tag import = "gov.nih.nci.cabig.caaers.domain.UserGroupType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<% 
Object caaersUser = null;

try{
	SecurityContext context = (SecurityContext)request.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
	if(context != null){
	   if(context.getAuthentication().getPrincipal() instanceof WebSSOUser){
		   caaersUser = (WebSSOUser)context.getAuthentication().getPrincipal();
	   }else if(context.getAuthentication().getPrincipal() instanceof CaaersUser){
		   caaersUser = (CaaersUser)context.getAuthentication().getPrincipal();
	   }
	}
	
}catch(Exception exx){
	
}
%>

<div id="USER_ROLES" style="display:none;">

<ol>
<c:if test="${fn:length(ol) > 0}">
    <b style="font-size:14px">Roles:</b><br><br>
<div style='color:#888;'>
<c:forEach items="${ol}" var="a">
    <li>${a}</li>
</c:forEach>
</div>
</c:if>
    
<div style='color:yellow;'>
<c:forEach items="${cl}" var="a">
    <li>${a}</li>
</c:forEach>
</div>
</ol>   
</div>

<c:set var="user" value="<%= caaersUser %>" />
<span id="welcome-user">
	<c:if test="${user != null }">
		<a onmouseover="showUserRolesToolTip($('USER_ROLES').innerHTML, '')" onmouseout="tt_Hide();">Welcome <b> ${user.firstName} ${user.lastName}</b></a>
	</c:if>
</span>

<script>
function showUserRolesToolTip(text, title) {
    Tip(text,
            WIDTH, 300,
            TITLE, title,
            SHADOW, false,
            FADEIN, 300,
            FADEOUT, 300,
            STICKY, 1,
            CLOSEBTN, false,
            CLICKCLOSE, false,
            OPACITY, 95,
            FONTCOLOR, "#fff",
            BORDERCOLOR, "#fff",
            BGCOLOR, "#444",
            PADDING, 15,
            FONTSIZE, "12px"
    );
}
</script>