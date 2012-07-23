<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Configure caAERS</title>
    <script type="text/javascript" src="<c:url value="/js/jquery/jquery.cookie.js" />"></script>
    <script type="text/javascript" language="javascript">
        jQuery(function() {
            jQuery( "#ctabs" ).tabs({cookie:{expires:1}});
        });
    </script>
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
    <style type="text/css">
        .ui-tabs-nav{z-index: 150;}
        div.row { padding: 5px 3px; }
        .row .value { margin-left: 22%; }
        .row .label { width: 20%; text-align: right; }
        p.description { margin: 0.25em 0 0 1em; }
        div.submit { text-align: right; }
        .value input[type=text] { width: 80%; }
        form { margin-top: 1em; }

    </style>
</head>
<body>

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


        
    <form:form action="${action}" cssClass="standard">
    	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
        <chrome:box title="Configure caAERS" autopad="true">
            <c:if test="${param.updated}">
                <div class="success-box message"><p>Settings saved</p></div>
            </c:if>
            <p><tags:instructions code="configurecaares" /></p>
            <div id="ctabs">
                <ul>
                    <li><a href="#tabs-1">General</a></li>
                    <li><a href="#tabs-2">AdEERS Integration</a></li>
                    <c:if test="${configuration.authenticationMode ne 'local'}">
                        <li><a href="#tabs-3">CCTS Integration</a></li>
                    </c:if>
                    <li><a href="#tabs-4">Email</a></li>
                    <li><a href="happy?subview=1">System Status</a></li>
                </ul>
                <div id="tabs-1">

                    <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.tools.configuration.Configuration:UPDATE">
                        <div class="row">
                            <div class="label"><caaers:message code="reloadLabels" text="Reload labels"/></div>
                            <div class="value" id="reloadedLabels">
                                <caaers:message code="reloadLabels" text="Reload labels" var="lblReloadLabelValue"/>
                                <tags:button value="${lblReloadLabelValue}" color="blue" onclick="reloadLabels()" />
                            </div>
                        </div>
                    </csmauthz:accesscontrol>
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
                </div>
                <div id="tabs-2">
                    <admin:oneConfigEntry entry="${command.conf['esbUrl'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['esbWSUrl'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['wsUsername'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['wsPassword'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['esbLogLocation'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['studySyncDelay'].property}" cssClass="validate-WHOLENUMBER"/>
                </div>
                <c:if test="${configuration.authenticationMode ne 'local'}">
                    <div id="tabs-3">
                        <admin:oneConfigEntry entry="${command.conf['paLimit'].property}"/>
                        <admin:oneConfigEntry entry="${command.conf['poLimit'].property}"/>
                        <admin:oneConfigEntry entry="${command.conf['labViewerBaseUrl'].property}"/>
                        <admin:oneConfigEntry entry="${command.conf['pscBaseUrl'].property}"/>
                        <admin:oneConfigEntry entry="${command.conf['caExchangeNonGridUrl'].property}"/>
                        <admin:oneConfigEntry entry="${command.conf['caExchangeUrl'].property}"/>
                        <admin:oneConfigEntry entry="${command.conf['caExchangeNonGridUserName'].property}"/>
                        <admin:oneConfigEntry entry="${command.conf['caExchangeNonGridPassword'].property}"/>
                    </div>
                </c:if>
                <div id="tabs-4">
                    <admin:oneConfigEntry entry="${command.conf['smtpAddress'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['smtpPort'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['smtpTimeout'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['smtpProtocol'].property}" options="${command.emailProtocols}"/>
                    <admin:oneConfigEntry entry="${command.conf['smtpSSLEnabled'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['smtpUser'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['smtpPassword'].property}"/>
                    <admin:oneConfigEntry entry="${command.conf['systemFromEmail'].property}"/>
                </div>
            </div>

        </chrome:box>

        <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.tools.configuration.Configuration:UPDATE">
	        <div class="row submit"><tags:button type="submit" value="Save" color="green" icon="save" /></div>
    	</csmauthz:accesscontrol>
    </form:form>

</body>
</html>