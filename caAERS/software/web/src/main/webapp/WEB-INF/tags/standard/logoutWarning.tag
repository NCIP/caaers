<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<div id="logout_warning" style="display:none;text-align:left;padding-left:10px; width:560px;">
    <div class="warning-box message"><p><tags:message key="instruction_logout_warning"></tags:message></p></div>
    <div style="text-align: center;"><tags:button color="blue" value="Keep Working" icon="continue" onclick="logOutOKClicked();"></tags:button></div>
</div>
