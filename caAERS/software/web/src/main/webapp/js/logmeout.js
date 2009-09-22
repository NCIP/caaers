AE.SESSION_TIME_OUT_WARNING=15; //timeout duration in minutes
AE.SESSION_TIMED_OUT = false;
AE.SESSION_TIMER_ID;
AE.SESSION_TIME_OUT_ENABLED = true;
//----------------------------------------------------------------------------------------------------------------
// INITIALIZE THE TIMER
Event.observe(window, 'load', function(){
	if(AE.SESSION_TIME_OUT_ENABLED){
		resetLogoutTimer();
	}
});

Event.observe(window, 'mousemove', function(){
	if(AE.SESSION_TIME_OUT_ENABLED){
		resetLogoutTimer();
	}
	
});

//----------------------------------------------------------------------------------------------------------------
// reset the timer
function resetLogoutTimer() {
  if(AE.SESSION_TIMER_ID){
	  window.clearTimeout(AE.SESSION_TIMER_ID);
	 // $('logout_warning').hide();
  }
  AE.SESSION_TIMER_ID = logoutWarning.delay(AE.SESSION_TIME_OUT_WARNING);
}

//----------------------------------------------------------------------------------------------------------------
//shows the warning box
function logoutWarning() {
  var warnDiv = $('logout_warning');
  warnDiv.top = 100;//document.viewport.getHeight()/2;
  warnDiv.left = 100;//document.viewport.getWidth()/2;
  warnDiv.show();
  AE.SESSION_TIMER_ID = timeoutSession.delay(AE.SESSION_TIME_OUT_WARNING);
}
function timeoutSession() {
	AE.SESSION_TIMED_OUT = true;
}

// ----------------------------------------------------------------------------------------------------------------
function logOutOKClicked(url){
	if(AE.SESSION_TIMED_OUT) window.location=url;
}


