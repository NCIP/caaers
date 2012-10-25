<script>
		(function(jQuery){		
		    jQuery.InFieldLabels = function(label,field, options){
		        // To avoid scope issues, use 'base' instead of 'this'
		        // to reference this class from internal events and functions.
		        alert("3");
		        var base = this;
		        
		        // Access to jQuery and DOM versions of each element
		        base.jQuerylabel = jQuery(label);
		        base.label = label;

		 		base.jQueryfield = jQuery(field);
				base.field = field;
		        
				base.jQuerylabel.data("InFieldLabels", base);
				base.showing = true;
		        
		        base.init = function(){
					// Merge supplied options with default options
		            base.options = jQuery.extend({},jQuery.InFieldLabels.defaultOptions, options);

					// Check if the field is already filled in
					if(base.jQueryfield.val() != ""){
						base.jQuerylabel.hide();
						base.showing = false;
					};
					
					base.jQueryfield.focus(function(){
						base.fadeOnFocus();
					}).blur(function(){
						base.checkForEmpty(true);
					}).bind('keydown.infieldlabel',function(e){
						// Use of a namespace (.infieldlabel) allows us to
						// unbind just this method later
						base.hideOnChange(e);
					}).change(function(e){
						base.checkForEmpty();
					}).bind('onPropertyChange', function(){
						base.checkForEmpty();
					});
		        };

				// If the label is currently showing
				// then fade it down to the amount
				// specified in the settings
				base.fadeOnFocus = function(){
					if(base.showing){
						base.setOpacity(base.options.fadeOpacity);
					};
				};
				
				base.setOpacity = function(opacity){
					base.jQuerylabel.stop().animate({ opacity: opacity }, base.options.fadeDuration);
					base.showing = (opacity > 0.0);
				};
				
				// Checks for empty as a fail safe
				// set blur to true when passing from
				// the blur event
				base.checkForEmpty = function(blur){
					if(base.jQueryfield.val() == ""){
						base.prepForShow();
						base.setOpacity( blur ? 1.0 : base.options.fadeOpacity );
					} else {
						base.setOpacity(0.0);
					};
				};
				
				base.prepForShow = function(e){
					if(!base.showing) {
						// Prepare for a animate in...
						base.jQuerylabel.css({opacity: 0.0}).show();
						
						// Reattach the keydown event
						base.jQueryfield.bind('keydown.infieldlabel',function(e){
							base.hideOnChange(e);
						});
					};
				};

				base.hideOnChange = function(e){
					if(
						(e.keyCode == 16) || // Skip Shift
						(e.keyCode == 9) // Skip Tab
					  ) return; 
					
					if(base.showing){
						base.jQuerylabel.hide();
						base.showing = false;
					};
					
					// Remove keydown event to save on CPU processing
					base.jQueryfield.unbind('keydown.infieldlabel');
				};
		      
				// Run the initialization method
		        base.init();
		    };
			
		    jQuery.InFieldLabels.defaultOptions = {
		        fadeOpacity: 0.5, // Once a field has focus, how transparent should the label be
				fadeDuration: 300 // How long should it take to animate from 1.0 opacity to the fadeOpacity
		    };
			

		    jQuery.fn.inFieldLabels = function(options){
		        return this.each(function(){
					// Find input or textarea based on for= attribute
					// The for attribute on the label must contain the ID
					// of the input or textarea element
					var for_attr = jQuery(this).attr('for');
					if( !for_attr ) return; // Nothing to attach, since the for field wasn't used
					
					
					// Find the referenced input or textarea element
					var jQueryfield = jQuery(
						"input#" + for_attr + "[type='text']," + 
						"input#" + for_attr + "[type='password']," + 
						"textarea#" + for_attr
						);
						
					if( jQueryfield.length == 0) return; // Again, nothing to attach
					
					// Only create object for input[text], input[password], or textarea
		            (new jQuery.InFieldLabels(this, jQueryfield[0], options));
		        });
		    };
			
		})(jQuery);
		</script>