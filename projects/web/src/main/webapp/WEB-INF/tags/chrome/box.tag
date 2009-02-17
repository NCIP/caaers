<%-- TODO: support for inner tabs (needs uniform controller support first) --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@attribute name="title"%>
<%@attribute name="id"%>
<%@attribute name="cssClass"%>
<%@attribute name="style"%>
<%@attribute name="noBackground" required="false" %>
<%@attribute name="additionalTitle" required="false" %>

<%-- If this attribute is true, the provided contents will be wrapped in a .content div.
     Use it if the box will only need one content div -- i.e., it doesn't contain any
     chrome:divisions with titles. --%>
<%@attribute name="autopad" required="false" %>
<%@attribute name="collapsable" required="false" %>


<c:if test="${noBackground}">
<div class=" ${cssClass}"
    <tags:attribute name="id" value="${id}"/> <tags:attribute name="style" value="${style}"/>>
    <!-- inner border -->
    <div>
        <div class="interior">
            <c:if test="${autopad}"><div class="content"></c:if>
            <jsp:doBody/>
            <c:if test="${autopad}"></div></c:if>
        </div>
    </div>
    <!-- end inner border -->
</div>
<!-- end box -->
</c:if>


<c:if test="${!noBackground}">
<div class="box ${cssClass}"
    <tags:attribute name="id" value="${id}"/> <tags:attribute name="style" value="${style}"/>>

    <!-- box header -->
    <div class="header"><div class="background-L"><div class="background-R">
        <table width="100%" border="0" cellpadding="1" cellspacing="0"><tr>
            <td align="left"><h2><c:if test="${collapsable and not empty id}"><a style="cursor:pointer;" onClick="SwitchCollapsableState('contentOf-${id}', '${id}')"><img id="image-${id}" src="<c:url value="/images/arrow-${collapsed ? 'right' : 'down'}.png" />" border="0" style="padding-right:5px;"/></a></c:if>${title}</h2></td>
            <td align="left" width="100%" style="padding-right:10px;">${additionalTitle}</td>
        </tr></table>
      <c:if test="${!empty title}"><div class="hr"></div></c:if>
    </div></div></div>
    <!-- end box header -->

    <!-- inner border -->
    <div class="border-T"><div class="border-L"><div class="border-R"><div class="border-B"><div class="border-TL"><div class="border-TR"><div class="border-BL"><div class="border-BR">
        <div class="interior" id="contentOf-${id}" style="display:${collapsed ? "none" : "inline"};">
            <c:if test="${autopad}"><div class="content"></c:if>
            <jsp:doBody/>
            <c:if test="${autopad}"></div></c:if>
        </div>
    </div></div></div></div></div></div></div></div>
    <!-- end inner border -->
</div>
<!-- end box -->
</c:if>