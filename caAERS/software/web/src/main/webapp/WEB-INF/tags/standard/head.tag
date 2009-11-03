<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://jawr.net/tags" prefix="jwr" %>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<tags:js name="compressed/co_prototype"/>
<tags:js name="jquery-1.3.2.min"/>
<tags:js name="jquery-ui-1.7.2.custom.min"/>
<script type="text/javascript">
	jQuery.noConflict();
</script>
<tags:js name="fg.menu"/>
<tags:js name="ajax-loading-indicator" />

<jwr:style src="/csslib/caaers.zcss" />

<tags:js name="compressed/co_calendar" />

<tags:js name="common" />
<tags:js name="validation" />
<tags:js name="commons-validation" />

<tags:js name="compressed/co_scriptaculous_files"/>
<tags:js name="compressed/co_accordion"/>

<tags:js name="advancedSearch"/>
<tags:js name="common_help"/>
<tags:js name="side-bar/side-bar"/>

<tags:js name="compressed/co_date"/>
<tags:js name="compressed/co_D2H_ctxt"/>
<tags:js name="compressed/co_dropdown_menu"/>
<tags:js name="compressed/co_extremecomponents"/>
<tags:js name="compressed/co_hover-display"/>

<tags:js name="routing_and_review"/>
<tags:js name="errors"/>
<tags:js name="logmeout"/>

<jwr:script src="/jslib/caaers.zjs" />
<tags:js name="common-scriptaculous"/>

<!--[if IE]>
<jwr:style src="/csslib/ie.zcss" />
<![endif]-->

<!--[if lte IE 6]>
<jwr:style src="/csslib/ie6.zcss" />
<![endif]-->

<script type="text/javascript">
// hotlinking
// https://wiki.nci.nih.gov/x/ygqG
CCTS.appShortName = 'caaers';

// this works together with [tags:collapsableElement.tag], allows setting the collapsable state = TRUE
var _collapsedELs = new Array();

//for autocompleters to work.
AE.autocompleterDelay = ${configuration.map.autoCompleterDelay};
AE.autocompleterChars = ${configuration.map.autoCompleterChars};
AE.APP_BASE_URL = '<c:url value="/" />';

//overwrite the HttpSession timeout warning & wait
AE.SESSION_TIME_OUT_WARNING = ${empty configuration.map.httpSessionWarning ? '10' : configuration.map.httpSessionWarning} * 60;
AE.SESSION_TIME_OUT_WAIT= ${empty configuration.map.httpSessionWarningWait ? '2' : configuration.map.httpSessionWarningWait} * 60;
</script>

<link rel="shortcut icon" href="../../images/caaers.ico" type="image/x-icon"/>

<!--[if lte IE 6]>
<script type="text/javascript">  
try { document.execCommand('BackgroundImageCache', false, true); } catch(e) {}  
</script>  
<![endif]-->

<!-- END tags\standard\head.tag -->
