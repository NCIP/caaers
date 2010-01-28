/* Javascript to track changes, and prompt the user for saving */


var ModificationTracker = Class.create({

    initialize: function(){
        if(AE.checkForModification){
        	this.form = $('command');
            
            if(this.form){
            	this.formElements =  this.form.select('textarea','input','select');
                AE.formFieldCount = this.formElements.size();
                
                //observe the change
                this.formElements.each(function(el){
                	el.observe('change',function(){
                		AE.formFieldModified=true;
                	});
                });
                
                //observe submit on the form
                this.form.observe('submit', function(){
                	AE.checkForModification=false;
                });
                
                window.onbeforeunload = this.checkForModificationsOnPage.bind(this);
            }
            
        }
		
    },
    
    //check if there is a form field change
    checkForModificationsOnPage: function(){
        if(AE.checkForModification){
           var curFormElementCount = this.form.select('textarea','input','select').size();
           if(AE.formFieldModified || (curFormElementCount != AE.formFieldCount) )
        	   return AE.UNSAVED_DATA_MESSAGE;
        }
    }
       
});

Event.observe(document, "dom:loaded", function(){
	new ModificationTracker();
});

