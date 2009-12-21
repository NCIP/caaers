<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <title>${tab.longTitle}</title>
  
  <tags:dwrJavascriptLink objects="createStudy, createAE"/>

  <script type="text/javascript">

   var catSel = null;
   var setOfWindowsOpened = new Array();
  
   Event.observe(window, 'load', initializeAllEvents); 
    
    function initializeAllEvents()
    {
        Event.observe('flow-prev', 'click', checkForm);
        Event.observe('flow-next', 'click', checkForm);

    <c:if test="${param.studyId != null}">
        Event.observe('flow-update', 'click', checkForm);
    </c:if>

        registerAddInstructionLinks();
        registerSelectAllCheckBoxes();
        registerDeleteEpochIcons();
        registerAddEpochButton();

        var listOfTermIds = updateTermIds();
        var termIDArray = $A(listOfTermIds);
        termIDArray.each(registerDeleteButtons);

    }

   function uninitializeAllEvents()
   {
       try {
           unRegisterAddInstructionLinks();
           unRegisterSelectAllCheckBoxes();
           unRegisterDeleteEpochIcons();
           unRegisterAddEpochButton();

       } catch(ex)
       {
           //    alert('exception');
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
                         params="_addEpoch=true"
                         onComplete="initializeAllEvents"/>
	   	               
                   
    }

   function deleteEpoch(epoch_index) {

   <tags:tabMethod  method="deleteEpoch"
       viewName="study/ajax/generateSolicitedAETable"
       divElement="'SolicitedAETableArea'"
       formName="'command'"
       params="_deleteEpoch=true"
       onComplete="initializeAllEvents"/>
   }

   function registerSelectAllCheckBoxes()
    {
      var header_of_all_epochs = $$('.epoch');
       
      for(var i = 0 ; i < header_of_all_epochs.length ; i++ )
      {
        Event.observe('ck'+i, 'click', selectAll);
        
        var all_ck_boxes_for_this_column = $$('.ck'+i);
        for(var j = 0 ; j < all_ck_boxes_for_this_column.length ; j++ )
        {
          Event.observe(all_ck_boxes_for_this_column[j],'click', monitorCheckBoxes);
        } 
      }
    }
    
    function monitorCheckBoxes(event)
    {
      if( !$(Event.element(event).id).checked )
      {
        // if any check box is unselected, unselect appropriate selectAll checkBox
        var selectAllCkBoxID = Event.element(event).id.gsub(/(\w+)-(\d+)/,'#{1}'); 
        $(selectAllCkBoxID).checked = false;
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
      var all_instructionLinks = $$('.instructionLinks');
      
      for(var i = 0 ; i < all_instructionLinks.length ; i++ )
      {
        Event.observe(all_instructionLinks[i], 'click', addInstructions);   
      }
    }
    
    function unRegisterAddInstructionLinks()
    {
       var all_instructionLinks = $$('.instructionLinks');
       
      for(var i = 0 ; i < all_instructionLinks.length ; i++ )
      {
        Event.stopObserving(all_instructionLinks[i], 'click', addInstructions);   
      }
    }
    
    function registerDeleteEpochIcons() {
       var all_delete_epoch_links = $$('.'+'delete-epoch'); 
       for(var i = 0 ; i < all_delete_epoch_links.length; i++) {
             Event.observe($(all_delete_epoch_links[i]).id, 'click', callback_delete_epoch.bind($(all_delete_epoch_links[i])));
       }
    }
    
    function unRegisterDeleteEpochIcons() {
       var all_delete_epoch_links = $$('.'+'delete-epoch'); 
       for(var i = 0 ; i < all_delete_epoch_links.length; i++) {
             Event.stopObserving($(all_delete_epoch_links[i]).id,'click', callback_delete_epoch.bind($(all_delete_epoch_links[i])));
       }
    }
    
    function callback_delete_epoch(event)
    {
        if (!event.currentTarget) event.currentTarget = this;

        var delete_epoch_icon_id = event.currentTarget.id;
        var epoch_index = delete_epoch_icon_id.substring("delete-epoch-".length);
        var all_cells_of_this_epoch_column = $$('.col-epoch-' + epoch_index);
        var header_of_all_epochs = $$('.epoch');

        if (confirm("Do you really want to delete this treatment period ?"))
        {
            uninitializeAllEvents();

            $('th-table1-' + epoch_index).remove();
            $('th-col-epoch-' + epoch_index).remove();
            for (var i = 0; i < all_cells_of_this_epoch_column.length; i++) {
                all_cells_of_this_epoch_column[i].remove();
            }
            deleteEpoch(epoch_index);
        }
        else
            return false;
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
      div_id = "study." + div_id;
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
      
      
      var win = new Window({ id: window_id , className: "alphacube", closable : false, minimizable : false, maximizable : false, title: "Edit Instructions", height:200, width: 450, top: xtop, left: xleft});
      
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
      else 
           xleft =  Event.pointerX(event) - 60;         
      
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
    <%--<tags:instructions code="study.study_evalpdtypes.top" />--%>
  	  	
  	<!--  Idea is copied from tabForm.tag -->
  	<chrome:box title="${tab.longTitle}" >
  		<chrome:flashMessage/>

<tags:aeTermQuery title="Choose CTC terms" isMeddra="${not empty command.study.aeTerminology.meddraVersion}"
  		callbackFunctionName="myCallback"
          noBackground="true"
  	 	version="${not empty command.study.aeTerminology.meddraVersion ? command.study.aeTerminology.meddraVersion.id : command.study.aeTerminology.ctcVersion.id}"
  	 	ignoreOtherSpecify="false" isAjaxable="true"/>

  		<tags:tabFields tab="${tab}"/>
  		<p><tags:instructions code="study.study_evalpdtypes.2" /></p>

          <tags:hasErrorsMessage/>
          <tags:jsErrorsMessage/>

          <p>
		  <ul id="err-section" class="errors"></ul>
		  
		</p>
		<input type="hidden" name="_action" value="">
  		<!--  start of body -->
  		
  	  <span id="SolicitedAETableArea">	
  		<study:solicitedAETable displayOnly="false" />
      </span>
	  		<!--  end of body  -->
  		
  	</chrome:box>
	<tags:tabControls tab="${tab}" flow="${flow}" />
  </form:form>
 
</body>
</html>
