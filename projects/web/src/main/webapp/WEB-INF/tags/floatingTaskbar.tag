<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>

<script language="JavaScript">

    var TASKS = [
                <c:forEach items="${sections}" var="section">
                    <csmauthz:accesscontrol authorizationCheckName="sectionAuthorizationCheck" domainObject="${section}">
                        <c:set var="noOfTasks" value="${fn:length(section.tasks)}" />
                        ['<c:out value="${section.displayName}" />', ${noOfTasks}, ${section == currentSection ? "true" : "false"},
                                <c:forEach items="${section.tasks}" var="task" varStatus="index">
                                    <csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck">
                                       ['<c:out value="${task.displayName}" />', '<c:url value="${task.url}"/>', '/caaers/images/blue/icons/${task.linkName}_icon.png'] ${!index.last ? ", " : ""}
                                    </csmauthz:accesscontrol>
                                </c:forEach>
                        ],
                    </csmauthz:accesscontrol>
                </c:forEach>
            ];

    var FloatingTaskbar = Class.create({
        ms      : 100,
        hidden  : true,
        initialize: function() {
        },

        show: function(index) {
            var sz = TASKS[index].size();
            if (sz <= 3) ft.hide();
            if (TASKS[index][2]) {
                ft.hidden = true;
                ft.hide();
                return;
            };

            var _string = "<ul id='secondLevelTaskbar'>";
            for (i=3; i<sz; i++) {
                var _length = TASKS[index][i][0].length;
                _string += "<li class='" + ((sz - 3 > 4) ? "gt4" : "lte4") + "'><a href='" + TASKS[index][i][1] + "' class='" + (_length > 21 ? "gt18" : "") + "'><img class='" + (_length > 21 ? "imagegt18" : "") + "' alt='' src='" + TASKS[index][i][2] + "'/><span class='spangt18'>" + TASKS[index][i][0] + "</span></a>";
            }
            _string += "</ul>";

            $('floatingTaskbar').innerHTML = _string;
            $('floatingTaskbar').show();
        },

        hide: function() {
            $('floatingTaskbar').hide();
        },

        checkSubmenu: function() {
            if (ft.hidden) $('floatingTaskbar').hide();
        }
    });

    var ft = new FloatingTaskbar();

    $$('#sections.tabs li a').each(function(element) {
        Event.observe(element, "mouseover", function() {
            ft.show(Element.readAttribute(element, 'index'));
            ft.hidden = false;
        });
        Event.observe(element, "mouseout", function() {
            ft.hidden = true;
            setTimeout("ft.checkSubmenu()", ft.ms);
        });
    });

    Event.observe($('floatingTaskbar'), "mouseover", function() {
        ft.hidden = false;
    });
    Event.observe($('floatingTaskbar'), "mouseout", function() {
        ft.hidden = true;
        setTimeout("ft.checkSubmenu()", ft.ms);
    });

</script>