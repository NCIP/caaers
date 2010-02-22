AE.clickSrc;
AE.tabbedFlowUpdateTarget = function(evt) {

    AE.checkForModification = false;

    if(!AE.SUBMISSION_INPROGRESS){
    	AE.SUBMISSION_INPROGRESS = true;
    	var a = Event.element(evt)
        var tabclass = Element.classNames(a).detect(function(cls) { return cls.slice(0, 3) == "tab" })
        var targetPage = tabclass.slice(3)
        $('_target').name = "_target" + targetPage
        if(Prototype.Browser.IE){
        	if ($('command')._finish) $('_finish').disable()
        }else{
        	if ($('command')._finish) $('command')._finish.disable()
        }
        showDWRLoadingIndicator();
    }else{
    	//stop the event.
    	 Event.stop(evt);
    }
}


AE.tabbedFlowDisableTarget = function(evt) {

    AE.checkForModification = false;

	if(!AE.SUBMISSION_INPROGRESS){
		AE.SUBMISSION_INPROGRESS = true;
	    showDWRLoadingIndicator();
	}else{
    	//stop the event.
   	 	Event.stop(evt);
   }
}

AE.tabbedFlowSelectAndSubmit = function(click) {
    Event.stop(click)
    if(!AE.SUBMISSION_INPROGRESS){
    	AE.tabbedFlowUpdateTarget(click)
    	$('command').submit() // command is the default ID for a form created with form:form
    }
   
}


Event.observe(window, "load", function() {
    $$("li.tab a").each(function(a) {
        Event.observe(a, "click", AE.tabbedFlowSelectAndSubmit)
    })
    if ($("flow-prev")) Event.observe("flow-prev", "click", AE.tabbedFlowUpdateTarget)
    if ($("flow-update")) Event.observe("flow-update", "click", AE.tabbedFlowUpdateTarget)
    if ($("flow-next")) Event.observe("flow-next", "click",  AE.tabbedFlowDisableTarget)
})

//updates the forms action, by chopping off the query string in action.
function removeQueryStringFromForm(frm){
	var frmObj = $(frm)
	var _action = frmObj.action;
	var queryIndex = _action.indexOf('?');
	if(queryIndex > 0){
		var _newAction = _action.substring(0, queryIndex);
		frmObj.action = _newAction;
	}
}