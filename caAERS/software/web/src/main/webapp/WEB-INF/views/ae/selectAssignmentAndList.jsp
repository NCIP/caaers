<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>${pageTitle}</title>
    <style type="text/css">
        input.autocomplete {
            width: 450px;
            font-style: normal;
            background-color: #CCE6FF;
        }

        input.pending-search {
            width: 450px;
            color: gray;
            font-style: italic;
            background-color: #CCE6FF;
        }

        #criteria-div{
        
        }
        
        .eXtremeTable .eXtremeTable {
    		border: 0 solid #6cafe9;
		}
        
    </style>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">

    	AE.checkForModification = false; 
        var participantAutocompleterProps = {
            basename: "participant",
            populator: function(autocompleter, text) {
                createAE.matchParticipants(text, $('study').value, function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {
                return obj.displayName
            }
        }

        var studyAutocompleterProps = {
            basename: "study",
            populator: function(autocompleter, text) {
                createAE.matchStudies(text, $('participant').value, ${command.ignoreCompletedStudy}, function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {
                return obj.displayName;
            }
        }

        function acPostSelect(mode, selectedChoice) {
            Element.removeClassName($(mode.basename + "-input"), "required");
            Element.addClassName($(mode.basename + "-input"), "validField");
            
            Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
            $(mode.basename).value = selectedChoice.id;
            $(mode.basename + '-selected').show()
            new Effect.Highlight(mode.basename + "-selected");

            $(mode.basename + "-input").onblur = function() {
                if ($(mode.basename + "-input").hasClassName('validField')) {
                    ValidationManager.setNormalState($(mode.basename + "-input"))
                };
            }
            $(mode.basename + "-input").onchange = function() {
                if (!$(mode.basename + "-input").hasClassName('validField')) {
                    ValidationManager.setInvalidState($(mode.basename + "-input"));
                }
            }
            
        }

        function updateSelectedDisplay(mode) {
            if ($(mode.basename).value) {
                Element.update(mode.basename + "-selected-name", $(mode.basename + "-input").value)
                $(mode.basename + '-selected').show()
            }
        }

        function acCreate(mode) {
            new Autocompleter.DWR(mode.basename + "-input", mode.basename + "-choices", mode.populator, {
                valueSelector: mode.valueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                    acPostSelect(mode, selectedChoice)
                },
                indicator: mode.basename + "-indicator",
                minChars : AE.autocompleterChars , 
                frequency : AE.autocompleterDelay
            })
            Event.observe(mode.basename + "-clear", "click", function() {
                Element.removeClassName($(mode.basename + "-input"), "valueOK");
                Element.removeClassName($(mode.basename + "-input"), "validField");
                Element.addClassName($(mode.basename + "-input"), "required");
                $(mode.basename + "-selected").hide()
                $(mode.basename).value = ""
                $(mode.basename + "-input").value = ""
            })
        }

        Event.observe(window, "load", function() {
            acCreate(participantAutocompleterProps)
            acCreate(studyAutocompleterProps)
            updateSelectedDisplay(participantAutocompleterProps)
            updateSelectedDisplay(studyAutocompleterProps)
            initSearchField()
        })
        
        function showDetails(elId){
		$(elId).show();
	}

	function hideDetails(elId){
		$(elId).hide();
	}
	
	function doAction(action, aeReportId, reportId) {
        try {
            AjaxResult = null;
            if (action == 'withdraw') {
                createAE.withdrawReportVersion(aeReportId, reportId, function(result) {
                    ajaxResult = result;
                    if (ajaxResult.error) {
                        caaersLog(ajaxResult.errorMessage);
                    } else {
                        var statusColumn = $("status" + reportId)
                        var statusColumnData = "<span class='submittedOn' ><i>Withdrawn <\/i><\/span>";
                        Element.update(statusColumn, statusColumnData);
                        updateDropDownAfterWithdraw(reportId);
                    }
                });
            } else if (action == 'submit') {
                var url = '<c:url value="/pages/ae/reviewResolver?from=list&ras=t&viewOnly=true" />' + '&aeReport=' + aeReportId + '&reportId=' + reportId;
                window.location = url;
            } else if (action == 'amend') {
                var url = '<c:url value="/pages/ae/edit"/>' + '?aeReport=' + aeReportId + '&report=' + reportId + '&action=amendReport';
                window.location = url;
            }
        } catch(e) {
            caaersLog(e);
        }
        
    }  

    function updateDropDownAfterWithdraw(reportId) {
        jQuery('#SUBMIT_' + reportId).remove();
        jQuery('#WITHDRAW_' + reportId).remove();
        createDropDowns();
    }

    function doIt(type, reportId, aeReportId, submissionURL) {
        executeAction(type, reportId, '<c:url value='/pages/ae/generateExpeditedfPdf?aeReport='/>' + aeReportId + '&reportId=' + reportId, aeReportId, submissionURL);
    }

    function executeAction(type, reportId, url, aeReportId, submissionUrl) {
            if(confirm('Are you sure you want to take the action ?')) {
                switch (type) {
                    case "notifyPSC": notifyPsc(aeReportId); break;
                    case "submit": doAction(type, aeReportId, reportId); break;
                    case "withdraw": doAction(type, aeReportId, reportId); break;
                    case "amend": doAction(type, aeReportId, reportId);  break;
                    case "adeers": window.open(submissionUrl, "_blank");  break;
                    default: window.open(url + "&format=" + type, "_self");
                }
            }else{
                return false;
            }
     }
     
     function notifyPsc(aeReportId) {
            AE.showIndicator("notify-indicator-" + aeReportId)
            createAE.pushAdverseEventToStudyCalendar(aeReportId, function(result) {
                AE.hideIndicator("notify-indicator-" + aeReportId)
                var unit = $("notify-unit-" + aeReportId)
                if (result) {
                    Element.update(unit, "Notified")
                    Element.addClassName(unit, "success")
                } else {
                    Element.update(unit, "Notification failed")
                    Element.addClassName(unit, "failure")
                }
            })
        }
        
     function notifyPscRoutineEvent(aeReportId) {

            AE.showIndicator("notify-indicator-routine-" + aeReportId)
            createAE.pushRoutineAdverseEventToStudyCalendar(aeReportId, function(result) {
                AE.hideIndicator("notify-indicator-routine-" + aeReportId)
                var unit = $("notify-unit-routine-" + aeReportId)
                if (result) {
                    Element.update(unit, "Notified")
                    Element.addClassName(unit, "success")
                } else {
                    Element.update(unit, "Notification failed")
                    Element.addClassName(unit, "failure")
                }
            })
        }

     function executeReportingPeriodActions(id){
 		var url = '<c:url value="/pages/ae/captureRoutine?participant=${command.participant.id}&study=${command.study.id}&_page=0&adverseEventReportingPeriod=' + id + '&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true"/>';
 		window.location = url; 
 	}

     function showToolTip(text, title) {
         Tip(text, WIDTH, 300, TITLE, title, SHADOW, false, FADEIN, 300, FADEOUT, 300, STICKY, 1, CLOSEBTN, true, CLICKCLOSE, true);
     }
        
    </script>
</head>
<body>
<form:form method="post" cssClass="standard autoclear">
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
    <tags:tabFields tab="${tab}"/>

    <div class="autoclear" id="criteria-div">
    	<chrome:box title="Select study and subject" id="study-entry" autopad="true" cssClass="pairedLong">
            <div class="row">
                <div class="label"><caaers:message code="LBL_Study" /></div>
                <div class="value">
                    <%--<p><tags:instructions code="instruction_ae_select_study"/></p>--%>
                    <form:hidden path="study"/>
                    <input type="text" id="study-input" value="${command.study.shortTitle}" class="autocomplete"/>
                    <a id="study-clear" style="cursor:pointer"><img src="<chrome:imageUrl name="../clear-left-button.png" />" alt="Clear" /></a>
                    <tags:indicator id="study-indicator"/>
                    <tags:errors path="study"/>
                    <div id="study-choices" class="autocomplete"></div>
                </div>
                <p id="study-selected" style="display: none">You have selected the study <span id="study-selected-name"></span>.</p>
            </div>
            <div class="row">
                <div class="label"><caaers:message code="LBL_Subject" /></div>
                <div class="value">
                    <form:hidden path="participant"/>
                    <input type="text" id="participant-input" value="${command.participant.fullName}" class="autocomplete"/>
                    <a id="participant-clear" style="cursor:pointer"><img src="<chrome:imageUrl name="../clear-left-button.png" />" alt="Clear" /></a>
                    <tags:indicator id="participant-indicator"/>
                    <div id="participant-choices" class="autocomplete"></div>
                    <tags:errors path="participant"/>
                </div>
                <p id="participant-selected" style="display: none">You have selected the subject <span id="participant-selected-name"></span>.</p>
            </div>
         	<div class="row">
	            <div class="label">
	            Report ID</div>
	            <div class="value">
	                <form:input type="text" path="searchIdentifier"/>
	            </div>
            </div> 
            <div class="row">
                <div class="label">Report Submission status</div>
                <div class="value">
                    <form:select path="reportStatus">
	            		<form:option value="" label="Please select" />
	            		<form:options items="${command.reportStatusOptionsMap}"/>
        			</form:select>  
                </div>
            </div>
           <div class="row">
                <div class="label">Data Entry status</div>
                <div class="value">
                    <form:select path="inputDataEntryStatus">
	            		<form:option value="" label="Please select" />
	            		<form:option value="Complete" label="Complete" />
	            		<form:option value="In-progress" label="In-progress" />
        			</form:select>  
                </div>
            </div> 
        </chrome:box>
    </div>
    <input type="hidden" name="paginationAction" value="firstPage"/>
	<input type="hidden" name="numberOfResultsPerPage" value="15"/>
    <div align="right">
    	<tags:button color="blue" type="submit" value="Find" size="small" icon="search" />
    </div>
</form:form>
  
<tags:standardForm title="Search Results">
<jsp:attribute name="singleFields">
  <c:if test="${fn:length(command.resultList) gt 0}">
	<c:if test="${not empty command.study}">
	  <div>
	    <div class="row">
	      <div class="summarylabel">Study</div>
	      <div class="summaryvalue shorty">(${command.study.primaryIdentifier.value}) ${command.study.longTitle}</div>
	    </div>
	</c:if>
	<c:if test="${not empty command.participant}">
	    <div class="row">
	      <div class="summarylabel">Subject</div>
	      <div class="summaryvalue shorty">(${command.participant == null ? '' : command.participant.primaryIdentifier })</div>
	    </div>
	</c:if>
    <tags:instructions code="instruction_manage_reports"/>
  
	<div class="eXtremeTable">
		  	<table><tr>
					<td style="padding-top:15px;padding-left:15px;">
	    				<tags:paginationControl isFirstPage="${isFirstPage}" isLastPage="${isLastPage}"/>
						<div style="color:#808080">
							${totalResults } results found, displaying ${startIndex } to ${endIndex }
						</div>
	  				</td>
				</tr></table>
		  <c:forEach items="${command.filteredResultMap}" var="entry">
		     <br/>
		 	 <c:choose>
		    	   <c:when test="${command.studyCentric}">
		           		<div> <b>Subject</b> ${entry.value[0].adverseEventReportingPeriod.participantSummaryLine}</div>
		           </c:when>
		           <c:when test="${command.participantCentric}">
		           		<div> <b>Study</b> ${entry.value[0].adverseEventReportingPeriod.studySummaryLine}</div>
		           </c:when>
		           <c:otherwise>
		           	  <div> <b>Study</b> ${entry.value[0].adverseEventReportingPeriod.studySummaryLine} </div>
		              <div> <b>Subject</b> ${entry.value[0].adverseEventReportingPeriod.participantSummaryLine}  </div>
		           </c:otherwise>
			    	   
			 </c:choose>
		  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableRegion">
		    <thead>
		      <tr align="center" class="label">
		        <td width="2%" class="tableHeader"></td>
		        <td width="18%" class="tableHeader">Course</td>
		        <td width="16%" class="centerTableHeader"># of Reports</td>
		        <td width="16%" class="centerTableHeader"># of AEs</td>
		        <td width="16%" class="tableHeader">Report Submission Status</td>
		        <td width="16%" class="tableHeader">Options</td>
		      </tr>
		    </thead>
					
		    	<c:forEach items="${entry.value}" var="mrp" varStatus="mrpStatus">
		    	 	<ae:oneListReportingPeriodRow manageReportsRepotingPeriodDTO="${mrp}" index="${mrpStatus.index}"/>
		    	</c:forEach>
		  </table>
		  </c:forEach>
	</div>
	<c:set var="reportingPeriodPageURLNoPeriod" value="/pages/ae/captureRoutine?participant=${command.participant.id}&study=${command.study.id}&_page=0&_target0=0&displayReportingPeriod=true"/>
</c:if>
<c:if test="${fn:length(command.resultList) le 0}">
   	<tags:instructions code="instruction_ae_no_courses"/> 
</c:if>
</jsp:attribute>
</tags:standardForm>

</body>
</html>
