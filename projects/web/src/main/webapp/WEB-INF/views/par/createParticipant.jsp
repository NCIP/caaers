<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
    Spring.addDecoration(new Spring.ElementDecoration({
        elementId : "firstName",
        widgetType : "dijit.form.FilteringSelect",
        widgetAttrs : {
            autocomplete : false,
            required : true
        }
    }));

    Spring.addDecoration(new Spring.ElementDecoration({
        elementId : "lastName",
        widgetType : "dijit.form.ValidationTextBox",
        widgetAttrs : {
            invalidMessage : "Invalid last name",
            regExp : "\\d{16}",
            required : true
        }
    }));


    //                  Spring.addDecoration(new Spring.ElementDecoration({
    //					elementId : "edit",
    //					widgetType : "dijit.form.InlineEditBox"
    //
    //				}));


    Spring.addDecoration(new Spring.ElementDecoration({
        elementId : "date",
        widgetType : "dijit.form.DateTextBox",
        widgetAttrs : {

            required : true
        }
    }));

    //         dojo.require("dojo.parser");
    //       dojo.require("dijit.form.InlineEditBox");
    //       dojo.require("dijit.form.TextBox");
    //       function myHandler(idOfBox, value) {
    //           console.debug("Edited value from "+idOfBox+" is now "+value)};

</script>

<form:form id="participant" method="post" modelAttribute="participantCommand">


    <chrome:division title="Participant Details">


        <td><a href="${flowExecutionUrl}&_eventId=addDetails">Add</a></td>
        <div>


            <input type="text" id="firstName" value="${participantCommand.firstName}">
        </div>
        <div>

            <form:input id="lastName" path="${participantCommand.firstName}"></form:input>
        </div>
        <div>

            <input type="text" id="date" value="${participantCommand.firstName}">
        </div>


        <!--<div>-->
        <!--Edit Please:<br>-->
        <!--<p id="areaEditable" dojoType="dijit.form.InlineEditBox"-->
        <!--renderAsHtml="true" autoSave="false">-->
        <!--<textarea dojoType="dijit.form.Textarea">-->
        <!--vinod-->
        <!--</textarea>-->
        <!--</p>   -->
        <div>


        </div>

    </chrome:division>
</form:form>
