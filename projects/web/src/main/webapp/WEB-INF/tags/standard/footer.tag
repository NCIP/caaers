<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div id="footer">
    <div id="copyright">&copy; 2006 SemanticBits Company. All Rights Reserved</div>
    <div id="cabig-logo"><img src="<chrome:imageUrl name="caBIG_logo.gif"/>" alt="caBIG cancer Biomedical Informatics Grid"></div>
</div>
<form action="#" id="sso-form" target="_blank" method="post">
    <!-- TODO: actual value for gridProxy -->
    <input type="hidden" name="gridProxy" value="?"/>
</form>
<%-- <tags:debugInfo/> --%>
