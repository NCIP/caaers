<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<!-- <link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>"> -->
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<script type="text/javascript" src="/caaers/js/dropdown_menu.js"></script>
<html>
 <head>
 <tags:includeScriptaculous />
 <tags:includePrototypeWindow />
 <tags:stylesheetLink name="ae"/>
 <tags:dwrJavascriptLink objects="createAE,createStudy"/>
 <tags:stylesheetLink name="pw_default" />
 <tags:stylesheetLink name="pw_alphacube" />
  <tags:stylesheetLink name="aeTermQuery_box" />

<style type="text/css"> 
 .selectbox
{	
	width:200px;
	behavior:expression(window.dropdown_menu_hack!=null?window.dropdown_menu_hack(this):0);
}
/* Override basic styles */
div.row div.value {
font-weight:normal;
	white-space: normal;
}
</style>
 
 <script>

 	var RPCreatorClass = Class.create();
 	Object.extend(RPCreatorClass.prototype, {
 	 	/*
 	 		rpCtrl - ID of the reporting period control. The option 'Create New' will be added to this control.
 	 		rpDetailsDiv - The DIV element where the content of selected reporting period is shown.
 	 	*/
 	 	initialize : function(rpCtrl,rpDetailsDiv,rpEditCtrl){
 	 	
 	 		this.win = null;
 	 		this.rpCtrl = $(rpCtrl);
 	 		this.rpEditCtrl = $(rpEditCtrl);
 	 		this.rpDetailsDiv = $(rpDetailsDiv);
 	 		
			showOrHideEditRPCtrl(); //determine edit-button visiblility 
			
 	 	 	this.addOptionToSelectBox(this.rpCtrl, 'Create New' , '-1');//add Create New option.
 	 		Event.observe(this.rpCtrl, 'change', this.rpCtrlOnChange.bindAsEventListener(this));
 	 		Event.observe(this.rpEditCtrl, 'click', this.rpEditCtrlClick.bindAsEventListener(this));
 		},
 		displayRPPopup:function(){
 			//will show the reporting period creation popup
 	 		rpId = this.rpCtrl.value;
 	 		url = "createReportingPeriod?assignmentId=#{assignmentId}&id=#{id}&subview".interpolate({assignmentId:"${command.assignment.id}" , id:rpId});
 	 		this.win = new Window({className:"alphacube", 
 	 	 		destroyOnClose:true, 
 	 	 		title:"Reporting Period Information",  
 	 	 		width:700,  height:530, 
 				url: url, 
 				top: 30, left: 300});
 			this.win.show(true);
 		},
 		addOptionToSelectBox:function(selBox, optLabel, optValue, isSecondLast){
 			//adds the option to specified select box.
 	 		opt = new Option(optLabel, optValue);
 	 		len = selBox.options.length;
			if(isSecondLast){
 	 			selBox.add(opt,selBox.options[len - 1]);
			}else{
				selBox.options.add(opt);
			}
 		},
 		rpCtrlOnChange : function(){
 	 		this.clearRPDetails(); //clear existing reporting period details
 	 		if(this.rpCtrl.value == -1){
 	 	 		this.displayRPPopup(); //create reporting period flow
 	 		}else if(this.rpCtrl.value){
				this.showRPDetails(); //show the reporting period details and AEs	 	 	 		
 	 		}
 	 		
 		},
 		rpEditCtrlClick:function(){
 	 		if(this.rpCtrl.value > 0) this.displayRPPopup();
 	 	 			
 		},
 		showRPDetails:function(){
 	 		//shows reporting period details , solicited and observed adverse events
 	 		this.rpDetailsDiv.show().defer();
 		},
 		clearRPDetails :function() {
 	 		//will clear the content of details section & properly unregister events
 	 		this.rpDetailsDiv.hide();
 	 		this.rpDetailsDiv.innerHTML = 'Fetching data from server......';
 		},
 		loadNewlyCreatedRP:function(id, name){
 			Windows.close(this.win.getId()); //closes the window.
 	 		this.addOptionToSelectBox(this.rpCtrl, name, id, true);
 	 		this.rpCtrl.selectedIndex = this.rpCtrl.options.length - 2;
 	 		this.showRPDetails(); //show the selected reporting period details
 		},
 		showOrHideEditRPCtrl:fumction(){
 			//the edit reporting period button show/hide based on select box value
 	 		if(this.rpCtrl.value > 0){
 	 	 		 this.rpEditCtrl.show();
 	 	 	}else{
 	 	 	 	this.rpEditCtrl.hide();
 	 	 	}
 		}
 		
 	});
 	
 	/*
 		Create an instance of the RPCreatorClass, by passing 'adverseEventReportingPeriod' which is the ID of Reporting Period select element.
 	*/
 	var rpCreator = null; 
 	Event.observe(window, "load", function(){
 		rpCreator = new RPCreatorClass('adverseEventReportingPeriod','detailSection','edit_button');
 		
 	});

 </script>
 
</head>
 <body>
	 <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enteraes" formName="addRoutineAeForm">
        <jsp:attribute name="instructions">
    	    <tags:instructions code="instruction_ae_enterBasics" />
        </jsp:attribute>
      	
      	<jsp:attribute name="singleFields">
      		<input type="hidden" name="_action" id="_action" value="">
			<div id="reportingPeriodSelector">      	
      				<tags:renderRow field="${fieldGroups.reportingPeriodFG.fields[0]}">
						<jsp:attribute name="value">
								<tags:renderInputs field="${fieldGroups.reportingPeriodFG.fields[0]}" />
    							<input id="edit_button" type="button" value="Edit" style="display:none;"/>
						</jsp:attribute>
					</tags:renderRow>
      		</div>
      		
      		<div style="display: none" id="detailSection">
       		</div>
       </jsp:attribute>
    </tags:tabForm>
 </body>
</html>