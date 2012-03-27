<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>Manage safety notifications</title>
    <style>
        .yui-dt-resizerliner{height:30px;}

        .row .value {
            margin-left: 22%;
        }

        p.description {
            margin: 0.25em 0 0 1em;
        }
        div.submit {
            margin:0 0 0 157px;
            padding:0;
        }
        .value input[type=text] {
            width: 80%;
        }

        form {
            margin-top: 1em;
        }

        .updated {
            border: #494 solid;
            border-width: 1px 0;
            background-color: #8C8;
            padding: 1em 2em;
            text-align: center;
            margin: 1em 30%;
            color: #fff;
            font-weight: bold;
            font-size: 1.1em;
        }
        .new_definition {
            margin:10px 0 10px 65px;
        }
    </style>
    <link type="image/x-icon" href="../../../images/caaers.ico" rel="shortcut icon"/>
    <script language="JavaScript">
        YAHOO.example.Data = {

            notifications: [
        <c:forEach items="${command.notifications}" var="nf" varStatus="status">
        {
            nfName: "${nf.name}",
            nfStudy: "${nf.study.displayName}",
            nfAction: "<select id='action-id' onChange=\"javascript:handleAction(this, '${nf.id}')\">" +
                        "<option value=\"\">Please select</option>" +
                        "<option value=\"\">View/Edit</option>" +
                        <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.Rule:CREATE">
                        "<option value=\"\">Export</option>" +
                        </csmauthz:accesscontrol>
                      "</select>"
        }
        <c:if test="${!status.last}">,</c:if>
        </c:forEach>

        ]
        };

        /////////////////////////////////

        YAHOO.util.Event.addListener(window, "load", function() {
            YAHOO.example.CustomSort = function() {

                var myColumnDefs = [
                    {key:"nfName",              label:"Name",               sortable:true,      resizeable:true},
                    {key:"nfStudy",             label:"Study",              sortable:true,      resizeable:true},
                    {key:"nfAction",            label:"Action",             sortable:false,     resizeable:true}
                ];

                var activeDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.notifications.slice(0,50));
                activeDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
                activeDataSource.responseSchema = {
                    fields: ["nfName", "nfStudy","nfAction" ]
                };

                //Create config
                var oConfigs = {
                    initialRequest: "results=50",
                    draggableColumns:false
                };
                var activeDataTable = new YAHOO.widget.DataTable("basic", myColumnDefs, activeDataSource, oConfigs);

                return {
                    oDS: activeDataSource,
                    oDT: activeDataTable
                };
            }();


        });

        /////////////////////////////////

        function handleAction(selectElement, id){
            var action = selectElement.options[selectElement.selectedIndex].text;
            if(action != 'Please select')
                if(confirm('Are you sure you want to take the action - ' + action + ' ?')){
                    switch (action) {
                        case "Please select": break;
                        case "View/Edit"         : var url = '<c:url value="/pages/rule/editSafetyNotifiction?id=" />' + id;
                            window.location = url;
                            break;
                        case "Export"       : var url = '<c:url value="/exportSafetyNotification?id="/>' + id;
                            document.location = url;
                            break;

                    }
                }
        }


    </script>


</head>
<body>

<caaers:message code="notification.manage" var="manageNFTitle"/>
<caaers:message code="notification.import" var="importNFTitle" />
<chrome:box title="${manageNFTitle}" autopad="true">
    <p><tags:instructions code="listreportdefinitions" /></p>
    <div id="basic" class="yui-skin-sam"></div>
    <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.Rule:CREATE">
        <div class="new_definition">
            <c:set var="create_url"><c:url value="/pages/rule/createSafetyNotifiction"/></c:set>
            <tags:button color="blue" icon="add" size="small" type="button" value="New Safety Notification" markupWithTag="a" href="${create_url}" />
        </div>
    </csmauthz:accesscontrol>

    <%--<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.Rule:CREATE">--%>
        <%--<chrome:division title="${importNFTitle}" id="import-rules-id" collapsable="true" collapsed="true">--%>

            <%--<tags:instructions code="importxmlnotifications" />--%>

            <%--<form:form enctype="multipart/form-data" cssClass="standard">--%>
                <%--<div class="row">--%>
                    <%--<div class="label" style="width:11em;">--%>
                        <%--Safety notification file--%>
                    <%--</div>--%>
                    <%--<div class="value" style="margin-left:12em;">--%>
                        <%--<input type="file" name="nfFile" size="50" onchange="$('add-nf').removeAttribute('disabled')"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="row submit">--%>
                    <%--<tags:button id="add-nf" disabled="disabled" type="submit" value="Import" size="small" color="green" icon="check" />--%>
                <%--</div>--%>
            <%--</form:form>--%>

        <%--</chrome:division>--%>
    <%--</csmauthz:accesscontrol>--%>
</chrome:box>
</body>
</html>
