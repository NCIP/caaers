<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="ae1" required="false" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
<%@attribute name="ae2" required="false" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
<%@attribute name="fillerRow" required="false" type="java.lang.Boolean" %>

<c:if test="${fillerRow}">
    <tr>
        <td colspan="10" class="fillerRow">
            <hr align="center" width="96%" size="1" color="blue">
        </td>
    </tr>
</c:if>

<c:set var="widgetId" value="${ae1.id}" />

<c:if test="${not fillerRow}">
    <c:if test="${not empty ae1}">
        <tr id="aewd-${widgetId}-tr-iae">
            <td width="${requestScope.widthSource}">${ae1.source}</td>
            <td width="${requestScope.widthId}">${empty ae1.externalID ? '-' : ae1.externalID }</td>
            <td width="${requestScope.widthTerm}"> ${ae1.term.displayName}</td>
            <td width="${requestScope.widthGrade}">${ae1.grade}</td>
            <td width="${requestScope.widthStartDate}">${ae1.startDate}</td>
            <td width="${requestScope.widthEndDate}">${ae1.endDate}</td>
            <td width="${requestScope.widthVerbatim}">${ae1.verbatim}</td>
            <td width="${requestScope.widthWhySerious}">${ae1.whySerious}</td>
            <td width="${requestScope.widthAttribution}">${ae1.attribution}</td>
            <td width="${requestScope.widthActions}" class="actionBtns">
                <div id="aewd-${widgetId}-div-edit" style="${empty ae2 ? 'display:none;' : ''}" class="wgtBtnDiv">
                    <tags:button  id="aewd-${widgetId}-btn-edit" value="Edit" color="blue" size="small" cssClass="wgtBtn wgtBtnEdit" type="button" icon="edit"
                                  onclick="wt${widgetId}.onEditBtnClick(this);" />
                </div>
                <div id="aewd-${widgetId}-div-cancel" style="display:none;" class="wgtBtnDiv">
                    <tags:button  id="aewd-${widgetId}-btn-cancel" value="Save" color="blue" size="small" cssClass="wgtBtn wgtBtnCancel" type="button" icon="save"
                                  onclick="wt${widgetId}.onCancelBtnClick(this);" />
                </div>
                <div id="aewd-${widgetId}-div-find" style="${empty ae2 ? '' :'display:none;'}"  class="wgtBtnDiv" >
                    <tags:button  id="aewd-${widgetId}-btn-find" value="Find" color="orange" cssClass="wgtBtn wgtBtnFind" type="button"  size="small" icon="search"
                                  onclick="wt${widgetId}.onFindBtnClick(this);" />
                </div>

                <a name="a${widgetId}" />
            </td>
        </tr>
    </c:if>
        <tr id="aewd-${widgetId}-tr-eae" style="${empty ae2 ? 'display:none;' : ''}">
            <td width="${requestScope.widthSource}" class="eae">${ae2.source}</td>
            <td width="${requestScope.widthId}"  class="eae">${ae2.externalID}</td>
            <td width="${requestScope.widthTerm}"  class="eae">${ae2.term.displayName}</td>
            <td width="${requestScope.widthGrade}"  class="eae">${ae2.grade}</td>
            <td width="${requestScope.widthStartDate}"  class="eae">${ae2.startDate}</td>
            <td width="${requestScope.widthEndDate}"  class="eae">${ae2.endDate}</td>
            <td width="${requestScope.widthVerbatim}"  class="eae">${ae2.verbatim}</td>
            <td width="${requestScope.widthWhySerious}"  class="eae">${ae2.whySerious}</td>
            <td width="${requestScope.widthAttribution}"  class="eae">${ae2.attribution}</td>
            <td width="${requestScope.widthActions}">
              &nbsp;
            </td>
        </tr>
    <tr id="aewd-${widgetId}-tr-nomatch" style="${empty ae2 ? '' : 'display:none;'}">
        <td width="100%" class="tdNoMatch" colspan="10">No matching AE found</td>
    </tr>

    <tr id="aewd-${widgetId}-tr-pick">
        <td id="aewd-${widgetId}-tbl-t2td"  width="94%" colspan="9">
            <div id="aewd-${widgetId}-t2div" style="display:none;" class="yui-skin-sam picker-div">
            </div>
        </td>
        <td width="${requestScope.widthActions}" class="actionBtns">
            <div id="aewd-${widgetId}-div-link" style="display:none;"  class="wgtBtnDiv" >
                <tags:button  id="aewd-${widgetId}-btn-link" value="Link" color="green" cssClass="wgtBtn wgtBtnLink" type="button"  size="small" icon="add"
                              onclick="wt${widgetId}.onLinkBtnClick(this)" />
            </div>
            <div id="aewd-${widgetId}-div-unlink" style="display:none;"  class="wgtBtnDiv" >
                <tags:button  id="aewd-${widgetId}-btn-unlink" value="Unlink" color="red" cssClass="wgtBtn wgtBtnUnlink" type="button"  size="small" icon="delete"
                              onclick="wt${widgetId}.onUnlinkBtnClick(this)" />
            </div>
        </td>
    </tr>
    <script type="text/javascript">
       var wt${widgetId} = new AE.aeWidget(${widgetId}, ${ae1.id}, ${empty ae2 ? -1 : ae2.id});
    </script>
