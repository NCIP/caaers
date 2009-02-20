<!-- BEGIN tags\standard\head.tag -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:stylesheetLink name="debug"/>
<tags:stylesheetLink name="common"/>
<tags:stylesheetLink name="fields"/>
<tags:stylesheetLink name="buttons"/>
<tags:stylesheetLink name="extremecomponents"/>
<tags:stylesheetLink name="calendar/calendar-blue"/>
<tags:stylesheetLink name="hoverhelp"/>
<tags:stylesheetLink name="tigra-menu/tigra-menu"/>
<tags:stylesheetLink name="side-bar"/>
<!--[if IE]>
<tags:stylesheetLink name="ie"/>
<![endif]-->
<!--[if lte IE 6]>
<tags:stylesheetLink name="ie6"/>
<![endif]-->
<tags:stylesheetLink name="box"/>

<tags:javascriptLink name="calendar"/>
<tags:javascriptLink name="prototype"/>
<tags:javascriptLink name="common"/>
<tags:javascriptLink name="validation"/>
<tags:javascriptLink name="commons-validation"/>

<tags:javascriptLink name="side-bar/side-bar"/>

<!-- SCRIPACULOUS START -->

<tags:javascriptLink name="scriptaculous/effects"/>
<tags:javascriptLink name="scriptaculous/builder"/>
<tags:javascriptLink name="scriptaculous/controls"/>
<tags:javascriptLink name="scriptaculous/dragdrop"/>
<tags:javascriptLink name="scriptaculous/slider"/>
<tags:javascriptLink name="common-scriptaculous"/>
<tags:javascriptLink name="date"/>

<!-- SCRIPACULOUS END -->

<!-- LIVEPIPE START -->
<tags:javascriptLink name="livepipe/livepipe"/>
<tags:javascriptLink name="livepipe/tabs"/>
<tags:javascriptLink name="livepipe/scrollbar"/>

<!-- LIVEPIPE END -->

<tags:javascriptLink name="ccts-hotlinks"/>
<script type="text/javascript">
    // hotlinking
    // https://wiki.nci.nih.gov/x/ygqG
    CCTS.appShortName = 'caaers'

    // this works together with [tags:collapsableElement.tag], allows setting the collapsable state = TRUE
    var _collapsedELs = new Array();
/*
    Event.observe(window, "load", function() {
        for (var i in _collapsedELs) {
            if ($(i));
            // alert('key is: ' + i + ', value is: ' + _collapsedELs[i]);
            // $(i).hide();
        }
    });
*/
</script>
<link rel="shortcut icon" href="../../images/caaers.ico" type="image/x-icon"/>
<!-- END tags\standard\head.tag -->
