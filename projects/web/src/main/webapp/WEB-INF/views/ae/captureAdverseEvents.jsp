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
div.row div.value {
font-weight:normal;
	white-space: normal;
}
</style>
 
 <script>
 	var descArray = new Array();
 	var win;
 	var index;
 	var selectElement;
 	
 	//from here for autopopulator
 	
 	var catSel = null;
 	var CategorySelector = Class.create();
 	Object.extend(CategorySelector.prototype, {
		initialize: function(meddra, ver, ignoreOtherSpecify) {
			this.win = null;
			this.isMeddra = meddra;
			this.version = ver;
			this.ignoreOtherSpecify = ignoreOtherSpecify;
			
			Event.observe("edit_button", "click", function() {
				var reportingPeriodId = document.getElementById('adverseEventReportingPeriod').value;
				displayReportingPeriodPopup(reportingPeriodId);
			 })
		},
		
		showWindow:function(wUrl, wTitle, wWidth, wHeight){
			win = new Window({className:"alphacube", destroyOnClose:true,title:wTitle,  width:wWidth,  height:wHeight, 
			onShow:this.show.bind(this),
			onBeforeShow:this.beforeShow.bind(this)
			});
			this.win = win;
			win.setContent('chooseCategory');
			win.show(true);
			
		},
		initializeAutoCompleter: function() {
			AE.createStandardAutocompleter('termCode', 
            		function(autocompleter, text){
            			if(this.isMeddra){
            				createAE.matchLowLevelTermsByCode(this.version,text, function(values) {
            					if(catSel.ignoreOtherSpecify){
                    				var vals = [];
                    				values.each(function(aterm){
                        				if(aterm.fullName.indexOf('Other (Specify') < 0){
                        					 vals.push(aterm);
                    					}
                        			});
                    				autocompleter.setChoices(vals);
                				}else{
                					autocompleter.setChoices(values);
                    			}								
							});
            			}else{
            				createAE.matchTerms(text, this.version, '', 25 , function(values){
                				if(catSel.ignoreOtherSpecify){
                    				var vals = [];
                    				values.each(function(aterm){
                        				if(aterm.fullName.indexOf('Other (Specify') < 0){
                        					 vals.push(aterm);
                    					}
                        			});
                    				autocompleter.setChoices(vals);
                				}else{
                					autocompleter.setChoices(values);
                    			}
            				});
            			}
            		},
            		function(aterm) {
            			return aterm.fullName;
            		}
            	);
		},
		finishSingleTermSelection:function(){
			var selTermMap = new Hash();
			var termElement = $('termCode');
			var termElementInput = $('termCode-input');
			
			var termId = termElement.getValue();
			if(termId) selTermMap.set(termId, termElementInput.getValue());
			
			termElement.clear();
			termElementInput.clear();
			
			//${callbackFunctionName}(selTermMap); //need to refactor, this is a rude way of calling a function
			myCallback(selTermMap);
		},
		finishMultiTermsSelection:function() {
			var terms = $('terms');
			var categories = $('categories');
			
			var opts = terms.options;
			
			var selTermMap = new Hash();
			//each over iterator is not working, dont know why.
			if(opts.length > 0) {
				for(i = 0; i< opts.length; i++){
					if(opts[i].selected) selTermMap.set(opts[i].value, opts[i].text);
				}
			}
			Windows.close(this.win.getId());
			//reset the category and terms
			terms.options.length=0;
			categories.selectedIndex = -1;
			//call the call back
			//${callbackFunctionName}(selTermMap); //need to refactor, this is a rude way of calling a function
			myCallback(selTermMap);
		},
		beforeShow : function(){
			
		},
		show: function(){
			
		},
		showTerms: function(el, ignoreOtherSpecify){
			catIds = $(el).getValue();
			var terms = $('terms');
			terms.options.length=0;
			
			/* BiJu : Optimize this to make single call instead of multiple */
			
			catIds.each(function(catId){
				 createAE.getTermsByCategory(catId, function(ctcTerms) {
				 	ctcTerms.each(function(ctcTerm) {
				 		if(!(ignoreOtherSpecify && ctcTerm.fullName.indexOf('Other (Specify')  > 0) ){
                       		var opt = new Option(ctcTerm.fullName, ctcTerm.id)
                       		terms.options.add(opt);
				 		}
                   })
				 });		
			});
		}
				
 	});
 	
 	//till here for autopopulator
 	
 	function addedReportingPeriod(periodId, periodName){
 		win.hide();
 		var length = selectElement.options.length;

		if(selectElement.options[selectElement.selectedIndex].value != periodId){
			var selElement = document.getElementById('adverseEventReportingPeriod');
			var optionNew = document.createElement('option');
			optionNew.text = periodName;
			optionNew.value = periodId;
			var optionOld = selElement.options[selectElement.options.length - 1];
			selectElement.add(optionNew, optionOld);
			selectElement.selectedIndex = selectElement.options.length - 2;
		}
		
		loadReportingPeriod(true);	
 	}
 	
 	function displayReportingPeriodPopup(reportingPeriodId){
 		var url='';
 		if(reportingPeriodId == '')
 			url = "<c:url value="/pages/ae/createReportingPeriod?studyId=${command.assignment.studySite.study.id}&participantId=${command.assignment.participant.id}&subview="/>";
 		else{
 			//url = "<c:url value="/pages/ae/createReportingPeriod?studyId=${command.assignment.studySite.study.id}&participantId=${command.assignment.participant.id}&id=${command.adverseEventReportingPeriod.id}&subview="/>";
 			url="/caaers/pages/ae/createReportingPeriod?studyId=${command.assignment.studySite.study.id}&participantId=${command.assignment.participant.id}&id=" + reportingPeriodId + "&subview=";
		}
		win = new Window({className:"alphacube", destroyOnClose:true, title:"Reporting Period Information",  width:700,  height:525, 
			url: url, top: 0, left: 300});
		win.show(true);
	}
	
	function isTermAgainAdded( termID )
    {
      var listOfTermIds = $$('.eachRowTermID');
      for(var i = 0 ; i < listOfTermIds.length ; i++)
      {
        if( termID == listOfTermIds[i].value)
        {
          return true;
        }      
      } 
      return false;
    }
	
	function myCallback(selectedTerms){
		var listOfTermIDs = new Array();
	    var listOfTerms = new Array();
	  	
	  		$H(selectedTerms).keys().each( function(termID) {
	  		
	  		var term = $H( selectedTerms ).get(termID);
	  		if( !isTermAgainAdded(termID))
	  		{
	  		  listOfTermIDs.push( termID );
	  		  listOfTerms.push(term );
	        }
	  	   });
	  	   
	  	   createAE.addObservedAE(listOfTermIDs, listOfTerms, function(responseStr)
	  	   {
	  	   	new Insertion.After('observedBlankRow', responseStr);
	  	   });
	}
	
	function loadReportingPeriod(updateReportingPeriodSelector){
		if(selectElement.selectedIndex == selectElement.options.length - 1){
			var reportingPeriodId = '';
			displayReportingPeriodPopup(reportingPeriodId);
		}else{
			var selectedId = document.getElementById('adverseEventReportingPeriod').value;
			
			createAE.displayDetailsSection(0, selectedId, function(str){
				var detailElement = document.getElementById('detailSection');
				detailElement.innerHTML = str;
				new Effect.toggle('detailSection', 'slide', {afterFinish: function (obj) { new
					Effect.Appear('detailSection') }})
				AE.registerCalendarPopups('detailSection');
				var isMeddra = ${not empty command.study.aeTerminology.meddraVersion};
				var version = ${not empty command.study.aeTerminology.meddraVersion ? command.study.aeTerminology.meddraVersion.id : command.study.aeTerminology.ctcVersion.id};	
				catSel = new CategorySelector(isMeddra, version, true);
 	 			
			});
		}
	}
	
	function showCategoryBox(){
 		catSel.showWindow('<c:url value="/pages/selectCTCTerms" />', '${title}', 800, 380 );
 	}
	
	Event.observe(window, "load", function(){
		selectElement = document.getElementById('adverseEventReportingPeriod');
		var optn = document.createElement("OPTION");
		optn.text = 'Create New';
		optn.value = '-1';
		selectElement.options.add(optn);
		
		if(${command.adverseEventReportingPeriod.id != null})
			Event.observe("edit_button", "click", function() {
			var reportingPeriodId = ${command.adverseEventReportingPeriod.id}
			displayReportingPeriodPopup(reportingPeriodId);
			 })
		
		Event.observe("adverseEventReportingPeriod", "change", function(){
			loadReportingPeriod(false);
		})
})           

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
								<c:if test='${command.adverseEventReportingPeriod != null}'>
    							<input id="edit_button" type="button" value="Edit"/>
								</c:if>
						</jsp:attribute>
					</tags:renderRow>
      		</div>
      		
      		<div style="display: none" id="detailSection">
       		</div>
       </jsp:attribute>
    </tags:tabForm>
 </body>
</html>