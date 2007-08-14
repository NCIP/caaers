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

Form.selectedRadioValue = function(form, radioName) {
    var pair = $A($(form)[radioName])
        .collect(Form.Element.Serializers.inputSelector)
        .detect(function(e) { return e != null })
    return pair ? pair[1] : null;
}

////// CALENDAR POPUP HANDLERS

AE.registerCalendarPopups = function(containerId) {
    var sel = "input.date"
    if (containerId) sel = "#" + containerId + " " + sel
    $$(sel).each(function(input) {
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
}

Element.observe(window, "load", function() {
    AE.registerCalendarPopups()
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
// dynamically-resizable list.
var ListEditor = Class.create();
Object.extend(ListEditor.prototype, {
    // divisionClass: class for the container.  Each container should have this
    //    class, and have the id "${divisionClass}-${listIndex}"
    // dwrNS: the DWR namespace object in which the ajax fns can be found
    // basename:  the base of the name for the various ajax fns
    //     e.g., add will call dwrNS.add${basename}(...) or dwrNS.addFormSection(basename, ...)
    initialize: function(divisionClass, dwrNS, basename, options, collectionProperty) {
        this.divisionClass = divisionClass
        this.dwrNS = dwrNS
        this.basenameUC = basename.substring(0, 1).toUpperCase() + basename.substring(1)
        this.basenameLC = basename.substring(0, 1).toLowerCase() + basename.substring(1)
        this.collectionProperty = collectionProperty
        this.options = Object.extend({
            addButton:    "add-" + divisionClass + "-button",
            addIndicator: "add-" + divisionClass + "-indicator",
            addParameters: [ ],
            reorderable: false
        }, options)

        this.options.addButton = $(this.options.addButton)
        this.options.addIndicator = $(this.options.addIndicator)
        this.options.addFirstAfter = $(this.options.addFirstAfter)
        if (this.options.addButton) {
            this.options.addButton.observe("click", this.add.bindAsEventListener(this))
        }

        this.form = $("command") // might want to make this overridable with an option

        this.updateFirstAndLast();
        this.createControls();
    },

    createControls: function() {
        $$("div." + this.divisionClass).each(this.createSingleItemControls.bind(this))
    },

    createSingleItemControls: function(itemDiv) {
        itemDiv = $(itemDiv)
        var heading = itemDiv.getElementsByTagName("H3")[0]
        if (!heading) {
            alert("division " + itemDiv.id + " is missing its header")
            return;
        }
        // wrap heading content in a span for easier access later
        heading.innerHTML = "<span class='text'>" + heading.innerHTML + "</span>"
        if (this.options.reorderable) {
            // TODO: icons instead of text
            var upControl = Builder.node("a", { 'class': 'list-control move-up-control', 'title': 'Move up', 'href': '#' });
            upControl.innerHTML = "&#9650;" // set directly to avoid escaping
            Event.observe(upControl, "click", this.up.bindAsEventListener(this))

            var downControl = Builder.node("a", { 'class': 'list-control move-down-control', 'title': 'Move down', 'href': '#' });
            downControl.innerHTML = "&#9660;" // set directly to avoid escaping
            Event.observe(downControl, "click", this.down.bindAsEventListener(this))

            heading.insertBefore(
                Builder.node("span", { 'class': "list-controls" }, [ upControl, downControl ]),
                heading.childNodes[0]
            );
        }
    },

    add: function() {
        // fn resolution:  If there's a fn named add${basename}, use that
        var specificFnName = "add" + this.basenameUC;
        var addFn = this.dwrNS[specificFnName]
        // otherwise ...
        if (!addFn) {
            var parameterizedFnName = "addFormSection";
            var parameterizedFn = this.dwrNS[parameterizedFnName]
            if (!parameterizedFn) {
                alert("There is no function the selected dwr namespace named either " + specificFnName + " or " + parameterizedFnName); return;
            }
            // ... use a function called addFormSection, passing the basename as the first parameter
            addFn = function() {
                var args = [this.basenameLC].concat($A(arguments))
                return parameterizedFn.apply(this, args)
            }
        }

        if (this.options.addButton) this.options.addButton.disable()
        if (this.options.addIndicator) AE.showIndicator(this.options.addIndicator)
        var sel = "." + this.divisionClass
        var nextIndex = $$(sel).length
        var args = [nextIndex].concat(this.options.addParameters).concat([
            function(html) {
                var after = nextIndex == 0 ? this.options.addFirstAfter : $$(sel).last()
                new Insertion.After(after, html)
                var newId = this.divisionClass + "-" + nextIndex;
                AE.slideAndShow(newId)
                this.updateFirstAndLast()
                this.createSingleItemControls(newId)
                if (this.options.addCallback) this.options.addCallback(nextIndex)
                if (this.options.addButton) this.options.addButton.enable()
                if (this.options.addIndicator) AE.hideIndicator(this.options.addIndicator)
            }.bind(this)
        ])
        addFn.apply(this, args)
    },

    up: function(event) {
        this._reorderEventHandler(event, function(i) { return +i - 1 })
    },

    down: function(event) {
        this._reorderEventHandler(event, function(i) { return +i + 1 })
    },

    _reorderEventHandler: function(evt, delta) {
        Event.stop(evt);
        var div = Event.element(evt).up("div." + this.divisionClass)
        if (!div) {
            alert("Could not find containing div for reorder event");
            return;
        }
        var original = div.getAttribute("item-index");
        var target = delta(original);
        this.reorder(original, target)
    },

    reorder: function(original, target) {
        if (!this.collectionProperty) {
            alert("collectionProperty must be specified for reordering to work")
            return;
        }

        var reorderFn = this.dwrNS["reorder"]
        if (!reorderFn) {
            alert("There is no function named 'reorder' in the selected DWR namespace")
            return;
        }

        reorderFn.apply(this, [this.collectionProperty, original, target, function(changes) {
            if (changes.length == 0) return;

            var divs = $$('div.' + this.divisionClass)
            if (!divs[original]) return;
            if (target < 0 || target >= divs.length) return;

            $$("div." + this.divisionClass + " .list-controls").each(function(e) { e.conceal(); })

            var container = divs[0].parentNode
            var toMove = divs[original];
            // console.log("Trying to move %o from %i to %i", toMove, original, target)
            container.removeChild(toMove)
            if (target != divs.length - 1) {
                if (target < original) {
                    // console.log("inserting before div[%i] %o", target, divs[target])
                    container.insertBefore(toMove, divs[target])
                } else {
                    // console.log("inserting before div[%i] %o", target + 1, divs[target + 1])
                    container.insertBefore(toMove, divs[target + 1])
                }
            } else {
                // find the child after the last div, if any
                var postItemsChild = divs[divs.length - 1].nextSibling;
                if (postItemsChild) {
                    // console.log("inserting before post item child %o", postItemsChild)
                    container.insertBefore(toMove, postItemsChild);
                } else {
                    // console.log("appending", postItemsChild)
                    container.appendChild(toMove)
                }
            }

            this.updateFirstAndLast()
            this.applyChanges(changes)
            
            $$("div." + this.divisionClass + " .list-controls").each(function(e) { e.reveal() })
        }.bind(this)])
    },

    // Updates the field names, IDs, and "for" attributes of all properties
    // modified by the given list of changes.
    applyChanges: function(changes) {
        // radio button values are trashed sometimes; preserve them so they can be restored
        // TODO: are checkboxes similarly afflicted?
        var radioValues = $$("#" + this.form.id + " input[type=radio]").inject({ }, function(values, radio) {
            if (radio.checked) values[radio.name] = radio.value;
            return values
        })
        this.form.descendants().each(function(elt) {
            if (!(elt.name || elt.id || elt.hasAttribute("for"))) return;
            changes.each(function(change) {
                var matchedAndChanged = false
                var root = this.collectionProperty + "[" + change.original + "]";
                var newRoot = this.collectionProperty + "[" + change.current + "]";
                var rootRE = "^" + root.replace("[", "\\[").replace("]", "\\]")
                if (elt.name && elt.name.match(rootRE)) {
                    var oldName = elt.name
                    elt.name = elt.name.replace(root, newRoot)
                    matchedAndChanged = true
                    // restore radio value, if necessary
                    if (radioValues[oldName] && elt.value == radioValues[oldName]) {
                        elt.checked = true
                    }
                }
                if (elt.id && elt.id.match(rootRE)) {
                    elt.id = elt.id.replace(root, newRoot)
                    matchedAndChanged = true
                }
                if (elt.hasAttribute("for") && elt.getAttribute("for") && elt.getAttribute("for").match(rootRE)) {
                    elt.setAttribute("for", elt.getAttribute("for").replace(root, newRoot));
                    matchedAndChanged = true
                }
                if (matchedAndChanged) {
                    // only change each elt once
                    throw $break;
                }
            }.bind(this))
        }.bind(this))

        $$("div." + this.divisionClass).each(function(div, index) {
            var itemIndex = div.getAttribute("item-index")
            changes.each(function(change) {
                if (change.current == itemIndex) {
                    div.getElementsBySelector("h3 .text")[0].innerHTML = change.currentDisplayName;
                }
            })
        })
    },

    updateFirstAndLast: function() {
        var divs = $$('div.' + this.divisionClass);
        divs.each(function(div, index) {
            div.removeClassName("first-item")
            div.removeClassName("last-item")
            if (index == 0) {
                div.addClassName("first-item")
            }
            if (index == divs.length - 1) {
                div.addClassName("last-item")
            }
            div.setAttribute("item-index", index)
        });
    }
})

//////// SEARCH helpers

function showTable(table) {
	$('indicator').className='indicator'
	document.getElementById('tableDiv').innerHTML=table;
}

function copyValues(select,prop){
	
	var selectArray = $(select).options;
	 for ( i=0; i < selectArray.length; i++){
	 	if (selectArray[i].selected  ) {
	 		$(prop).value=selectArray[i].value == "---" ? "" : selectArray[i].value
	 	}
	 }
}
