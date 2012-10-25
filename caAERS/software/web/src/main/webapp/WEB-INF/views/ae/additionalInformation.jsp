<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:css name="fileuploader/fileuploader"/>

    <style type="text/css">
        div.row div.label {
            width: 17em;
        }

        div.row div.value, div.row div.extra {
            margin-left: 18em;
        }

        table.tablecontent {
            border-collapse: separate;
        }

        .qq-upload-button {
            width: 200px;
        }

    </style>

    <tags:dwrJavascriptLink objects="createAE"/>
    <tags:slider renderComments="${command.associatedToWorkflow }" renderAlerts="${command.associatedToLabAlerts}"
                 reports="${command.selectedReportsAssociatedToWorkflow}"
                 display="${(command.associatedToWorkflow or command.associatedToLabAlerts) ? '' : 'none'}"
                 workflowType="report">
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
                <tags:labs labs="${command.assignment.labLoads}"/>
            </div>
    	</jsp:attribute>
    </tags:slider>
    <tags:js name="fileuploader.min"/>

    <script type="text/javascript">
        var routingHelper = new RoutingAndReviewHelper(createAE, 'aeReport');

        function deleteDocument(fileId, elementIdToRemove) {

            if (confirm("Are you sure you want to delete this?\n\n<caaers:message code="undoableDeleteAEOperation" />")) {
                new Ajax.Request("additionalInformationDocumentDelete?fileId=" + fileId, {
                    onSuccess:function (request) {
                        $(elementIdToRemove).remove()
                    }
                });
            }
        }

        Event.observe(window, "load", function () {

            //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if (associatedToWorkflow) {
            <c:forEach items="${command.selectedReportsAssociatedToWorkflow}" var="report" varStatus="status">
                routingHelper.retrieveReviewCommentsAndActions('${report.id}');
            </c:forEach>
            }

            $$("div.file_upload_form").each(function (es) {


                var uploader = new qq.FileUploaderBasic({
                    button:es,
                    action:'additionalInformationDocumentUpload',
                    params:{
                        CSRF_TOKEN:'${CSRF_TOKEN}',
                        aeReport:'${command.aeReport.id}',
                        additionalInformationDocumentType:$(es.id + "_file_type").getValue()
                    },
                    onSubmit:function (id, fileName) {

                        if ($(es.id + "_no_attachment")) {
                            $(es.id + "_no_attachment").remove();
                        }

                        $(es.id + "_files_item").insert({
                            'before':'<tr id="file-' + id + '" ><td valign="top">' + fileName + '</td><td id="file-' + id + '_progress"></td><td></td></tr>'
                        });
                    },
                    onUpload:function (id, fileName) {
                        $('file-' + id).addClassName('info-box')
                    },
                    onProgress:function (id, fileName, loaded, total) {
                        if (loaded < total) {
                            progress =   '  '+ Math.round(loaded / total * 100) + ' % of ' + Math.round(total / 1024) + ' kB';
                            $('file-' + id + '_progress').removeClassName('info-box')
                                    .update('Uploading ' + fileName + progress);
                        }
                    },
                    onComplete:function (id, fileName, responseJSON) {
                        if (responseJSON.success) {
                            var deleteIcon = "<img src='<c:url value="/images/buttons/button_icons/small/x_icon_small.png" />'>";

                            var downloadURL = '<a target="_blank" href="additionalInformationDocumentDownload?fileId=' + responseJSON.fileId + '">' + fileName + '</a>';
                            var deleteURL = '<a href="javascript:deleteDocument(\'' + responseJSON.fileId + '\',\'file-' + id + '\')">' + deleteIcon + '</a>';

                            $('file-' + id).removeClassName('info-box')
                                    .update('<tr id="file-' + id + '" ><td valign="top">' + downloadURL + '</td><td>' + responseJSON.fileSize + '</td><td>' + deleteURL + '</td></tr>');
                        } else {
                            $('file-' + id).removeClassName('info-box')
                                    .addClassName('error-box')
                                    .update('<i class="icon-exclamation-sign"></i> ' +
                                    'Error with ' + fileName +responseJSON.error);
                        }
                    }
                });
            })

        })
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section17attachments">
    <jsp:attribute name="instructions">
        <tags:instructions code="instruction_ae_additionalInfo"/>
    </jsp:attribute>
    <jsp:attribute name="singleFields">
    	<c:forEach items="${fieldGroups.desc.fields}" var="field" begin="0"
                   end="12" varStatus="status">
            <ae:oneListAdditionalInformation title="${field.displayName}"
                                             property="${field.propertyName}"
                                             documents="${documents}"
                                             show="true"
                                             listElementIndex="file_upload_form${status.index}"
                                             fileUpload="true"
                                             fileType="${field.propertyName}"
                                             canDeleteFiles="true">

                 <jsp:attribute name="inputField">
                    <tags:renderRow field="${field}">
                        <jsp:attribute name="label">
                            	<ui:label path="${field.propertyName}"
                                          text="${status.index==12?'':'Include'}"
                                          labelProperty="${status.index==12?'':'Include'}"
                                          mandatory="${field.attributes.mandatory}"
                                          required="${field.required}"/>

                        </jsp:attribute>
                    </tags:renderRow>
                </jsp:attribute>

            </ae:oneListAdditionalInformation>

        </c:forEach>
    	

        <div id="spacer" style="clear: both;"></div>



        <ae:reportingContext allReportDefinitions="${command.applicableReportDefinitions}"
                             selectedReportDefinitions="${command.selectedReportDefinitions}"/>
        <c:if test="${empty requestScope.cntRF or (requestScope.cntRF lt 1)}">
            <caaers:message code="LBL_aeReport.additionalInformation.noFields"
                            text="Additional information is not applicable for this report." var="na" scope="request"/>
            <chrome:warningMessage key="na"/>
        </c:if>
         <ae:zipAdditionalInformation additionalInformationId="${additionalInformationId}"/>

    </jsp:attribute>
</tags:tabForm>
</body>
</html>