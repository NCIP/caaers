<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>Not implemented</title>
    <tags:dwrJavascriptLink objects="reportDef"/>
    <script>
        var win;
        Event.observe(window, "load", function() {
            //Calls ReportDefinitionAjaxFacade:matchOrganization(..)
            AE.createStandardAutocompleter('reportDefinition.organization',
                    function(autocompleter, text) {
                        reportDef.restrictOrganization(text, function(values) {
                            autocompleter.setChoices(values)
                        })
                    },
                    function(organization) {
                        var image;
                        if (organization.externalId != null) {
                            image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>';
                        } else {
                            image = '';
                        }

                        var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
                        return image + organization.name + nciInstituteCode;
                    });

            //populate the name of the associated organization in 'organization-input' field
            $('reportDefinition.organization-input').value = '${command.reportDefinition.organization.fullName}';

            Event.observe($('reportDefinition.organization-input'), "blur", function() {
                fetchParentReportDefinitions($('reportDefinition.organization').value);
            });

            Event.observe($('reportDefinition.reportType'), "change", function() {
                if ($('reportDefinition.reportType').options[$('reportDefinition.reportType').selectedIndex].value == -1) {
                    newReportTypePopup();
                }
            });

            function newReportTypePopup() {
                url = "createReportType",
                win = new Window({className:"alphacube",
                    destroyOnClose:true,
                    url: url,
                    title:"",
                    width: 500,
                    height: 300,
                    recenterAuto: true,
                    closable: true,
                    resizable: false,
                    maximizable: false,
                    minimizable: false
                });

                win.showCenter(true);
            }
            
            function fetchParentReportDefinitions(organizationID) {
                reportDef.fetchReportDefinitionsByOrganizationName(organizationID, ${command.reportDefinition != null && command.reportDefinition.id > 0 ? command.reportDefinition.id : 0}, function(output) {
                    var len = output.objectContent.length;
                    $('reportDefinition.parent').options.length = 0;

/*
                    alert(len);
                    alert(organizationID);
*/

                    opt = new Option('Please select', '');
                    $('reportDefinition.parent').options.add(opt);
                    for (i=0; i<len; i++) {
                        var repDef = output.objectContent[i];
                        opt = new Option(repDef.name, repDef.id);
                        $('reportDefinition.parent').options.add(opt);
                    }

                });
            }

            opt = new Option('Create new...', '-1');
            $('reportDefinition.reportType').options.add(opt);
        });
        function refreshReportTypes() {
            reportDef.fetchReportTypes(function(output) {
                var len = output.objectContent.length;
                $('reportDefinition.reportType').options.length = 0;

                for (i=0; i<len; i++) {
                    var repType = output.objectContent[i];
                    var opt = new Option(repType.name, repType.id);
                    $('reportDefinition.reportType').options.add(opt);
                }
                opt = new Option('Create new...', '-1');
                $('reportDefinition.reportType').options.add(opt);
            });
        }
      
    </script>
<link type="image/x-icon" href="../../../images/caaers.ico" rel="shortcut icon"/>   
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}">
		<jsp:attribute name="singleFields">
        <tags:instructions code="createreportdefinition" />
			<div>
			<tags:renderRow field="${fieldGroups.reportDefinitionOrganization.fields[0]}" />
			</div>
            <div id="ruleset-fields">
                <c:forEach items="${fieldGroups.reportDefinitionFieldGroup.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                </c:forEach>
               <input type="hidden" name="lastPointOnScale" value=""/>
               <input type="hidden" name="notificationType" value="EMAIL_NOTIFICATION" />
            </div>
		</jsp:attribute>
	</tags:tabForm> 

</body>
</html>