<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
	<script language="JavaScript" type="text/JavaScript">
	
	function selectType()
	{
		var type = $('select-type-id').value;
		if(type == 'medDRA'){
			$('meddra-import-id').style.display = '';
			$('file-id').style.display = 'none';
		}else{
			$('meddra-import-id').style.display = 'none';
			$('file-id').style.display = '';
		}		
	}
	
	Event.observe(window, "load", function() {
		selectType();
	});
	
</script>

</head>
<body>

    <tags:tabForm tab="${tab}" flow="${flow}" formName="ImportForm" enctype="multipart/form-data">
        <jsp:attribute name="singleFields">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
		<tags:instructions code="LBL_import_instruction"/>
	</div>

		<div class="row">
        	<div class="label"><tags:requiredIndicator/><caaers:message code="LBL_type"/></div>
			<div class="value">
				<form:select path="type" id="select-type-id" onchange="javascript:selectType()">
					<form:option value="">Please select</form:option>
         			<form:option value="study">Study / Protocol</form:option>
         			<form:option value="participant">Subject</form:option>
         			<form:option value="investigator">Investigator</form:option>
         			<form:option value="researchStaff">Research staff</form:option>
         			<form:option value="organization">Organization</form:option>
         			<form:option value="agent">Agent</form:option>
         			<form:option value="agentSpecificAEList">Agent Specific AE List</form:option>
         			<form:option value="medDRA">MedDRA</form:option>
        		</form:select>
			</div>
		</div>
		<div class="row" style="display:none" id="file-id">
			<div class="label">
				<tags:requiredIndicator/>
				<caaers:message code="LBL_dataFile"/>
			</div>
			<div class="value" >
				<input type="file" name="dataFile" id="file-input-id"/>
			</div>
		</div>
		<div id="meddra-import-id" style="display:none">
			<div class="row">
			<div class="label">
				<tags:requiredIndicator/>
				<caaers:message code="LBL_meddraVersionName"/>
			</div>
			<div class="value">
				<form:input id="meddraVersionName-id" path="meddraVersionName" />
			</div>
			</div>
			<div class="row">
			<div class="label">
				<tags:requiredIndicator/>
				<caaers:message code="LBL_socFile"/>
			</div>
			<div class="value">
				<input id="socFile-id" type="file" name="socFile" />
			</div>
			</div>
			<div class="row">
			<div class="label">
				<tags:requiredIndicator/>
				<caaers:message code="LBL_hlgtFile"/>
			</div>
			<div class="value">
				<input id="" type="file" name="hlgtFile" />
			</div>
			</div>
			<div class="row">
			<div class="label">
				<tags:requiredIndicator/>
				<caaers:message code="LBL_socHlgtFile"/>
			</div>
			<div class="value">
				<input id="" type="file" name="socHlgtFile" />
			</div>
			</div>
			<div class="row">
			<div class="label">
				<tags:requiredIndicator/>
				<caaers:message code="LBL_hltFile"/>
			</div>
			<div class="value">
				<input id="" type="file" name="hltFile" />
			</div>
			</div>
			<div class="row">
			<div class="label">
				<tags:requiredIndicator/>
				<caaers:message code="LBL_hlgtHltFile"/>
			</div>
			<div class="value">
				<input id="" type="file" name="hlgtHltFile" />
			</div>
			</div>
			<div class="row">
			<div class="label">
				<tags:requiredIndicator/>
				<caaers:message code="LBL_ptFile"/>
			</div>
			<div class="value">
				<input id="" type="file" name="ptFile" />
			</div>
			</div>
			<div class="row">
			<div class="label">
				<tags:requiredIndicator/>
				<caaers:message code="LBL_hltPtFile"/>
			</div>
			<div class="value">
				<input id="" type="file" name="hltPtFile" />
			</div>
			</div>
			<div class="row">
			<div class="label">
				<tags:requiredIndicator/>
				<caaers:message code="LBL_lltFile"/>
			</div>
			<div class="value">
				<input id="" type="file" name="lltFile" />
			</div>
			</div>
		</div>

 		</jsp:attribute>
 		<jsp:attribute name="tabControls">
	 		<tags:tabControls tab="${tab}" flow="${flow}" willSave="false" saveButtonLabel="Save">
	 		</tags:tabControls>
	 	</jsp:attribute>
    </tags:tabForm>    
</body>
</html>