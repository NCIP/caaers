<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ attribute name="id" required="false" %>
<%@ attribute name="onclick" required="false" %>
<%@ attribute name="icon" required="false" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="href" required="false" %>
<%@ attribute name="cssClass" required="false" %>
<%@ attribute name="markupWithTag" required="false" %>
<%@ attribute name="color" required="true" %>
<%@ attribute name="size" required="false" %>
<%@ attribute name="type" required="false" %>
<%@ attribute name="disabled" required="false" %>

<<c:choose><c:when test="${markupWithTag=='a'}">a</c:when><c:otherwise>button</c:otherwise></c:choose>
	class="omnipotent-button ${color}<c:if test="${not empty size}"> ${size}</c:if><c:if test="${not empty cssClass}"> ${cssClass}</c:if>"
	<c:if test="${not empty type && markupWithTag!='a'}">
		type="${type}"
	</c:if>
	<c:if test="${not empty disabled && markupWithTag!='a'}">
		disabled="${disabled}"
	</c:if>
	
	<c:if test="${not empty id}">
		id="${id}" 
	</c:if>
	<c:if test="${not empty onclick}">
    	onclick="${onclick}"
	</c:if>>
	<table onclick="location.href='<c:if test="${not empty href}">${href}</c:if>';">
		<tr>
			<td class="l"></td>
			<td class="m">
				<c:if test="${fn:toLowerCase(icon) == 'save'}">
					<c:choose>
						<c:when test="${size=='small'}">
							<img src="<chrome:imageUrl name="../buttons/button_icons/small/disk_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/disk_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) =='back'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/back_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/back_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) =='save &amp; back'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/saveback_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/saveback_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) == 'add' || fn:toLowerCase(icon) =='+'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/add_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/add_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) == 'page'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/page_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/page_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) == 'window'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/window_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/window_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) == 'check'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/check_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/check_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) == 'subject'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/subject_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/subject_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) == 'x'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/x_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/x_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) == 'search'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/search_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/search.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) == 'add multiple'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/addmultiple_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/addmultiple_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) == 'edit'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/edit_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/edit_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				
				${value}
				
				<c:if test="${fn:toLowerCase(icon) == 'save &amp; continue' || fn:toLowerCase(icon) =='save & continue'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/savecontinue_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/savecontinue_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${fn:toLowerCase(icon) == 'continue' || fn:toLowerCase(icon) =='next'}">
					<c:choose>
						<c:when test="${size=='small'}">
		    				<img src="<chrome:imageUrl name="../buttons/button_icons/small/continue_icon_small.png"/>" alt="" />
						</c:when>
						<c:otherwise>
							<img src="<chrome:imageUrl name="../buttons/button_icons/continue_icon.png"/>" alt="" />
						</c:otherwise>
					</c:choose>
				</c:if>
			</td>
			<td class="r"></td>
		</tr>
	</table>

</<c:choose><c:when test="${markupWithTag=='a'}">a</c:when><c:otherwise>button</c:otherwise></c:choose>>