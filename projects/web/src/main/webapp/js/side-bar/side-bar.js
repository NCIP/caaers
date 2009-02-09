var isExtended = 0;

function slideSideBar(){

	new Effect.toggle('slider-pane', 'blind', {scaleX: 'true', scaleY: 'true;', scaleContent: false, duration:0.5});
	
	if(isExtended == 0) {
		new Effect.Fade('slider-pane', { duration:0.5, from:0.0, to:1.0 });
		isExtended++;
	}
	else {
		new Effect.Fade('slider-pane', { duration:0.5, from:1.0, to:0.0 });
		isExtended = 0;
	}
}

function init() {
    if ($('sideBarTab')) {
        Event.observe('sideBarTab', 'click', slideSideBar);
    }
}

Event.observe(window, 'load', init, true);