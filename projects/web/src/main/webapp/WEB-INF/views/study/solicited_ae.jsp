<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="study" tagdir="/WEB-INF/tags/study" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <title>${tab.longTitle}</title>
  <tags:includeScriptaculous/>
  <tags:includePrototypeWindow />
  <tags:dwrJavascriptLink objects="createStudy"/>
  <tags:stylesheetLink name="pw_default" />
  <tags:stylesheetLink name="pw_alphacube" />
  <tags:stylesheetLink name="aeTermQuery_box" />
  <style type="text/css">
  	
  	.close-button {
        position:relative;
        top:-20px;
        border:0;
        
    }
  	.sae, .query {
		border-spacing: 0;
		border-collapse: collapse;
 		margin: 1em 2em;
 	}
 	.query td.one {
 		padding-right: 2em;
 	}
 	.query td.three {
 	padding-left: 2em;
 	}
 	.query td.two {
 	 border-color:#6E81A6;
	 border-style:solid;
	 border-width:0px 1px 0px 1px;
	 height : 1em;
	 padding-left: 1em;
	 padding-right: 1em;
	 
 	}
 	
 	.sae col.term {
 		text-align: center;
 	}
 	
 	.sae col.epoch {
 		text-align: center;
 	}
 	
 	.sae .deletecol {
 	    
		padding-left: 2em;
	    padding-right: 2em;
	    
 	}
 	
	.sae .data{
 	    border-width:0px 1px 0px 1px;
		border-color:#6E81A6;
	 	border-style:solid;
 	}
 	
	.sae th.term {
	    border-width:1px 1px 1px 1px;
		border-color:#6E81A6;
	 	border-style:solid;
	 	width: 22em;
		height : 3em;
		vertical-align: CENTER;
	    horizontal-align: center;
	}
	.sae th.epoch {
		width: 10em;
		vertical-align: center;
	}

	.sae th.reportingperiod {
		width: 10em;
		vertical-align: top;
	}
	.sae th.action {
		border-width:1px 1px 1px 1px;
		border-color:#6E81A6;
	 	border-style:solid;
	 	color: black;
	 	width: 90px;
	}
	
	.sae .lastLineOfTable
	{
		border-width:0px 0px 1px 0px;
		border-color:#6E81A6;
	 	border-style:solid;
	
	}
	
    th.reportingperiod, th.epoch {
	 border-color:#6E81A6;
	 border-style:solid;
	 border-width:1px 1px 1px 1px;
	 height : 2em;
	}
	
	
	
    .sae td {
	 border-color:#6E81A6;
	 border-style:solid;
	 border-width:0px 1px 0px 1px;
	 height : 2em;
	}
	
	
	tr.gap
	{
	    border : none;
	 	height : 2em;
	}
	.sae tr.head {
		border-width:1px 1px 1px 1px;
		border-color:#6E81A6;
	 	border-style:solid;
	 	
	}
	.sae tr.bottom {
		border-width:0px 1px 1px 1px;
		border-color:#6E81A6;
	 	border-style:solid;
	 	
	}
	.sae tr.bottom td{
		border-width: 0px 1px 1px 1px;
		border-color:#6E81A6;
	 	border-style:solid;
	 	
	}
	.sae .inst {
		font-style: normal;
		font-weight: normal;
	}
    
	.lastRowValue {
	 font-style: normal;
		text-align: center;
		color:red; 
	}
	
  </style>
  
  <script type="text/javascript">
  
   var setOfWindowsOpened = new Array();
  
   Event.observe(window, 'load', initializeAllEvents); 
    
    function initializeAllEvents()
    {
     Event.observe('flow-prev', 'click', checkForm);
     Event.observe('flow-next', 'click', checkForm);
     Event.observe('flow-update', 'click', checkForm);
     
     registerAddInstructionLinks();
    
     registerSelectAllCheckBoxes();  
     
     registerDeleteEpochIcons();   
     
     registerAddEpochButton();  
    
     var listOfTermIds = updateTermIds();
     var termIDArray = $A(listOfTermIds);
     termIDArray.each( registerDeleteButtons );
    
    
    }
    
    function uninitializeAllEvents()
    {
    try{
     unRegisterAddInstructionLinks();
    
     unRegisterSelectAllCheckBoxes();  
     
     unRegisterDeleteEpochIcons();   
     
     unRegisterAddEpochButton();  
    
     var listOfTermIds = updateTermIds();
     var termIDArray = $A(listOfTermIds);
     termIDArray.each( unRegisterDeleteButtons );
    }catch(ex)
    {
      //alert('exception');
    }
    }
    
    function registerAddEpochButton()
    {
        Event.observe('AddEpoch', 'click', addEpoch);
    } 
    
    function unRegisterAddEpochButton()
    {
        Event.stopObserving('AddEpoch', 'click', addEpoch);
    } 
    
    function addEpoch( event ){
        
       	    uninitializeAllEvents();
        
           <tags:tabMethod  method="addEpoch" 
                         viewName="study/ajax/generateSolicitedAETable" 
                         divElement="'SolicitedAETableArea'" 
                         formName="'command'"
                         onComplete="initializeAllEvents"/>
	   	               
                   
    }
   
   function registerSelectAllCheckBoxes()
    {
      var header_of_all_epochs = $$('.epoch');
       
      for(var i = 0 ; i < header_of_all_epochs.length ; i++ )
      {
        Event.observe('ck'+i, 'click', selectAll);
      }
    }
    
    function unRegisterSelectAllCheckBoxes()
    {
      var header_of_all_epochs = $$('.epoch');
       
      for(var i = 0 ; i < header_of_all_epochs.length ; i++ )
      {
        Event.stopObserving('ck'+i, 'click', selectAll);
      }
    }
    
    function registerAddInstructionLinks()
    {
      var header_of_all_epochs = $$('.epoch');
       
      for(var i = 0 ; i < header_of_all_epochs.length ; i++ )
      {
        Event.observe('epochs['+i+'].descriptionText-id', 'click', addInstructions);   
      }
    }
    
    function unRegisterAddInstructionLinks()
    {
      var header_of_all_epochs = $$('.epoch');
       
      for(var i = 0 ; i < header_of_all_epochs.length ; i++ )
      {
        Event.stopObserving('epochs['+i+'].descriptionText-id', 'click', addInstructions);   
      }
    }
    
    function registerDeleteEpochIcons()
    {
       var all_delete_epoch_links = $$('.'+'delete-epoch'); 
       
       for(var i = 0 ; i < all_delete_epoch_links.length; i++)
       {
         // img element do not have id -> which is target elment
         // anchor element have id -> which is current target element
         // but Event.element(event).id --> always refers to target elment which is img which do not have id
         // bind function is used to interoperate with IE because event.currentTarget would not work in IE.
         // event.currentTarget only works for all standard browsers.      
         
         // removing delete option for BaseLine Epoch
         if( i == 0 )
             $(all_delete_epoch_links[i]).hide();
         else    
             Event.observe($(all_delete_epoch_links[i]).id,'click', callback_delete_epoch.bind($(all_delete_epoch_links[i])));
       }
    }
    
    function unRegisterDeleteEpochIcons()
    {
       var all_delete_epoch_links = $$('.'+'delete-epoch'); 
       
       for(var i = 0 ; i < all_delete_epoch_links.length; i++)
       {
         // img element do not have id -> which is target elment
         // anchor element have id -> which is current target element
         // but Event.element(event).id --> always refers to target elment which is img which do not have id
         // bind function is used to interoperate with IE because event.currentTarget would not work in IE.
         // event.currentTarget only works for all standard browsers.      
         
         // removing delete option for BaseLine Epoch
         if( i == 0 )
             $(all_delete_epoch_links[i]).hide();
         else    
             Event.stopObserving($(all_delete_epoch_links[i]).id,'click', callback_delete_epoch.bind($(all_delete_epoch_links[i])));
       }
    }
    
    function callback_delete_epoch(event)
    {
    
    
       if(!event.currentTarget) event.currentTarget = this;
       
       var delete_epoch_icon_id = event.currentTarget.id;
       var epoch_index = delete_epoch_icon_id.substring("delete-epoch-".length);
       var all_cells_of_this_epoch_column = $$('.col-epoch-'+epoch_index);
       
       var header_of_all_epochs = $$('.epoch');
       
       
       if(confirm("Do you really want to delete this treatment period ?"))
       { 
     //    deleteEpoch( epoch_order );
    
     //    deleteEpochFromBackEnd( epoch_index )
        
         $('th-table1-'+epoch_index).remove();
         $('th-col-epoch-'+epoch_index).remove();
         for(var i = 0 ; i < all_cells_of_this_epoch_column.length ; i++)
         {
           all_cells_of_this_epoch_column[i].remove();
         }
         
         
       }
       else
         return false;
         
        
    }
    
    function deleteEpochFromBackEnd( epoch_index )
    {
       createStudy.deleteEpoch( epoch_index , function(responseStr)
	   	{
             alert('deleted from backend : ' + responseStr);	   	
	   	});
    }
    
    
    function selectAll(event)
    {
      
       var selectall_ckbox = Event.element(event).id;
       var all_ckboxes = $$('.'+selectall_ckbox);
       for(var i = 0 ; i < all_ckboxes.length ; i++)
       {
         if( $(selectall_ckbox).checked )
          $(all_ckboxes)[i].checked = true;
         else
          $(all_ckboxes)[i].checked = false;  
       }   
	     
    }
    function addInstructions(event)
    {
      var event_id = Event.element(event).id;
      var div_id = event_id.gsub(/(\w+)-id/,'#{1}-div');
      var window_id = "window-"+div_id;
      
      if( setOfWindowsOpened.indexOf(window_id) != -1 )
      {
        return;
      }
      else
      {
        setOfWindowsOpened.push( window_id );
      }
      
      
      var xtop = getX(event_id,event);
      var xleft = getY(event_id,event);
      
      
      var win = new Window({ id: window_id , className: "alphacube", closable : false, minimizable : false, maximizable : false, title: "Add Instructions", height:200, width: 450, top: xtop, left: xleft});
      
      win.setDestroyOnClose(); 
      win.setContent( div_id );
      Event.observe(div_id + "-button",'click',function(event){
           
           setOfWindowsOpened = setOfWindowsOpened.without("window-"+div_id);
           Windows.close("window-"+div_id);
      });
      win.show();      
    }
    
    function getX(event_id, event)
    {
      var xtop = Event.pointerY(event);
      
       return xtop + 30;
    }
    
    function getY(event_id, event)
    {
      var xleft = Event.pointerX(event);
      
      if(event_id == 'epochs[0].descriptionText-id')
           xleft = 50;
      else if( event_id == 'epochs[1].descriptionText-id' )
           xleft =  Event.pointerX(event) - 60;         
      else if( event_id == 'epochs[2].descriptionText-id' )
           xleft =  Event.pointerX(event) + 300;         
       return xleft;
    }
    
    
    
    function registerDeleteButtons(termID)
    {
      Event.observe("button-"+termID.value,'click',handleDelete);
    }
    
    function updateTermIds()
    {
	     var listOfTermIds = $$('.eachRowTermID');
	     if(listOfTermIds.length != 0){
	      $('specialLastRow').hide();
	     }
	     else
	     {
	       $('specialLastRow').show();
	       
	     }
	     $('err-section').innerHTML = "";
	     return listOfTermIds;
    
    }
    function handleDelete(event)
    {
                var buttonid = Event.element(event).id;
                var trid = buttonid.gsub(/button/,'tr');
                if(!confirm( "Are you sure you want to delete this?" ))
                  return false;   
                Element.remove(trid);
                updateTermIds(); 
    }
    function checkForm(event)
    {
      $('err-section').innerHTML = "";
      var listOfTermIds = $$('.eachRowTermID');
      
      for(var i = 0 ; i < listOfTermIds.length ; i++)
      {
      
      var one_row_checkboxes = $$('.ck-'+listOfTermIds[i].value);
       
       var atleastOneCheckboxIsSelected = false;
        for(var j = 0 ; j < one_row_checkboxes.length ; j++ )
        {
          atleastOneCheckboxIsSelected = atleastOneCheckboxIsSelected || one_row_checkboxes[j].checked;
        }
        
        if( !atleastOneCheckboxIsSelected )
        {
          var aeText = $('name-'+listOfTermIds[i].value).innerHTML;
          $('err-section').innerHTML += "<li>The Solicited Adverse Event \"" + aeText.substring(0,9)+'....' + "\" is not associated to a treatment period type. Select the appropriate check box(es) to associate it to a treatment period type or delete the solicited Adverse Event.</li>";
          Event.stop(event);
        }
      }
    }

    function isTermAgainAdded( termID )
    {
      var listOfTermIds = $$('.eachRowTermID');
      for(var i = 0 ; i < listOfTermIds.length ; i++)
      {
        if( termID == listOfTermIds[i].value)
        {
          return true;
        }      
      } 
      return false;
    }
    
  	function myCallback(selectedTerms){
  	
	    var listOfTermIDs = new Array();
	    var listOfTerms = new Array();
	    
	    	$H(selectedTerms).keys().each( function(termID) {
	  		
	  		var term = $H( selectedTerms ).get(termID);
	  		if( !isTermAgainAdded(termID))
	  		{
	  		  listOfTermIDs.push( termID );
	  		  listOfTerms.push(term );
	        }
	  	   });
	  
	    
	   	createStudy.addSolicitedAE(listOfTermIDs, listOfTerms, function(responseStr)
	   	{
   	      var listOfTermIds = $$('.eachRowTermID');
          var termIDArray = $A(listOfTermIds);
          termIDArray.each( unRegisterDeleteButtons );
            new Insertion.Before('specialLastRow', responseStr);
   	      listOfTermIds = updateTermIds();
          termIDArray = $A(listOfTermIds);
          termIDArray.each( registerDeleteButtons );
		  
	   	});

   	
   	}
  
    function unRegisterDeleteButtons(termID)
    {
      Event.stopObserving("button-"+termID.value,'click',handleDelete);
    }
    
  
  </script>
  
