<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<meta http-equiv="X-UA-Compatible" content="IE=7">
<jwr:style src="/csslib/caaers.zcss" />
<jwr:script src="/jslib/caaers.zjs" useRandomParam="false"/>


<!--[if IE]>
<tags:js name='jquery/jquery.caret.ie' />
<jwr:style src="/csslib/ie.zcss" />
<![endif]-->

<!--[if lte IE 6]>
<jwr:style src="/csslib/ie6.zcss" />
<![endif]-->

<caaers:message var="_unsavedInfoMsg" code="${code}" text="There are unsaved changes." />

<script type="text/javascript">
// hotlinking
// https://wiki.nci.nih.gov/x/ygqG
CCTS.appShortName = 'caaers';

// this works together with [tags:collapsableElement.tag], allows setting the collapsable state = TRUE
var _collapsedELs = new Array();

//for autocompleters to work.
AE.autocompleterDelay = ${empty configuration.map.autoCompleterDelay ? 1 : configuration.map.autoCompleterDelay};
AE.autocompleterChars = ${empty configuration.map.autoCompleterChars ? 1 : configuration.map.autoCompleterChars};
AE.APP_BASE_URL = '<c:url value="/" />';

//overwrite the HttpSession timeout warning & wait
AE.SESSION_TIME_OUT_WARNING = ${empty configuration.map.httpSessionWarning ? '10' : configuration.map.httpSessionWarning} * 60;
AE.SESSION_TIME_OUT_WAIT= ${empty configuration.map.httpSessionWarningWait ? '2' : configuration.map.httpSessionWarningWait} * 60;

AE.UNSAVED_DATA_MESSAGE='${_unsavedInfoMsg}';

AE.WEBSSO_AUTHENTICATION_MODE = '${configuration.authenticationModeWebSSO}';

AE.CSRF_TOKEN = '${CSRF_TOKEN }';

</script>

<link rel="shortcut icon" href="../../images/caaers.ico" type="image/x-icon"/>

<!--[if lte IE 6]>
<script type="text/javascript">  
try { document.execCommand('BackgroundImageCache', false, true); } catch(e) {}  
</script>

<![endif]-->
<!--
<c:forEach items="${roles}" var="r">
    <c:out value="${r.key}" />
</c:forEach>
-->
<script>
    var roles_map = new Array();
    <c:forEach items="${originalRoles}" var="r">
    roles_map.push("${r.key}");
    </c:forEach>

    function hasRole(_roleName) {
        return (jQuery.inArray(_roleName, roles_map) >=0);
    }
</script>

<!-- END tags\standard\head.tag -->
