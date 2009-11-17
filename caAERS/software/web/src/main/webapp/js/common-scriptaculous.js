/*
 *  caAERS-local classes & functions which require scriptaculous.
 */

////// JS STYLES

AE.slideAndHide = function(element, options) {
    var e = $(element);
    if (e){
    	//Effect.Parallel, is not propery working in IE7
    	if(Prototype.Browser.IE){
    		new Effect.BlindUp(e);
    	}else{
    		 new Effect.Parallel(
        		[
            		new Effect.BlindUp(e, {sync:true}),
            		new Effect.Fade(e, {sync:true})
        		], $H(options).merge({
            		duration: 1.0
        		})
    		);
    	}
    }
}

AE.slideAndShow = function(element, options) {
    var e = $(element);
    if (e) {
    	//The Effect.Parallel, is not properly working in IE7,  
    	if(Prototype.Browser.IE){
    		new Effect.BlindDown(e);
    	}else {
    		new Effect.Parallel(
        		[
            		new Effect.BlindDown(e, {sync:true,  to:1.01}),
            		new Effect.Appear(e, {sync:true,  to:1.01}),
        		], $H(options).merge({
            		duration: 1.0
        		})
    		);
    	}
    
    }
    
}

AE.highlight = function(element, options) {
    var e = $(element)
    if (e) new Effect.Highlight(element, Object.extend({
        restorecolor: "#ffffff"
    }, $H(options)));
}

// This is based on the code from https://dwr.dev.java.net/servlets/ReadMsg?list=users&msgNo=2629
// Differences:  this version includes records whose display value does not include the typed string
//    (this allows the server to match on multiple fields at once)
Autocompleter.DWR = Class.create();
Autocompleter.DWR.prototype = Object.extend(new Autocompleter.Base(), {
    initialize: function(element, update, populator, options) {
        this.baseInitialize(element, update, options);
        this.options.array = new Array(0);
        this.populator = populator;
        if (this.options.afterUpdateElement) {
            this.afterUpdateCallback = this.options.afterUpdateElement;
            this.options.afterUpdateElement = this.afterUpdateElement.bind(this);
        }
    },

    // called by the autocompleter on an event.
    getUpdatedChoices: function() {
        this.startIndicator();
        this.populator(this, this.getToken());
    },

    afterUpdateElement: function(element, selectedElement) {
        this.afterUpdateCallback(element, selectedElement, this.options.array[this.index]);
    },

    // should be called by the populator (specified in the constructor)
    setChoices: function(array) {
        this.options.array = array;
        if(this.options.array.length < 1){
        	this.stopIndicator();
        	this.update.innerHTML = "<ul><li><b>No matches found</b></li></ul>";
        	if(this.hasFocus){
        		this.show();
        		this.active = true;
        	}else {
        		 this.active = false;
        	     this.hide();
        	}
		}else{
			 var _choices =this.options.selector(this); 
		     this.updateChoices(_choices);
		}
    },
    selectEntry: function() {
        this.active = false;
        var _curEntry = this.getCurrentEntry();
        if(_curEntry){
        	var _curContent = _curEntry.innerHTML;
        	if(_curContent && (_curContent.indexOf('No matches found') > 0) ) return;
        }
        this.updateElement(_curEntry);
    },
    
    setOptions: function(options) {
        this.options = Object.extend({
            choices: 25,
            selector: function(instance) {
                var items = [];
                var entry = instance.getToken();
                var count = 0;
                var valueSelector = instance.options.valueSelector;

                for (var i = 0; i < instance.options.array.length &&
                                items.length < instance.options.choices; i++) {

                    var value = valueSelector(instance.options.array[i]);
                    var foundPos = value.toLowerCase().indexOf(entry.toLowerCase());

                    if (foundPos != -1) {
                        items.push("<li>" + value.substr(0, foundPos)
                            + "<strong>" + value.substr(foundPos, entry.length) + "</strong>"
                            + value.substr(foundPos + entry.length) + "</li>");
                    } else {
                        items.push("<li>" + value + "</li>")
                    }
                }
                return "<ul>" + items.join('') + "</ul>";
            },
            valueSelector: function(object) {
                return object;
            }
        }, options || {});
    },

    startIndicator: function() {
      if (this.options.indicator) AE.showIndicator(this.options.indicator)
    },

    stopIndicator: function() {
      if (this.options.indicator) AE.hideIndicator(this.options.indicator)
    }

});

