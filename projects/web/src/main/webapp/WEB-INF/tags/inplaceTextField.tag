<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="propertyName"%>
<%@attribute name="size"%>
<%@attribute name="validations"%>
<span id="${propertyName}-id"><caaers:value path="${propertyName}" /></span>
<script type="text/javascript">
	 var editor_${path}=new Ajax.InPlaceEditor('${propertyName}-id', document.URL, { validations:'${validations}', cancelLink:false, cancelButton:true, okText:'ok', cancelText:'cancel',
	 														 callback: function(form, value) {
	 														 		return '_asynchronous=true&_asyncMethodName=doInPlaceEdit&_ajaxInPlaceEditParam=${propertyName}&_pathToGet=${propertyName}&${propertyName}=' + escape(value);
	 														  	}
	 														  });
</script>