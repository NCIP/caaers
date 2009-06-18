<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://jawr.net/tags" prefix="jwr" %>

<jwr:style src="/csslib/caaers.zcss" />
<jwr:script src="/jslib/caaers.zjs" />
<tags:js name="calendar" />

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
