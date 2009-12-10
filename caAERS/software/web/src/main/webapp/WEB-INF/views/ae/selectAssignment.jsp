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
          width: 80%;
          margin-left: 8em;
        }
		.selection {
			display:none;
			background:#dbe9ff;
			border:1px solid #6e8bb8;
			color:#2a4876;
			font-style:italic;
			width:449px;
			margin-top:5px;
			margin-bottom:15px;
			padding:3px;
		}
    </style>
    
    <tags:dwrJavascriptLink objects="createAE,captureAE"/>
    <script type="text/javascript">
    var rpCreator = null;
    
    var RPCreatorClass = Class.create();
    Object.extend(RPCreatorClass.prototype, {
        /*
         rpCtrl - ID of the reporting period control. The option 'Create New' will be added to this control.
         rpDetailsDiv - The DIV element where the content of selected reporting period is shown.
         */
        initialize : function(rpCtrl, rpEditCtrl) {

            this.win = null;
            this.rpCtrl = $(rpCtrl);
            this.rpEditCtrl = $(rpEditCtrl);

            this.showOrHideEditRPCtrl(); //determine edit-button visiblility

            Event.observe(this.rpCtrl, 'change', this.rpCtrlOnChange.bindAsEventListener(this));
            Event.observe(this.rpEditCtrl, 'click', this.rpEditCtrlClick.bindAsEventListener(this));
        },

        displayRPPopup:function() {
            //will show the reporting period creation popup
            rpId = this.rpCtrl.value;
            sId = $('study').value;
            pId = $('participant').value;
            url = "createReportingPeriod?studyId=#{studyId}&participantId=#{participantId}&id=#{id}&subview".interpolate({studyId:sId , participantId:pId, id:rpId});
            this.win = new Window({className:"alphacube",
                destroyOnClose:true,
                title:"",
                url: url,
                width: 900,
                height: 500,
                recenterAuto:true});
            this.win.showCenter(true);
        },
        
        refreshRPCrlOptionsOnCreation:function(newRPId, rpName) {
        	// add the newly created course to the course dropdown
        	var cntOptions = this.rpCtrl.options.length;
            this.rpCtrl.options[cntOptions - 1] = new Option(rpName, newRPId);
            this.rpCtrl.selectedIndex = cntOptions - 1;
            this.addOptionToSelectBox(this.rpCtrl, 'Create New', '-1');
            this.showOrHideEditRPCtrl();
            $('created-message').style.display = '';
         },
         
         refreshRPCrlOptionsOnEdit:function(RPId, rpName) {
         	this.rpCtrl.options[this.rpCtrl.selectedIndex].text = rpName;
         	this.showOrHideEditRPCtrl();
         	$('edited-message').style.display = '';
         },

        addOptionToSelectBox:function(selBox, optLabel, optValue) {
            //adds the option to specified select box.
            opt = new Option(optLabel, optValue);
            selBox.options.add(opt);
        },
		rpCtrlSetValue : function(val){
        	$A(this.rpCtrl.options).each(function(opt){
            	if(opt.value == val) opt.selected = true;
        	});
        },
        rpCtrlOnChange : function() {
            if (this.rpCtrl.value == -1) {
                this.displayRPPopup(); //create reporting period flow
            }
            this.showOrHideEditRPCtrl();
            $('created-message').style.display = 'none';
            $('edited-message').style.display = 'none';
        },
        
        rpEditCtrlClick:function() {
            if (this.rpCtrl.value > 0) this.displayRPPopup();

        },

        showOrHideEditRPCtrl:function() {
        	$('adverseEventReportingPeriod').value = this.rpCtrl.value;
            //the edit reporting period button show/hide based on select box value
            if (this.rpCtrl.value > 0) {
                this.rpEditCtrl.show();
                this.fetchCourseDetails();
                $('course-details').show();
            } else {
                this.rpEditCtrl.hide();
                $('course-details').hide();
            }
        },
        
        fetchCourseDetails:function() {
        	captureAE.fetchCourseDetails($("adverseEventReportingPeriod").value, function(output){
        		var course = output.objectContent;
        		$('start-date-value').innerHTML = course.startDate;
        		$('end-date-value').innerHTML = course.endDate;
        		$('cycle-number-value').innerHTML = course.cycleNumber;
        		$('treatment-assignment-value').innerHTML = course.tacCode;
        		$('treatment-description-value').innerHTML = '<div class="label">Treatment description</div> <div class="value">' + course.tacDescription + '</div>';
        	});
        },
        
        clearRPCrlOptions:function(){
        	// If the value in study or participant is cleared, then the course dropdown should be cleared.
        	// This method takes care of clearing the contents of the course dropdown.
        	this.rpCtrl.options.length = 0;
        	this.addOptionToSelectBox(this.rpCtrl, 'Please select', '');
        	$('course-details').hide();
        	$('adverseEventReportingPeriod').value ='';
        	this.rpCtrl.value='';
        },
        
        populateRPCrlOptions:function(defaultVal){
        	// If Both Study and Participant are selected then make the Ajax call to fetch course informtion
        	// and populate the Course dropdown with the fetched values.
        	if(this.isStudyParticipantSelected()){
        		rpCreator.rpCtrl.disable();
        		captureAE.fetchCourses($("study").value, $("participant").value, function(output){
        			rpCreator.clearRPCrlOptions();
					var i = 0;
					for(i = 0; i< output.objectContent.length; i++){
						var status = output.objectContent[i];
						rpCreator.addOptionToSelectBox(rpCreator.rpCtrl, status.name, status.id);
					}
					rpCreator.addOptionToSelectBox(rpCreator.rpCtrl, 'Create New', '-1')
					rpCreator.rpCtrl.enable();

					//select the default value
					if(defaultVal){
						this.rpCtrlSetValue(defaultVal);
						this.showOrHideEditRPCtrl();
					}
        		}.bind(this));
        	}
        },
        
        isStudyParticipantSelected:function(){
        	if( $("study").value == "" || $("participant").value == "")
        		return false;
        	else
	        	return true;
        }
    
    });
    
        var participantAutocompleterProps = {
            basename: "participant",
            populator: function(autocompleter, text) {
                createAE.matchParticipants(text, $('study').value, function(values) {
                    autocompleter.setChoices(values);
                })
            },
            valueSelector: function(obj) {
                return obj.displayName;
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
            Element.removeClassName($(mode.basename + "-input"), "valueOK");
            Element.addClassName($(mode.basename + "-input"), "validField");
            Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
            $(mode.basename).value = selectedChoice.id;
            $(mode.basename + '-selected').show()
            new Effect.Highlight(mode.basename + "-selected")
            rpCreator.populateRPCrlOptions();
            $(mode.basename + "-input").onblur = function() {
                if ($(mode.basename + "-input").hasClassName('validField')) {
                    ValidationManager.setNormalState($(mode.basename + "-input"));
                } else {
                    ValidationManager.setInvalidState($(mode.basename + "-input"));
                }
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
            //rpCreator.populateRPCrlOptions();
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
                Element.addClassName($(mode.basename + "-input"), "required");
                Element.removeClassName($(mode.basename + "-input"), "validField");
                Element.removeClassName($(mode.basename + "-input"), "valueOK");
                $(mode.basename + "-selected").hide()
                $(mode.basename).value = ""
                $(mode.basename + "-input").value = ""
                rpCreator.clearRPCrlOptions();
            })
        }

        Event.observe(window, "load", function() {
            acCreate(participantAutocompleterProps);
            acCreate(studyAutocompleterProps);
            updateSelectedDisplay(participantAutocompleterProps);
            updateSelectedDisplay(studyAutocompleterProps);
            initSearchField();
            rpCreator = new RPCreatorClass('course-input','edit_button');
            $('adverseEventReportingPeriod').value = '${command.adverseEventReportingPeriod.id }';
            rpCreator.populateRPCrlOptions(${command.adverseEventReportingPeriod.id});
           
    		//remove the query string from form url
    		removeQueryStringFromForm('command');
    		
        })
    </script>
