
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
		showPopup : function(btn, itemType) {
			this.button = $(btn);
			this.button.disable();
			
			var viewName = '';
			var winTitle = "Prior Therapy";
			var page = ${tab.number};
			var target = '_target' + ${tab.number}; 
			 
			this.win = new Window({className:"alphacube", 
 	 	 		destroyOnClose:true, 
 	 	 		title:winTitle,  
 	 	 		width:700,  height:530, 
 				top: 30, left: 300,
 				onBeforeShow:this.beforeShow.bind(this),
 				onDestroy:this.destroy.bind(this)});
				
			var url = $('baseCommand').action;		
 			this.win.setAjaxContent(url, {_asynchronous:true,decorator='nullDecorator' , _asyncMethodName:'createPriorTherapy', _asyncViewName:'priorTherapyDetails', _page:page});
		},
		findViewName : function(itemType){
		},
		
		beforeShow : function(){
			
		},
		destroy : function() {
			this.button.enable();
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
	<chrome:section id="priorTherapySection-id">
		<jsp:attribute name="header">
			<table width="100%" bgcolor="gray">
				<th>
					<td>Prior Therapies</td>
					<td>Info added </td>
					<td><input type="button" value="Add" onclick="mHistory.showPopup(this, 'priorTherapy');"/></td>
				</th>
			</table>
		</jsp:attribute>
		<jsp:attribute name="content">
		
		</jsp:attribute>
	</chrome:section>
  </jsp:attribute>
</tags:tabForm>
</body>

</html>