</head>
<body>
 
  <study:summary />
 
  <form:form name="solicitedAEForm">
  	
  	<tags:aeTermQuery title="Choose CTC terms" isMeddra="${not empty command.aeTerminology.meddraVersion}"  callbackFunctionName="myCallback" version="${not empty command.aeTerminology.meddraVersion ? command.aeTerminology.meddraVersion.id : command.aeTerminology.ctcVersion.id}" ignoreOtherSpecify="true"/>
  	
  	<!--  Idea is copied from tabForm.tag -->
  	<chrome:box title="${tab.longTitle}" >
  		<chrome:flashMessage/>
  		<tags:tabFields tab="${tab}"/>
  		<p id="instructions">
To associate the term to a treatment period type, select the appropriate check box(es). 
Each term can be associated to multiple treatment period types. <br>To associate all terms to a treatment period type, select the check box directly under <b>Add Instructions</b>.
<br>To add specific instructions for the reporting period type, click <b>Add Instructions</b>.
		</p>
	
	    <tags:hasErrorsMessage />
  	
		<p>
		  <ul id="err-section" class="errors">
         </ul>
		  
		</p>
		<input type="hidden" name="_action" value="">
  		<!--  start of body -->
  		
  	  <span id="SolicitedAETableArea">	
  		<study:solicitedAETable />
      </span>
	  		<!--  end of body  -->
  		<tags:tabControls tab="${tab}" flow="${flow}" />
  	</chrome:box>
  </form:form>
 
</body>
</html>
