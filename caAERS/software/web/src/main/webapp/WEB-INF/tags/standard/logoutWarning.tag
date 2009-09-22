<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div id="logout_warning" style="display:none;position:absolute;width:200px;height:100px;z-index:30;right:150;top:150;">
	<p> <tags:message key="instruction_logout_warning"></tags:message></p>
	<tags:button color="blue" value="Ok" icon="x" onclick="logOutOKClicked(AE.APP_BASE_URL + 'j_acegi_logout');"></tags:button>
</div>