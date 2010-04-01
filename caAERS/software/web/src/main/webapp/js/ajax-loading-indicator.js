//will handle the exception occured in DWR method calls. 
function handleDWRError(err){
    try {
	   hideDWRLoadingIndicator();
       caaersLog(err);
    } catch(e) {
    }
}

//----------------------------------------------------------------------------------------------------------------------
//will show indicator for DWR method calls. 
function showDWRLoadingIndicator(){
/*
    try {
     var elIndicator =  $('ajax-loading-indictor');
	 if(elIndicator)
	 	elIndicator.show();
    } catch(e) {
    }
*/
}

//---------------------------------------------------------------------------------------------------------------------
//will hide indicator for DWR method calls. 
function hideDWRLoadingIndicator(){
/*
    try {
        var elIndicator =  $('ajax-loading-indictor');
		if(elIndicator)
			elIndicator.hide();
    } catch(e) {
    }
*/
}

//---------------------------------------------------------------------------------------------------------
//will register ajax handlers , calendars, SSO
Event.observe(window, "load", function() {
	
	//hide the dwr indicator
	hideDWRLoadingIndicator();
	
	//AJAX handlers
// BJ: commented based on discussion in Scrum	
//	Ajax.Responders.register({
// 		onCreate: function() {
//			if(Ajax.activeRequestCount > 0)
//				showDWRLoadingIndicator();
//		 },
//		onComplete: function() {
//			if(Ajax.activeRequestCount == 0)
//				hideDWRLoadingIndicator();
//		}
//	}); 
	
	//calendars
	AE.registerCalendarPopups();
	
	//SSO
    $$("a.sso").each(function(a) {
        Event.observe(a, "click", function(e) {
            Event.stop(e)
            var ssoForm = $('sso-form')
            ssoForm.action = a.href
            ssoForm.submit()
        })
    })
});