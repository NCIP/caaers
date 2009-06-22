<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<page:applyDecorator name="standardNoHeader">
<html>
<head>
    <title>caAERS || Review Comment</title>
    <tags:dwrJavascriptLink objects="createAE"/>
	<tags:dwrJavascriptLink objects="createStudy"/>
<style type="text/css">
	#main{
			text-align: left;
		}
		.standard-buttons{
			text-align: center;
		}
</style>
<script>
	Event.observe(window, "load", function(){

		$('ok-id').observe("click", function(){
			window.parent.Windows.close(window.parent.curWin.getId());			
		});
	});
</script>
</head>
<body>
<tags:standardForm title="Confirmation">
	<jsp:attribute name="singleFields">
		<p>The following comment is saved sucessfully</p>
		<pre>${command.comment}</pre>
	</jsp:attribute>
	<jsp:attribute name="navButtons">
		<input id="ok-id" type="button" value="OK" />
	</jsp:attribute>
</tags:standardForm>
</body>
</html>
</page:applyDecorator>  
