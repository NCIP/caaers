<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Configure caAERS</title>
    <style type="text/css">
        div.row { padding: 5px 3px; }
        .row .value { margin-left: 22%; }
        .row .label { width: 20%; text-align: right; }
        p.description { margin: 0.25em 0 0 1em; }
        div.submit { text-align: right; }
        .value input[type=text] { width: 80%; }
        form { margin-top: 1em; }
        .updated { border: #494 solid; border-width: 1px 0; background-color: #8C8; padding: 1em 2em; text-align: center; margin: 1em 30%; color: #fff; font-weight: bold; font-size: 1.1em; }
    </style>
</head>
<body>
	<div class="tabpane">
	    <div class="workflow-tabs2">
    	    <ul id="" class="tabs autoclear">
    	    	<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.tools.configuration.Configuration:READ || gov.nih.nci.cabig.caaers.tools.configuration.Configuration:UPDATE">
        	    	<li id="thirdlevelnav" class="tab selected">
           	    		<div>
                    		<a href="configure"><caaers:message code="configure.menu.general"/></a>
                		</div>
            		</li>
            	</csmauthz:accesscontrol>
            	<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy:READ || gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy:UPDATE">
            		<li id="thirdlevelnav" class="tab">
                		<div>
                   			<a href="passwordPolicyConfigure"><caaers:message code="configure.menu.passwordPolicy"/></a>
                		</div>
            		</li>
            	</csmauthz:accesscontrol>
            	<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition:READ || gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition:UPDATE">
            		<li id="thirdlevelnav" class="tab">
                		<div>
                			<a href="mandatoryFields"><caaers:message code="configure.menu.mandatoryFields"/></a>
                		</div>
            		</li>
            	</csmauthz:accesscontrol>
        	</ul>
    	</div>

<script>
    function reloadLabels() {
        jQuery.ajax({
            url: '<c:url value="/pages/admin/reload" />',
            success: function(data) {
                jQuery('#reloadedLabels').html("<a style='color:green; font-weight:bold;'>Labels reloaded successfully.</a>");
            }
        });
    }
</script>
        
    <form:form action="${action}" cssClass="standard">
        <chrome:box title="Configure caAERS" autopad="true">
            <p><tags:instructions code="configurecaares" /></p>
		
		<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.tools.configuration.Configuration:UPDATE">
        	<div class="row">
            	<div class="label"><caaers:message code="reloadLabels" text="Reload labels"/></div>
            	<div class="value" id="reloadedLabels"><input type="button" onclick="reloadLabels()" value="<caaers:message code="reloadLabels" text="Reload labels"/>"></div>
        	</div>
        </csmauthz:accesscontrol>
            


            <admin:oneConfigEntry entry="${command.conf['labViewerBaseUrl'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['pscBaseUrl'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['esbUrl'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['caExchangeNonGridUrl'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['caExchangeUrl'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['caExchangeNonGridUserName'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['caExchangeNonGridPassword'].property}"/>


            <admin:oneConfigEntry entry="${command.conf['paLimit'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['poLimit'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['autoCompleterChars'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['autoCompleterDelay'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['caaersBaseHelpUrl'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['caaersBaseUrl'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['enableWorkflow'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['httpSessionWarning'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['httpSessionWarningWait'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['unidentifiedMode'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['synchronousSpringEvents'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['showDebugInformation'].property}"/>


            <admin:oneConfigEntry entry="${command.conf['smtpAddress'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['smtpPort'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['smtpSSLEnabled'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['smtpUser'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['smtpPassword'].property}"/>
            <admin:oneConfigEntry entry="${command.conf['systemFromEmail'].property}"/>


        <c:if test="${param.updated}"><p class="updated">Settings saved</p></c:if>
        </chrome:box>

        <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.tools.configuration.Configuration:UPDATE">
	        <div class="row submit"><tags:button type="submit" value="Save" color="green" icon="save" /></div>
    	</csmauthz:accesscontrol>
    </form:form>
    </div>
</body>
</html>