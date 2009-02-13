<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<tags:includePrototypeWindow />
    <title>${pageTitle}</title>
    <style type="text/css">
        input.autocomplete {
            width: 75%;
            font-style: normal;
            background-color: #CCE6FF;
        }

        input.pending-search {
            width: 75%;
            color: gray;
            font-style: italic;
            background-color: #CCE6FF;
        }
        
        #criteria-div{
          width: 70%;
          margin-left: 10em;
        }
        
    </style>
    <c:if test="${empty tab}">
        <tags:stylesheetLink name="tabbedflow"/>
        <tags:javascriptLink name="tabbedflow"/>
    </c:if>
    <tags:includeScriptaculous/>
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
                width: 600,
                height: 450,
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
         },
         
         refreshRPCrlOptionsOnEdit:function(RPId, rpName) {
         	this.rpCtrl.options[this.rpCtrl.selectedIndex].text = rpName;
         },

        addOptionToSelectBox:function(selBox, optLabel, optValue) {
            //adds the option to specified select box.
            opt = new Option(optLabel, optValue);
            selBox.options.add(opt);
        },

        rpCtrlOnChange : function() {
            if (this.rpCtrl.value == -1) {
                this.displayRPPopup(); //create reporting period flow
            }
            this.showOrHideEditRPCtrl();
        },
        
        rpEditCtrlClick:function() {
            if (this.rpCtrl.value > 0) this.displayRPPopup();

        },

        showOrHideEditRPCtrl:function() {
            //the edit reporting period button show/hide based on select box value
            if (this.rpCtrl.value > 0) {
                this.rpEditCtrl.show();
                $('adverseEventReportingPeriod').value = this.rpCtrl.value;
            } else {
                this.rpEditCtrl.hide();
            }
        },
        
        clearRPCrlOptions:function(){
        	// If the value in study or participant is cleared, then the course dropdown should be cleared.
        	// This method takes care of clearing the contents of the course dropdown.
        	this.rpCtrl.options.length = 0;
        	this.addOptionToSelectBox(this.rpCtrl, 'Please Select', '');
        },
        
        populateRPCrlOptions:function(){
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
        		});
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
            Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
            $(mode.basename).value = selectedChoice.id;
            $(mode.basename + '-selected').show()
            new Effect.Highlight(mode.basename + "-selected")
            rpCreator.populateRPCrlOptions();
        }
        
        function updateSelectedDisplay(mode) {
            if ($(mode.basename).value) {
                Element.update(mode.basename + "-selected-name", $(mode.basename + "-input").value)
                $(mode.basename + '-selected').show()
            }
            //rpCreator.populateRPCrlOptions();
        }

        function acCreate(mode) {
            new Autocompleter.DWR(mode.basename + "-input", mode.basename + "-choices",
                    mode.populator, {
                valueSelector: mode.valueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                    acPostSelect(mode, selectedChoice)
                },
                indicator: mode.basename + "-indicator"
            })
            Event.observe(mode.basename + "-clear", "click", function() {
                $(mode.basename + "-selected").hide()
                $(mode.basename).value = ""
                $(mode.basename + "-input").value = ""
                rpCreator.clearRPCrlOptions();
            })
        }

        Event.observe(window, "load", function() {
            acCreate(participantAutocompleterProps)
            acCreate(studyAutocompleterProps)
            updateSelectedDisplay(participantAutocompleterProps)
            updateSelectedDisplay(studyAutocompleterProps)
            initSearchField()
            rpCreator = new RPCreatorClass('course-input','edit_button');
            rpCreator.populateRPCrlOptions();
        })
    </script>
</head>
<body>
<p>
    <tags:instructions code="instruction_ae_assignment"/>
</p>

<form:form method="post" cssClass="standard autoclear">
    <tags:tabFields tab="${tab}"/>
    <div class="autoclear" id="criteria-div">
        <chrome:box title="Select subject" id="participant-entry" autopad="true">
            <p><tags:instructions code="instruction_ae_select_subject"/></p>
            <form:hidden path="participant"/>
            <tags:requiredIndicator/>
            <input type="text" id="participant-input" value="${command.participant.fullName}" class="autocomplete"/>
            <input type="button" id="participant-clear" value="Clear"/>
            <tags:indicator id="participant-indicator"/>
            <div id="participant-choices" class="autocomplete"></div>
            <tags:errors path="participant"/>
            <p id="participant-selected" style="display: none">
                You have selected the subject <span id="participant-selected-name"></span>.
            </p>
        </chrome:box>
        <chrome:box title="Select study" id="study-entry" autopad="true">
            <p><tags:instructions code="instruction_ae_select_study"/></p>
            <form:hidden path="study"/>
            <tags:requiredIndicator/>
            <input type="text" id="study-input" value="${command.study.shortTitle}" class="autocomplete"/>
            <input type="button" id="study-clear" value="Clear"/>
            <tags:indicator id="study-indicator"/>
            <tags:errors path="study"/>
            <div id="study-choices" class="autocomplete"></div>
            <p id="study-selected" style="display: none">
                You have selected the study <span id="study-selected-name"></span>.
            </p>
        </chrome:box>
        <chrome:box title="Select course" id="course-entry" autopad="true">
        	<p><tags:instructions code="instruction_ae_select_course"/></p>
        	<form:hidden path="adverseEventReportingPeriod"/>
        	<tags:requiredIndicator/>
        	<select id="course-input" style="width:20em" value="${command.adverseEventReportingPeriod.id }">
				<option value="">Please select</option>
			</select>
			<input id="edit_button" type="button" value="Edit" style="display:none;"/>
			<tags:errors path="adverseEventReportingPeriod"/>
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