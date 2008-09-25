<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<script type="text/javascript" src="/caaers/js/dropdown_menu.js"></script>
<html>
 <head>
 <tags:includePrototypeWindow />
 <tags:stylesheetLink name="ae"/>
 <tags:dwrJavascriptLink objects="captureAE,createStudy,createAE"/>
 <tags:stylesheetLink name="aeTermQuery_box" />

<style type="text/css"> 
.selectdiv
{	
	width:170px;
	overflow: hidden;
}
.shortselectdiv
{	
	width:115px;
	overflow: hidden;
}
 .selectbox
{	
	width:165px;
}
.shortselectbox
{	
	width:110px;
}

.selectboxClick{
	width:750px;
}

.divNotes,.divOtherMeddra{
	font-size:8pt;
margin-top:5px;
float:left;
}
/* Override basic styles */
div.row div.value {
	font-weight:normal;
	white-space: normal;
	margin-left: 13em;
}

 div.row div.label { width: 12em; } 
		 
/* division where reporting period combo box is shown */
.reportingPeriodSelector{

}
.ae-section{
	padding-top: 5px;
}
#contentOf-observedID .even{
background-color:#e8e8ff;
}
#contentOf-solicitatedID .odd{
background-color:#ffe2ff;
}
.thterm{
	position:absolute;
	left: 10px;
	top: 10px;
	}
#boxholder{
position:relative;
height:210px;
width:100%;
border-top:1px solid #0066ff;
padding-top:10px;
}
#gradehead{
position:absolute;
left:10px;
top:75px;
}
#attributionhead{
position:absolute;
left:350px;
top:75px;
}
#hospitalizationhead{
position:absolute;
left:500px;
top:75px;
}
#expectedhead{
position:absolute;
left:10px;
top:145px;
}
#serioushead{
position:absolute;
left:180px;
top:145px;
}
/*Grade*/
.selectbox0{
position:absolute;
left:10px;
top:95px;
max-width:300px;
}
/*Attribution*/
.selectbox1{
position:absolute;
left:350px;
top:95px;
}
/*Hospitalization*/
.selectbox2{
position:absolute;
left:500px;
top:95px;
}
/*Expected*/
.selectbox3{
position:absolute;
left:10px;
top:165px;
}
/*Serious*/
.selectbox4{
position:absolute;
left:180px;
top:165px;
}
.delete{
position:absolute;
right:20px;
}

