<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField"%>
<%@attribute name="size"%>
<%@attribute name="validations"%>
<span id="${field.propertyName}-id"><caaers:value path="${field.propertyName}" /></span>
<script type="text/javascript">
	 var editor_${path}=new Ajax.InPlaceEditor('${field.propertyName}-id', document.URL, { validations:'${validations}', cancelLink:false, cancelButton:true, okText:'ok', cancelText:'cancel',
	 														 callback: function(form, value) {
	 														 		return '_asynchronous=true&_asyncMethodName=doInPlaceEdit&_ajaxInPlaceEditParam=${field.propertyName}&_pathToGet=${field.propertyName}&${field.propertyName}=' + escape(value);
	 														  	}
	 														  });
</script>