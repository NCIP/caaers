<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="method" required="true"%>
<%@attribute name="divElement" required="true"%>
<%@attribute name="viewName"%>
<%@attribute name="formName"%>
<%@attribute name="params"%>
<%@attribute name="javaScriptParam"%>
<%@attribute name="onSuccess"%>
<%@attribute name="onComplete"%>
<%@attribute name="onFailure"%>
<%@attribute name="insertion"%>

<c:set var="callbackOpts" value=""></c:set>
<c:if test="${! empty onComplete}">
    <c:set var="callbackOpts" value="${callbackOpts}onComplete:${onComplete },"></c:set>
</c:if>

<c:if test="${! empty onSuccess}">
    <c:set var="callbackOpts" value="${callbackOpts}onSuccess:${onSuccess },"></c:set>
</c:if>

<c:if test="${! empty onFailure}">
    <c:set var="callbackOpts" value="${callbackOpts}onFailure:${onFailure },"></c:set>
</c:if>

<c:choose>
<c:when test="${empty formName}">
tempTargetVar=$("command")._target!=null?$("command")._target.name:null;
if(tempTargetVar!=null){
	$("command")._target.name="notSubmitted";
}

var symbol = '&';

if( $("command").action.indexOf('?') == -1 )
  symbol = '?';

new Ajax.Updater(${divElement},$("command").action + symbol + "subview", 
					{parameters:"decorator=nullDecorator&_asynchronous=true&_asyncMethodName=${method}&_asyncViewName=${viewName}&${params}&"+${empty javaScriptParam?"''":javaScriptParam}+"&"+Form.serialize('command'),
					${callbackOpts} asynchronous:true, evalScripts:true});

    if(tempTargetVar != null) {
	    $("command")._target.name = tempTargetVar;
    }
</c:when>
<c:otherwise>
tempTargetVar=$(${formName})._target!=null?$(${formName})._target.name:null;
if(tempTargetVar!=null){
	$(${formName})._target.name="notSubmitted";
}

var symbol = '&';

if( $("command").action.indexOf('?') == -1 )
  symbol = '?';

new Ajax.Updater(${divElement},
                    $(${formName}).action + symbol + "subview", 
					{parameters:"decorator=nullDecorator&_asynchronous=true&_asyncMethodName=${method}&_asyncViewName=${viewName}&${params}&"+${empty javaScriptParam?"''":javaScriptParam}+"&"+Form.serialize(${formName}),
					${callbackOpts} asynchronous:true, evalScripts:true});

    if(tempTargetVar != null){
	    $(${formName})._target.name = tempTargetVar;
    }
</c:otherwise>
</c:choose>