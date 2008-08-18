<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="path"%>
<%@attribute name="size"%>
<%@attribute name="validations"%>
<span id="${path}-id"><caaers:value path="${path}" /></span>
<script type="text/javascript">

var symbol = '&';

if( $("command").action.indexOf('?') == -1 )
  symbol = '?';

	 var editor_${path}=new Ajax.InPlaceEditor('${path}-id', document.URL + symbol +'subview', { validations:'${validations}', cancelLink:false, cancelButton:true, okText:'ok', cancelText:'cancel', highlightcolor: 'lavender',
	 														 callback: function(form, value) {
	 														 		return '_asynchronous=true&_asyncMethodName=doInPlaceEdit&_ajaxInPlaceEditParam=${path}&_pathToGet=${path}&${path}=' + escape(value);
	 														  	}
	    													  });
	 var editableID = "${path}-id";
	 
	 if($(editableID).innerHTML.toLowerCase() == "enter name here")
	 {
	  try{
	   editor_${path}.enterEditMode('load');
	   }
       catch(ex)
       {}	   
	 }														  
</script>