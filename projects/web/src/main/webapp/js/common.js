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
    },

    // Disable all form elements contained in this element and add the class "disabled"
    disableDescendants: function() {
        for (var i = 0; i < arguments.length; i++) {
            var element = $(arguments[i]);
            element.addClassName("disabled")
            element.descendants().each(function(elt) {
                if (elt.disable) elt.disable()
            })
        }
    },

    // Enable all form elements contained in this element and remove the class "disabled"
    enableDescendants: function() {
        for (var i = 0; i < arguments.length; i++) {
            var element = $(arguments[i]);
            element.removeClassName("disabled")
            element.descendants().each(function(elt) {
                if (elt.enable) elt.enable()
            })
        }
    }
} );

////// CALENDAR POPUP HANDLERS

Element.observe(window, "load", function() {
    $$("input.date").each(function(input) {
        var anchorId = input.id + "-calbutton"
        Calendar.setup(
            {
                inputField  : input.id,
                button      : anchorId,
                ifFormat    : "%m/%d/%Y", // TODO: get this from the configuration
                weekNumbers : false
            }
        );
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

////// FORM EDITING

// Provides a uniform set of functions for editing a form containing a
// dynamically-resizable list.  Only "add" is implemented so far.
var ListEditor = Class.create();
Object.extend(ListEditor.prototype, {
    // divisionClass: class for the container.  Each container should have this
    //    class, and have the id "${divisionClass}-${listIndex}"
    // dwrNS: the DWR namespace object in which the ajax fns can be found
    // basename:  the base of the name for the various ajax fns
    //     e.g., add will call dwrNS.add${basename}
    initialize: function(divisionClass, dwrNS, basename, options) {
        this.divisionClass = divisionClass
        this.dwrNS = dwrNS
        this.basename = basename
        this.options = Object.extend({
            addButton:    "add-" + divisionClass + "-button",
            addIndicator: "add-" + divisionClass + "-indicator",
            addParameters: [ ]
        }, options)

        this.options.addButton = $(this.options.addButton)
        this.options.addIndicator = $(this.options.addIndicator)
        if (this.options.addButton) {
            this.options.addButton.observe("click", this.add.bindAsEventListener(this))
        }
    },

    add: function() {
        var fnName = "add" + this.basename;
        var addFn = this.dwrNS[fnName]
        if (!addFn) { alert("There is no function the selected dwr namespace named " + fnName); return; }

        if (this.options.addButton) this.options.addButton.disable()
        if (this.options.addIndicator) AE.showIndicator(this.options.addIndicator)
        var sel = "." + this.divisionClass
        var nextIndex = $$(sel).length
        var args = [nextIndex].concat(this.options.addParameters).concat([
            function(html) {
                new Insertion.After($$(sel).last(), html)
                if (this.options.addCallback) this.options.addCallback(nextIndex)
                AE.slideAndShow(this.divisionClass + "-" + nextIndex)
                if (this.options.addButton) this.options.addButton.enable()
                if (this.options.addIndicator) AE.hideIndicator(this.options.addIndicator)
            }.bind(this)
        ])
        addFn.apply(this, args)
    }

})