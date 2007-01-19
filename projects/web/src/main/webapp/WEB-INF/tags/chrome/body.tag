<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@attribute name="title"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="body">
    <tr>
        <td id="current">${title}</td>
    </tr>
    <tr><td class="display">
        <jsp:doBody/>
    </td></tr>
    <tr>
        <td class="display_B"><img src="<chrome:imageUrl name="display_BL.gif"/>" align="left" hspace="0"><img src="<chrome:imageUrl name="display_BR.gif"/>" align="right" hspace="0"></td>
    </tr>
</table>