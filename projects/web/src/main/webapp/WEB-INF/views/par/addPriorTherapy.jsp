<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<script type="text/javascript">
    //    Spring.addDecoration(new Spring.ElementDecoration({
    //        elementId : "firstName",
    //        widgetType : "dijit.form.FilteringSelect",
    //        widgetAttrs : {
    //            autocomplete : false,
    //            required : true
    //        }
    //    }));
    //
    //    Spring.addDecoration(new Spring.ElementDecoration({
    //        elementId : "lastName",
    //        widgetType : "dijit.form.ValidationTextBox",
    //        widgetAttrs : {
    //            invalidMessage : "Invalid last name",
    //            regExp : "\\d{16}",
    //            required : true
    //        }
    //    }));


    //                  Spring.addDecoration(new Spring.ElementDecoration({
    //					elementId : "edit",
    //					widgetType : "dijit.form.InlineEditBox"
    //
    //				}));


    //    Spring.addDecoration(new Spring.ElementDecoration({
    //        elementId : "date",
    //        widgetType : "dijit.form.DateTextBox",
    //        widgetAttrs : {
    //
    //            required : true
    //        }
    //    }));

    //         dojo.require("dojo.parser");
    //       dojo.require("dijit.form.InlineEditBox");
    //       dojo.require("dijit.form.TextBox");
    //       function myHandler(idOfBox, value) {
    //           console.debug("Edited value from "+idOfBox+" is now "+value)};

</script>

<form:form id="priorTherapy" method="post" modelAttribute="priorTherapy">

    <chrome:division title="Prior Therapy Details">
        <table id="test2" class="single-fields" width="100%">
            <tr>
                <td>
                    <c:forEach begin="0" end="0" items="${fieldGroups.fieldGroups.priorTherapy.fields}" var="field">
                        <tags:renderRow field="${field}"/>
                    </c:forEach>
                </td>
                    <%--<td>--%>
                    <%--<div class="row" id="priorTherapy.dateOfBirth-row">--%>
                    <%--<div class="label"><tags:renderLabel field="${fieldGroups.priorTherapy.fields[4]}"/></div>--%>
                    <%--<div class="value"><tags:renderInputs field="${fieldGroups.priorTherapy.fields[4]}"/></div>--%>
                    <%--</div>--%>
                    <%--<c:forEach begin="5" end="7" items="${fieldGroups.priorTherapy.fields}" var="field">--%>
                    <%--<tags:renderRow field="${field}"/>--%>
                    <%--</c:forEach>--%>
                    <%--</td>--%>
            </tr>

        </table>

    </chrome:division>


    <div id="submit">
        <input type="hidden" name="execution" value="${flowExecutionKey}">
        <input type="hidden" name="_eventId" value="save">
        <input type="submit" value="Save"/>
    </div>
</form:form>