</c:if>



<%--<div id="aewd-${widgetId}" class="aeLinkWidget">--%>
    <%--<table id="aewd-${widgetId}-tbl" width="100%">--%>
        <%--<tr id="aewd-${widgetId}-tbltr1">--%>
            <%--<td id="aewd-${widgetId}-tbl-t1td" width="99%">--%>
                <%--<div id="aewd-${widgetId}-t1div">--%>
                    <%--<table width="100%" id="aewd-${widgetId}-t1">--%>

                        <%--<tr id="aewd-${widgetId}-t1trae1">--%>
                            <%--<td width="6%">${ae1.source}</td>--%>
                            <%--<td width="2%">${ae1.id}</td>--%>
                            <%--<td width="23%"> ${ae1.term.displayName}</td>--%>
                            <%--<td width="14%">${ae1.grade}</td>--%>
                            <%--<td width="6%">${ae1.startDate}</td>--%>
                            <%--<td width="6%">${ae1.endDate}</td>--%>
                            <%--<td width="23%">${ae1.verbatim}</td>--%>
                            <%--<td width="10%">${ae1.whySerious}</td>--%>
                            <%--<td width="10%">${ae1.attribution}</td>--%>
                        <%--</tr>--%>
                        <%--<c:if test="${not empty ae2}">--%>

                            <%--<tr id="aewd-${widgetId}-t1trae2">--%>
                                <%--<td width="6%">${ae2.source}</td>--%>
                                <%--<td width="2%">${ae2.id}</td>--%>
                                <%--<td width="23%"> ${ae2.term.displayName}</td>--%>
                                <%--<td width="14%">${ae2.grade}</td>--%>
                                <%--<td width="6%">${ae2.startDate}</td>--%>
                                <%--<td width="6%">${ae2.endDate}</td>--%>
                                <%--<td width="23%">${ae2.verbatim}</td>--%>
                                <%--<td width="10%">${ae2.whySerious}</td>--%>
                                <%--<td width="10%">${ae2.attribution}</td>--%>
                            <%--</tr>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${empty ae2}">--%>
                            <%--<tr id="aewd-${widgetId}-tr-nomatch">--%>
                                <%--<td width="100%" class="tdNoMatch" colspan="9">No matching AE found</td>--%>
                            <%--</tr>--%>
                        <%--</c:if>--%>
                    <%--</table>--%>

                <%--</div>--%>
            <%--</td>--%>
            <%--<td width="1%">--%>
                <%--<div id="aewd-${widgetId}-div-actions">--%>
                   <%--<div id="aewd-${widgetId}-div-edit" style="${empty ae2 ? 'display:none;' : ''}">--%>
                       <%--<button id="aewd-${widgetId}-btn-edit" class="aeLinkWidgetEditBtn" type="button">Edit</button>--%>
                   <%--</div>--%>
                    <%--<div id="aewd-${widgetId}-div-find" style="${empty ae2 ? '' :'display:none;'}">--%>
                        <%--<button id="aewd-${widgetId}-btn-find" class="aeLinkWidgetFindBtn" type="button">Find</button>--%>
                    <%--</div>--%>
                <%--</div>--%>

            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr id="aewd-${widgetId}-tbltr2">--%>
            <%--<td id="aewd-${widgetId}-tbl-t2td"  width="100%" colspan="2">--%>
                <%--<div id="aewd-${widgetId}-t2div" style="display:none;">--%>
                <%--</div>--%>
            <%--</td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</div>--%>