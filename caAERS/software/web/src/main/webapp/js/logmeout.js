AE.SESSION_TIME_OUT_WARNING=10 * 60; //timeout duration in seconds
AE.SESSION_TIME_OUT_WAIT= 1 * 60;
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
  var warnDiv = new Window({className: "alphacube", width:580, height:190, zIndex: 100, resizable: true, recenterAuto:true, draggable:true}); 
  warnWindow.setContent('logout_warning');
  warnWindow.showCenter(true);
  warnWindow.show();
  AE.SESSION_TIMER_ID = timeoutSession.delay(AE.SESSION_TIME_OUT_WAIT);
}
function timeoutSession() {
	AE.SESSION_TIMED_OUT = true;
}

// ----------------------------------------------------------------------------------------------------------------
function logOutOKClicked(url){
	Windows.closeAll();
	if(AE.SESSION_TIMED_OUT) window.location=url;
}