// Creates an autocompleter matching the field names created by tag:renderInputs
AE.createStandardAutocompleter = function(propertyName, populator, valueSelector, options) {
    if (!$(propertyName)) alert("No element with id " + propertyName);
    var opts = Object.extend({
        indicator: propertyName + "-indicator",
        valueSelector: valueSelector,
        // if you replace this option, you'll need to include this functionality
        afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
            $(propertyName).value = selectedChoice.id;
            ValidationManager.setValidState($(propertyName + '-input'));
        },
        minChars : AE.autocompleterChars , 
        frequency : AE.autocompleterDelay
    }, options || { })

    if (opts.initialInputValue) {
        $(propertyName + "-input").value = opts.initialInputValue;
    }
    return new Autocompleter.DWR(
        propertyName + "-input",
        propertyName + "-choices",
        populator,
        opts
    )
}

AE.resetAutocompleter = function(propertyName){
	var el = $(propertyName);
	AE.hash.set(propertyName, '1');
	if(el){
		el.value='';
		var elInput = $(propertyName + '-input');
		elInput.value="Begin typing here...";
		elInput.addClassName('pending-search');
        // Insertion.Before(document.body, "<font color='white'>Reset.</font><br>");
        ValidationManager.setInvalidState($(propertyName + '-input'));
    }
}

////// INLINE HELP

AE.helpToggle = function(field) {
    var content = $(field + "-help-content")
    if (content.hasClassName("changing")) return;
    content.addClassName("changing")
    var after = function() {
        content.removeClassName("changing")
    }
    if (content.visible()) {
        AE.slideAndHide(content, { afterFinish: after })
    } else {
        AE.slideAndShow(content, { afterFinish: after })
    }
}

function captureHelpControlEvents(){

    $$(".inline-help-control").each(function(control) {
        var id = control.id
        var field = id.substr(0, id.length - 13)
        control.observe("click", function() {
            AE.helpToggle(field)
        })
    })
}

Event.observe(window, "load", function() {
	captureHelpControlEvents();
});

/*
 * In-Place Editor
 */
 
Ajax.InPlaceCollectionEditor.prototype.__createEditField = Ajax.InPlaceCollectionEditor.prototype.createEditField;
Ajax.InPlaceCollectionEditor.prototype.__onSubmit = Ajax.InPlaceCollectionEditor.prototype.onSubmit;
Object.extend(Ajax.InPlaceCollectionEditor.prototype, {
    createEditField: function() {
        if (this.options.callback) { var callbackSet = this.options.callback };
        this.__createEditField();
        if (callbackSet) { this.options.callback = callbackSet;    };
    },
    onSubmit: function(){
		if(this.options.validations!=null){
			this.editField.className=this.editField.className + " "+this.options.validations;
			ValidationManager.removeError(this.editField);
			var fields=new Array();
			fields.push(this.editField);
			ValidationManager.prepareField(fields[0]);
			if(validateFields(fields, true)){
				this.__onSubmit();
		    }else{
		    	arguments.length > 1?Event.stop(arguments[0]):null;
		    	return false;
		    }
		}else{
        	this.__onSubmit();
        }
    }
    
});

//InPlaceEditor extension that adds a 'click to edit' text when the field is 
//empty.
Ajax.InPlaceEditor.prototype.__onSubmit = Ajax.InPlaceEditor.prototype.onSubmit;
Ajax.InPlaceEditor.prototype = Object.extend(Ajax.InPlaceEditor.prototype, {
    onSubmit: function(){
		if(this.options.validations!=null){
			this.editField.className=this.editField.className + " "+this.options.validations;
			ValidationManager.removeError(this.editField);
			var fields=new Array();
			fields.push(this.editField);
			ValidationManager.prepareField(fields[0]);
			if(validateFields(fields, true)){
				this.__onSubmit();
		    }else{
		    	arguments.length > 1?Event.stop(arguments[0]):null;
		    	return false;
		    }
		}else{
        	this.__onSubmit();
        }
    }
});

//InPlaceCollectionEditor extension that adds a cancel button .
Ajax.InPlaceCollectionEditor.prototype.__createForm = Ajax.InPlaceCollectionEditor.prototype.createForm;
Ajax.InPlaceCollectionEditor.prototype = Object.extend(Ajax.InPlaceCollectionEditor.prototype, {
    createForm: function(){
		this.__createForm();
		if (this.options.cancelButton) {
          cancelButton = document.createElement("input");
	      cancelButton.type = "button";
   	      cancelButton.onclick = this.onclickCancel.bind(this);
	      cancelButton.value = this.options.cancelText;
	      cancelButton.className = 'editor_ok_button';
	      this.form.appendChild(cancelButton);
        }
    }
});

