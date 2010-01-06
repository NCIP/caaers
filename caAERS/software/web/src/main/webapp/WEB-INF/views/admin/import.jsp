<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
	<script language="JavaScript" type="text/JavaScript">
	
	function selectType()
	{
		var type = $('select-type-id').value;
		if(type == 'Please select'){
			$('file-id').name = '';
		}
		if(type == 'Import MedDRA'){
		
		}		
	}
	
</script>

</head>
<body>

<p id="instructions">
        Import Studies/Protocols or Subjects into caAERS
</p>
    <tags:tabForm tab="${tab}" flow="${flow}" formName="ImportForm" enctype="multipart/form-data">
        <jsp:attribute name="singleFields">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
	</div>

		<div class="row">
        	<div class="label"><ui:label path="type" text="Import" labelProperty="type" required="true"/></div>
			<div class="value">
				<form:select path="type" id="select-type-id">
					<form:option value="">Please select</form:option>
         			<form:option value="study">Study / Protocol</form:option>
         			<form:option value="participant">Subject</form:option>
         			<form:option value="investigator">Investigator</form:option>
         			<form:option value="researchStaff">Research staff</form:option>
         			<form:option value="organization">Organization</form:option>
         			<form:option value="medDRA">MedDRA</form:option>
        		</form:select>
        		<tags:errors path="type"/>
			</div>
		</div>
		<div class="row">
			<div class="label">File</div>
			<div class="value">
				<input id="file-id" type="file" name="dataFile" />
			</div>
			<tags:errors path="dataFile"/>
		</div>

 		</jsp:attribute>
 		<jsp:attribute name="tabControls">
	 		<tags:tabControls tab="${tab}" flow="${flow}" willSave="false" saveButtonLabel="Save">
	 		</tags:tabControls>
	 	</jsp:attribute>
    </tags:tabForm>    
</body>
</html>