</style>
 
 <script>

 	var RPCreatorClass = Class.create();
 	Object.extend(RPCreatorClass.prototype, {
 	 	/*
 	 		rpCtrl - ID of the reporting period control. The option 'Create New' will be added to this control.
 	 		rpDetailsDiv - The DIV element where the content of selected reporting period is shown.
 	 	*/
 	 	initialize : function(rpCtrl,rpDetailsDiv,rpEditCtrl,rpCtrlValue){
 	 	
 	 		this.win = null;
 	 		this.rpCtrl = $(rpCtrl);
 	 		this.rpCtrl.value = rpCtrlValue;
 	 		this.rpEditCtrl = $(rpEditCtrl);
 	 		this.rpDetailsDiv = $(rpDetailsDiv);
 	 		
			this.showOrHideEditRPCtrl(); //determine edit-button visiblility 
			
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
 	 	 		title:"",
 	 	 		width:700,  height:530, 
 				url: url, 
 				top: 30, left: 300});
 			this.win.show(true);
 		},
 		addOptionToSelectBox:function(selBox, optLabel, optValue){
 			//adds the option to specified select box.
 	 		opt = new Option(optLabel, optValue);
			selBox.options.add(opt);
 		},
 		rpCtrlOnChange : function(){
 	 		this.clearRPDetails(); //clear existing reporting period details
 	 		if(this.rpCtrl.value == -1){
 	 	 		this.displayRPPopup(); //create reporting period flow
 	 		}else if(this.rpCtrl.value){
				this.refreshRPCrlOptionsAndShowDetails(this.rpCtrl.value, true); //show the reporting period details and AEs	 	 	 		
 	 		}
 	 		
 		},
 		rpEditCtrlClick:function(){
 	 		if(this.rpCtrl.value > 0) this.displayRPPopup();
 	 	 			
 		},
 		showRPDetails:function(rpDetails){
 	 		//shows reporting period details , solicited and observed adverse events
 	 		Element.insert(this.rpDetailsDiv, rpDetails);
 	 		Effect.Appear(this.rpDetailsDiv);
 	 		this.showOrHideEditRPCtrl();
 	 		
 		},
 		clearRPDetails :function() {
 	 		//will clear the content of details section & properly unregister events
 	 		this.rpDetailsDiv.hide();
 	 		this.rpDetailsDiv.innerHTML="";
 	 		this.showOrHideEditRPCtrl();
 		},
 		showOrHideEditRPCtrl:function(){
 			//the edit reporting period button show/hide based on select box value
 	 		if(this.rpCtrl.value > 0){
 	 	 		 this.rpEditCtrl.show();
 	 	 	}else{
 	 	 	 	this.rpEditCtrl.hide();
 	 	 	}
 		},
 		refreshRPCrlOptionsAndShowDetails:function(newRPId, fetchOnlyDetails){
 	 		//will refresh the options of reporting period.
 	 		captureAE.refreshReportingPeriodAndGetDetails(newRPId, fetchOnlyDetails, function(ajaxOutput){
 	 	 		if(!fetchOnlyDetails){
 	 	 	 		//update the reporting period dropdown
 	 	 			this.rpCtrl.options.length = 1;
 	 	 			ajaxOutput.objectContent.each(function(rp){
 	 	 	 	 		 this.addOptionToSelectBox(this.rpCtrl,rp.name, rp.id);
 	 	 	 	 	}.bind(this));
 	 	 			this.addOptionToSelectBox(this.rpCtrl,'Create New', '-1');
 	 	 			this.rpCtrl.value = newRPId;	
 	 	 		}
 	 	 		
 	 	 		
	 	 	 	this.clearRPDetails();
 	 	 		this.showRPDetails(ajaxOutput.htmlContent);
                AE.registerCalendarPopups("detailSection");
              }.bind(this));
 		},
 		addAdverseEvents:function(selectedTerms){
 	 		//find the terms that are not already added in the page
 			var listOfTermIDs = new Array();
 		  	$H(selectedTerms).keys().each( function(termID) {
 		  		var term = $H( selectedTerms ).get(termID);
 		  		if( !this.isTermAgainAdded(termID)){
 		  		  listOfTermIDs.push( termID );
 		        }
 		  	}.bind(this));
 		  	//get the HTML to add from server   
 		  	captureAE.addObservedAE(listOfTermIDs, function(responseStr){
				$('observedBlankRow').insert({after:responseStr});
				if( $('observedEmptyRow')) $('observedEmptyRow').remove();
				this.initializeOtherMeddraAutoCompleters(listOfTermIDs);
 		  	}.bind(this));
 		},
 		isTermAgainAdded:function(termID){
 	 		//will tell wheter the term is already present
 			$$('.eachRowTermID').each(function(aTerm){
 	 			if(termID == aTerm.value()) return true;
 			});
 			return false;
 		},
 		initializeOtherMeddraAutoCompleters: function(listOfTermIDs){
 	 		listOfTermIDs.each(function(aTermId){
 	 	 		var acEls = $$('om'+aTermId);
 	 		}.bind(this));
 		},
 		deleteAdverseEvent:function(indx){
 	 		captureAE.deleteAdverseEvent(indx, function(ajaxOutput){
 	 	 		$('ae-section-' + indx).remove();
 	 		}.bind(this));
 	 	
 		}
 		 		
 	});

 	
 	/*
 		Create an instance of the RPCreatorClass, by passing 'adverseEventReportingPeriod' which is the ID of Reporting Period select element.
 	*/
 	var rpCreator = null; 
 	Event.observe(window, "load", function(){
 		var url = document.addRoutineAeForm.action;
 		var stripped_url = '';
 		var index = -1;
 		index = url.indexOf("?");
 		if(index != -1){
			stripped_url = url.substr(0,index);
			document.addRoutineAeForm.action = stripped_url;
		}
 		rpCreator = new RPCreatorClass('adverseEventReportingPeriod','detailSection','edit_button', '${command.adverseEventReportingPeriod.id}');
 		
 	});


 	

 </script>
 
</head>
 <body>

	 <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enteraes" formName="addRoutineAeForm">
      	
      	<jsp:attribute name="singleFields">
         <p><tags:instructions code="instruction_ae_adverseevents"/></p>
      		<input type="hidden" name="_action" id="_action" value="">
			<div id="reportingPeriodSelector">      	
      				<tags:renderRow field="${fieldGroups.reportingPeriodFG.fields[0]}">
						<jsp:attribute name="value">
								<tags:renderInputs field="${fieldGroups.reportingPeriodFG.fields[0]}" />
    							<input id="edit_button" type="button" value="Edit" style="display:none;"/>
						</jsp:attribute>
					</tags:renderRow>
      		</div>
      		<div id="detailSection">
				<c:if test="${not empty command.adverseEventReportingPeriod}">
					<ae:reportingPeriodAEDetails />
				</c:if>
       		</div>
       </jsp:attribute>
    </tags:tabForm>
 </body>
</html>