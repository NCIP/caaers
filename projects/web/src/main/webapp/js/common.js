// Namespace for caAERS-specific shared functions and classes
var AE = { }

AE.INDICATOR_REF_COUNTS = { };
AE.hash = new Hash();

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
    
    //for split-date
    sel ="input.split-date"
    if (containerId) sel = "#" + containerId + " " + sel
    $$(sel).each(function(input) {
    	var yearInputId = input.id
		var baseInputId = input.id.substring(0, input.id.lastIndexOf('.yearString'))
        var anchorId = baseInputId + "-calbutton"
        var monthInputId = baseInputId + ".monthString"
        var dayInputId = baseInputId + ".dayString"
        
        Calendar.setup(
            {
                'yearInputId' : yearInputId,
                'monthInputId': monthInputId,
                'dayInputId'  :dayInputId,
                button        : anchorId,
                ifFormat      : "%m/%d/%Y", // TODO: get this from the configuration
                weekNumbers   : false,
                onSelect      : function(cal){
                	$(cal.params['dayInputId']).value = cal.date.print('%d')
                	$(cal.params['monthInputId']).value = cal.date.print('%m')
                	$(cal.params['yearInputId']).value =  cal.date.print('%Y')
                }
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
            reorderable: false,
            deletable: false,
            minimizeable:false
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
            return;
        }
        // wrap heading content in a span for easier access later
        heading.innerHTML = "<span class='text'>" + heading.innerHTML + "</span>"
        controls = Builder.node("div", { 'class': "div-list-controls" })
        heading.insertBefore(controls, heading.childNodes[0])

        if (this.options.reorderable) {
            var upControl = Builder.node("a", { 'class': 'list-control move-up-control', 'title': 'Move up', 'href': '#' });
            upControl.innerHTML = "&#9650;" // set directly to avoid escaping
            Event.observe(upControl, "click", this.up.bindAsEventListener(this))
 				
 			
            var downControl = Builder.node("a", { 'class': 'list-control move-down-control', 'title': 'Move down', 'href': '#' });
            downControl.innerHTML = "&#9660;" // set directly to avoid escaping
            Event.observe(downControl, "click", this.down.bindAsEventListener(this))
            
            if(itemDiv.hasClassName('last-item')){
            	controls.appendChild(downControl)
            	controls.appendChild(upControl)	
            }else {
			  controls.appendChild(upControl)
              controls.appendChild(downControl)
            }	
            
        }
        if (this.options.minimizeable) {
        	var minMaxControl = Builder.node("a", { 'class': 'list-control min-max-control', 'title': 'Show-Hide Section', 'href': '#' });
        	if(itemDiv.hasClassName('minimized')) {
        		minMaxControl.innerHTML = '<img border="0" alt="Show Section" src="/caaers/images/b-plus.gif" />';
        	}else{
        		minMaxControl.innerHTML = '<img border="0" alt="Hide Section" src="/caaers/images/b-minus.gif" />'; 
        	}
        	// set directly to avoid escaping
            Event.observe(minMaxControl, "click", this.minMax.bindAsEventListener(this))
            controls.appendChild(minMaxControl)
        }
        if (this.options.deletable) {
            var deleteControl = Builder.node("a", { 'class': 'list-control delete-control', 'title': 'Delete', 'href': '#' })
//            deleteControl.innerHTML = "<img src='/caaers/images/checkno.gif'>"
            deleteControl.innerHTML = "<img src='../../images/checkno.gif'>"
            Event.observe(deleteControl, "click", this.remove.bindAsEventListener(this))
            controls.appendChild(deleteControl)
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
    minMax : function(event) {
    	var eelt = Event.element(event);
     	var pelt = Event.findElement(event, 'H3').up('div.' + this.divisionClass)
     	var elt = pelt.down('div.content');
     	if(pelt.hasClassName('minimized')) {
        	//maximize
        	pelt.removeClassName('minimized');
        	Effect.BlindDown(elt);
        	eelt.src = '/caaers/images/b-minus.gif';
     	} else {
     		//minimize
     		pelt.addClassName('minimized')
     		Effect.BlindUp(elt);
     		eelt.src = '/caaers/images/b-plus.gif'; 
     	}
     	Event.stop(event);
    }
    ,

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

    
    
    remove: function(evt) {
    
      if(!confirm ("Are you sure you want to delete this?"))
        return;
        
        Event.stop(evt);
        var div = Event.element(evt).up("div." + this.divisionClass)
        if (!div) {
            alert("Could not find containing div for delete event");
            return;
        }

        var indexToDelete = div.getAttribute("item-index");
        if (!this.collectionProperty) {
            alert("collectionProperty must be specified for deleting to work")
            return;
        }

        var removeFn = this.dwrNS["remove"]
        if (!removeFn) {
            alert("There is no function named 'remove' in the selected DWR namespace")
            return;
        }
        
        var delArgs = [this.collectionProperty,indexToDelete]
        if(this.options.removeParameters){
          delArgs = delArgs.concat(this.options.removeParameters);
        }
        delArgs = delArgs.concat([function(ajaxOutput) {
        
        	if(ajaxOutput.error){
        		alert(ajaxOutput.errorMessage);
        		return;
        	}
        	
            if (ajaxOutput.changes.length == 0) return;

            var divs = $$('div.' + this.divisionClass)
            if (!divs[indexToDelete]) return;

            $$("div." + this.divisionClass + " .list-controls").each(function(e) { e.conceal(); })

            var container = divs[0].parentNode
            var toDelete = divs[indexToDelete];
            container.removeChild(toDelete)

            this.updateFirstAndLast()
            this.applyIndexChanges(ajaxOutput.changes)

            $$("div." + this.divisionClass + " .list-controls").each(function(e) { e.reveal() })
            
            if (this.options.removeCallback) this.options.removeCallback(indexToDelete)
            
        }.bind(this)]);

        removeFn.apply(this, delArgs)
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

        reorderFn.apply(this, [this.collectionProperty, original, target, function(ajaxOutput) {
        	if(ajaxOutput.error){
        		alert(ajaxOutput.errorMessage);
        		return;
        	}
        	
            if (ajaxOutput.changes.length == 0) return;

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
            this.applyIndexChanges(ajaxOutput.changes)
            
            $$("div." + this.divisionClass + " .list-controls").each(function(e) { e.reveal() })
            
            // Following 3 lines ensure that asterisk appears of primary AE and disappears on  
            // secondary AE when we swap secondary and primary AEs( ie when we move secondary AE up).
            if (this.options.reorderCallback) this.options.reorderCallback(original, target);
              
        }.bind(this)])
    },

    // Updates the field names, IDs, and "for" attributes of all properties
    // modified by the given list of changes.
    applyIndexChanges: function(changes) {
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

/////  autocompleter search fields
function initSearchField() {

    $$("input[type=text].autocomplete").each(function(theInput)
    {
        addEventHandlersForAutoCompleter(theInput)
    });

     $$("input[type=text][class='autocomplete validate-NOTEMPTY']").each(function(theInput)
    {
            addEventHandlersForAutoCompleter(theInput)
    });

}
function addEventHandlersForAutoCompleter(theInput){

        var message = '(Begin typing here)';

          /* Add event handlers */
        Event.observe(theInput, 'focus', clearDefaultText);
        Event.observe(theInput, 'blur', replaceDefaultText);
        /* Save the current value */
        if (theInput.value == '') {
            theInput.defaultText = message;
            theInput.className = 'pending-search';
            theInput.value = message;
        }
}
function clearDefaultText(e) {
    var target = window.event ? window.event.srcElement : e ? e.target : null;
    if (!target) return;

    if (target.value == '(Begin typing here)') {
        target.value = '';
        target.className = 'autocomplete';

    }

}

function replaceDefaultText(e) {
    var target = window.event ? window.event.srcElement : e ? e.target : null;
    if (!target) return;

    if (target.value == '' ) {
        target.value = '(Begin typing here)';
        target.className = 'pending-search';
    }

}

// COLLAPSABLE DIV ELEMENT
////////////////////////////////////////////////////////////////////////////////////////////////

    function SwitchCollapsableState(contentElement, id) {

        panelDiv = $(contentElement);
        imageId= 'image-' + id;
        imageSource = $(imageId).src;

        if (panelDiv.style.display == 'none') {
            OpenUp(panelDiv, arguments[1] || {});
            document.getElementById(imageId).src=imageSource.replace('right','down');
        } else {
            CloseDown(panelDiv, arguments[1] || {});
            document.getElementById(imageId).src=imageSource.replace('down','right');
        }
    }

    function OpenUp(element) {
        element = $(element);
        new Effect.BlindDown(element, arguments[1] || {});
    }

    function CloseDown(element) {
        element = $(element);
        new Effect.BlindUp(element, arguments[1] || {});
    }

////////////////////////////////////////////////////////////////////////////////////////////////

function dump(arr, level) {
	var dumped_text = "";
	if(!level) level = 0;

	// The padding given at the beginning of the line.
	var level_padding = "";
	for(var j=0;j<level+1;j++) level_padding += "    ";

	if(typeof(arr) == 'object') { //Array/Hashes/Objects
		for(var item in arr) {
			var value = arr[item];

			if(typeof(value) == 'object') { //If it is an array,
				dumped_text += level_padding + "'" + item + "' ...\n";
				dumped_text += dump(value,level+1);
			} else {
				dumped_text += level_padding + "'" + item + "' => \"" + value + "\"\n";
			}
		}
	} else { //Stings/Chars/Numbers etc.
		dumped_text = "===>"+arr+"<===("+typeof(arr)+")";
	}
	return dumped_text;
}

function updateHelpLink(baseUrl, linkName){
	$('help').href= baseUrl + "#" + linkName + ".htm";
}


function showBigDropdown(evt, curCss, mup){
	if(curCss != 'selectbox') return;
	
	var el = evt.element();
	if(mup){
		var inCombo = !AE.hash.get(el.name);
		AE.hash.set(el.name, inCombo);
		if(inCombo){
			el.removeClassName(curCss);
			el.addClassName('selectboxClick');
		}else {
			hideBigDropdown(evt, el.name, false);
		}
	}else{
		el.removeClassName(curCss);
		el.addClassName('selectboxClick');
	}
		
}
function hideBigDropdown(evt, curCss, initToFalse){
	if(curCss != 'selectbox') return;
	var el = evt.element();
	if(initToFalse) AE.hash.set(el.name, false);
	el.removeClassName('selectboxClick');
	el.addClassName(curCss);
	//el.removeClassName('selectbox');
	
}

