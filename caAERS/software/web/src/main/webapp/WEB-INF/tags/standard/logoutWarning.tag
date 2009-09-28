<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<div id="logout_warning" style="display:none;text-align:left;padding-left:10px;">
    <p>
        <tags:message key="instruction_logout_warning">
        </tags:message>
    </p>
    <div class="content buttons autoclear" style="margin-top:50px;">
        <div class="flow-buttons">
            <span class="next">
                <tags:button color="blue" value="Keep Working" icon="continue" onclick="logOutOKClicked(AE.APP_BASE_URL + 'j_acegi_logout');"></tags:button>
            </span>
        </div>
    </div>
</div>
