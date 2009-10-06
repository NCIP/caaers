<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  Ajax loading indicator -->
<div id="ajax-loading-indictor" style="position:absolute; width:100%; height:100%; top:0; left:0;z-index:5;">
    <div style="margin:200px auto; z-index: 10; width: 250px; height: 250px; opacity: 0.6; background-color:#000000; filter:alpha(opacity=60); -moz-border-radius:30px; -webkit-border-radius:30px; border-radius:30px;">
        <img src="<c:url value="/images/ajax.indicator.orange.gif"/>" alt="ajax activity indicator" style="margin:25px auto 0;"/>
    </div>
</div>
<!--  Ajax loading indicator -->
