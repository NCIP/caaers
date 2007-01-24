// Namespace for caAERS-specific shared functions and classes
var AE = { }

AE.INDICATOR_REF_COUNTS = { };

// this stuff should technically be synchronized.  Let see if it causes a problem.
AE.showIndicator = function(id) {
    if (!AE.INDICATOR_REF_COUNTS[id]) AE.INDICATOR_REF_COUNTS[id] = 0;
    AE.INDICATOR_REF_COUNTS[id] += 1
    AE.updateIndicatorVisibility(id)
}

AE.hideIndicator = function(id) {
    if (!AE.INDICATOR_REF_COUNTS[id]) AE.INDICATOR_REF_COUNTS[id] = 0;
    AE.INDICATOR_REF_COUNTS[id] -= 1;
    if (AE.INDICATOR_REF_COUNTS[id] < 0) AE.INDICATOR_REF_COUNTS[id] = 0;
    AE.updateIndicatorVisibility(id)
}

AE.updateIndicatorVisibility = function(id) {
    if (AE.INDICATOR_REF_COUNTS[id] > 0) {
        $(id).reveal();
    } else {
        $(id).conceal();
    }
}

////// PROTOTYPE EXTENSIONS
// TODO: This code is shared with PSC.

Element.addMethods( {
    // Like prototype's hide(), but uses the visibility CSS prop instead of display
    conceal: function() {
        for (var i = 0; i < arguments.length; i++) {
          var element = $(arguments[i]);
          element.style.visibility = 'hidden';
        }
    },

    // Like prototype's show(), but uses the visibility CSS prop instead of display
    reveal: function() {
        for (var i = 0; i < arguments.length; i++) {
          var element = $(arguments[i]);
          element.style.visibility = 'visible';
        }
    }
} );

////// CALENDAR POPUP HANDLERS

AE.calendar = new CalendarPopup()

Element.observe(window, "load", function() {
    $$("input.date").each(function(input) {
        var anchorId = input.id + "-calbutton"
        Element.observe(anchorId, "click", function(e) {
            AE.calendar.select(input, anchorId, 'MM/dd/yyyy') // TODO: get this from the configuration
        })
    })
});

////// SSO

Event.observe(window, "load", function() {
    $$("a.sso").each(function(a) {
        Event.observe(a, "click", function(e) {
            Event.stop(e)
            var ssoForm = $('sso-form')
            ssoForm.action = a.href
            ssoForm.submit()
        })
    })
})
