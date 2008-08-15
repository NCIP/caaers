<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<html>
<head>
<title>${tab.longTitle}</title>
<tags:includePrototypeWindow />
<tags:dwrJavascriptLink objects="assignParticipant" />
<tags:dwrJavascriptLink objects="createAE, createStudy" />


<script type="text/javascript">
	
	var ParMedHistoryClass = Class.create();
	Object.extend(ParMedHistoryClass.prototype, {
		initalize : function() {
			this.win = null;
			this.button = null;
		},
		initializePopup : function() {
			//will create the Window 
			this.win = new Window('popUpWin',{className:"alphacube", 
 	 	 		destroyOnClose:true, 
 	 	 		title:'Manage Subject Medical History',  
 	 	 		width:700,  height:530, 
 				top: 30, left: 300,
 				onBeforeShow:this.beforeShow.bind(this),
 				onDestroy:this.destroy.bind(this)});
		},
		
		showPopup : function(btn, itemType, index){
			
			//the button should be disabled
			this.button = $(btn);
			this.button.disable();
			
			//setup the parameters
			var task = 'create';
			if(index > -1) task = 'edit'; //fining the action based on index (-1 means we are in the create mode)
			
			var paramHash = new Hash(); //setup parameters
			paramHash.set('task', task);
			paramHash.set('index', index);
			this.populateDeafultParameters(itemType, paramHash);
			
			//create the popup window
			this.initializePopup();
			var url = $('baseCommand').action + "?subview";
			this.win.setAjaxContent(url, {parameters : paramHash.toQueryString()});
			
		},
		
		savePopup : function (formId, itemType){
			var frm = $(formId); //the id of the form, available in child popup.
			
			var paramHash = new Hash(); //setup parameters
			paramHash.set('task', 'save');
			this.populateDeafultParameters(itemType, paramHash); 
			
			var formParams = Form.serialize(formId); //attach the child window form contents aswell along with the default parameter list
			var url = $('baseCommand').action + "?subview";
			this.win.setAjaxContent(url, {parameters : paramHash.toQueryString() + "&" + formParams});
		},

        closePopup : function (){
			//make an ajax call and refresh the currentItem display area
			alert('closing');
            
            //this function will close the popup window
			Windows.closeAll();
		},
        
        populateDeafultParameters : function(itemType, paramHash){
			//will populate the default parameters, to support ajax communication
			var page = ${tab.number};
			var target = '_target' + ${tab.number}; 
			paramHash.set('_asynchronous', true);
			paramHash.set('decorator', 'nullDecorator');
			paramHash.set('currentItem', itemType);
			paramHash.set('_page', page);
			paramHash.set(target, page);
			paramHash.set('title', 'Manage Subject Medical History');
		},
		
		beforeShow : function(){
		},

        destroy : function() {

			if(this.button) this.button.enable(); //enable the disabled button (ifany)
			
			//refresh the state of the this object
			this.button = null;
			this.win = null; 
		}
	});

	var mHistory;
	Event.observe(window, 'load', function () {
		mHistory = new ParMedHistoryClass();
	});
	
</script>

</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" title="Medical History"	willSave="false" formId="baseCommand">
  <jsp:attribute name="singleFields">
	<%-- 
		Note : Do not remove the currentItem and action hidden params, they should be set to empty
	 --%>
	 <input type="hidden" name="currentItem" value="" />
	 <input type="hidden" name="task" value="" />

	<chrome:division title="Disease Information" collapsable="true"	id="mh-disease">
	</chrome:division>



    <chrome:division title="Metastatic Site" collapsable="true"	id="mh-meta">
	AAA
	</chrome:division>


    <chrome:division title="Pre-Existing Conditions" collapsable="true"	id="mh-pre">
	</chrome:division>



    <chrome:division title="Con-Meds" collapsable="true" id="mh-conmeds">
	AAA
	</chrome:division>



    <chrome:division id="priorTherapies" collapsable="true" title="Prior Therapies">
    <table width="100%" bgcolor="gray">
				<th>
					<td>Prior Therapies</td>
					<td>Info added </td>
					<td><input type="button" value="Add" onclick="mHistory.showPopup(this, 'priorTherapy', -1);"/></td>
				</th>
    </table>
    </chrome:division>
      
  </jsp:attribute>
</tags:tabForm>
</body>

</html>
