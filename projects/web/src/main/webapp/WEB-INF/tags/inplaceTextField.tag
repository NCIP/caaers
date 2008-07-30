<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="propertyName"%>
<%@attribute name="size"%>
<%@attribute name="validations"%>
<span id="${propertyName}-id"><caaers:value path="${propertyName}" /></span>
<script type="text/javascript">

var symbol = '&';

if( $("command").action.indexOf('?') == -1 )
  symbol = '?';

	 var editor_${path}=new Ajax.InPlaceEditor('${propertyName}-id', document.URL + symbol +'subview', { validations:'${validations}', cancelLink:false, cancelButton:true, okText:'ok', cancelText:'cancel', highlightcolor: 'lavender',
	 														 callback: function(form, value) {
	 														 		return '_asynchronous=true&_asyncMethodName=doInPlaceEdit&_ajaxInPlaceEditParam=${propertyName}&_pathToGet=${propertyName}&${propertyName}=' + escape(value);
	 														  	}
	    													  });
	 var editableID = "${propertyName}-id";
	 
	 if($(editableID).innerHTML.toLowerCase() == "enter name here")
	 {
	  try{
	   editor_${path}.enterEditMode('load');
	   }
       catch(ex)
       {}	   
	 }														  
</script>