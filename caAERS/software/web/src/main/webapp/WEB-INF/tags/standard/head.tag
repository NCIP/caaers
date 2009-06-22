<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://jawr.net/tags" prefix="jwr" %>

<jwr:style src="/csslib/caaers.zcss" />

<tags:js name="prototype"/>
<tags:js name="calendar" />
<tags:js name="common" />

<tags:js name="validation" />
<tags:js name="commons-validation" />

<tags:js name="scriptaculous/effects"/>
<tags:js name="scriptaculous/builder"/>
<tags:js name="scriptaculous/controls"/>
<tags:js name="scriptaculous/dragdrop"/>
<tags:js name="scriptaculous/slider"/>

<tags:js name="advancedSearch"/>
<tags:js name="common_help"/>
<tags:js name="side-bar/side-bar"/>
<tags:js name="wz_tooltip"/>
<tags:js name="date"/>
<tags:js name="D2H_ctxt"/>
<tags:js name="dropdown_menu"/>
<tags:js name="extremecomponents"/>
<tags:js name="hover-display"/>
<tags:js name="routing_and_review"/>
<tags:js name="tabbedflow"/>

<!--
/js/livepipe/livepipe.js, /js/livepipe/tabs.js, /js/livepipe/scrollbar.js, \
/js/YUI/utilities.js, /js/YUI/datasource-min.js, /js/YUI/datatable-min.js, /js/YUI/datatable-patch.js, /js/YUI/container-min.js, /js/YUI/button-min.js, /js/YUI/paginator-min.js, \
/js/ccts-hotlinks.js, \
/js/prototypewindow/window.js, /js/prototypewindow/window_ext.js, /js/prototypewindow/window_effects.js, /js/prototypewindow/tooltip.js, \
-->


<jwr:script src="/jslib/caaers.zjs" />
<tags:js name="common-scriptaculous"/>

<!--[if IE]>
<jwr:style src="/csslib/ie.zcss" />
<![endif]-->

<!--[if lte IE 6]>
<jwr:style src="/csslib/ie6.zcss" />
<![endif]-->

<script type="text/javascript">
    AE.autocompleterDelay = ${configuration.map.autoCompleterDelay};
    AE.autocompleterChars = ${configuration.map.autoCompleterChars};
</script>

<link rel="shortcut icon" href="../../images/caaers.ico" type="image/x-icon"/>

<!--[if lte IE 6]>
<script type="text/javascript">  
try { document.execCommand('BackgroundImageCache', false, true); } catch(e) {}  
</script>  
<![endif]-->
<!-- END tags\standard\head.tag -->