</head>
<body>



<form:form method="post" cssClass="standard autoclear">
    <tags:tabFields tab="${tab}"/>
    <div class="autoclear" id="criteria-div">
    	<chrome:box title="Select study, subject, and course/cycle" id="study-entry">
    		<tags:instructions code="instruction_ae_assignmentNote"/>
    		<div class="row">
	            <%--<tags:instructions code="instruction_ae_select_study"/>--%>
	            <form:hidden path="study"/>
				<div class="label">
	            	<tags:requiredIndicator/> Study
				</div>
				<div class="value">
		            <input type="text" id="study-input" value="${command.study.shortTitle}" class="autocomplete"/>
					<a id="study-clear" style="cursor:pointer"><img src="<chrome:imageUrl name="../clear-left-button.png" />" alt="Clear" /></a>
		            <tags:indicator id="study-indicator"/>
		            <tags:errors path="study"/>
		            <div id="study-choices" class="autocomplete"></div>
				</div>
	            <div class="value selection" id="study-selected" style="display: none">

	                	You have selected the study <span id="study-selected-name"></span>.

				</div>
			</div>
			<div class="row">
	            <%--<tags:instructions code="instruction_ae_select_subject"/>--%>
	            <form:hidden path="participant"/>
				<div class="label">
	            	<tags:requiredIndicator/> Subject
				</div>
				<div class="value">
		            <input type="text" id="participant-input" value="${command.participant.fullName}" class="autocomplete"/>
		            <a id="participant-clear" style="cursor:pointer"><img src="<chrome:imageUrl name="../clear-left-button.png" />" alt="Clear" /></a>
		            <tags:indicator id="participant-indicator"/>
		            <div id="participant-choices" class="autocomplete"></div>
		            <tags:errors path="participant"/>
				</div>
			
            <div class="value selection" id="participant-selected" style="display: none">

            		You have selected the subject <span id="participant-selected-name"></span>.

            </div>
			</div>
			<div class="row">
				
	        	<%--<tags:instructions code="instruction_ae_select_course"/>--%>
	        	<form:hidden path="adverseEventReportingPeriod" />
				<div class="label">
	        		<tags:requiredIndicator/> Course/cycle
				</div>
				<div class="value">
	        	<select id="course-input" style="width:20em" class="required">
					<option value="">Please select</option>
				</select>
			
	            <tags:button id="edit_button" type="button" value="Edit" color="blue" icon="edit" size="small"/>
				<div style="display:none" id="created-message"><b><font color="green">Course/Cycle created successfully</font></b></div>
				<div style="display:none" id="edited-message"><b><font color="green">Course/Cycle details saved successfully</font></b></div>
				</div>
	            <div id="course-details" class="value selection" style="display:none">
					<div class="row">
							<div class="label">Start date</div>
							<div class="value" id="start-date-value"></div>
						</div>	
						<div class="row">
							<div class="label">End date</div>
							<div class="value" id="end-date-value"></div>
						</div>	
						<div class="row">
							<div class="label">Cycle #</div>
							<div class="value" id="cycle-number-value"></div>
						</div>	
						<div class="row">
							<div class="label">Treatment code</div>
							<div class="value" id="treatment-assignment-value"></div>
						</div>
						<div class="row" id="treatment-description-value">

						</div>
					</div>
				</div>
				<tags:errors path="adverseEventReportingPeriod"/>
				
			</div>
        </chrome:box>
    </div>
    <c:choose>
        <c:when test="${empty tab}">
            <tags:tabControls tabNumber="${0}" isLast="${false}" willSave="${false}"/>
        </c:when>
        <c:otherwise>
            <tags:tabControls tab="${tab}" flow="${flow}" willSave="${false}"/>
        </c:otherwise>
    </c:choose>
</form:form>
</body>
</html>