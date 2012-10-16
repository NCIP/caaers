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
            &nbsp;
        </td>
    </tr>
</c:if>

<c:set var="widgetId" value="${ae1.id}" />

<c:if test="${not fillerRow}">
    <c:if test="${not empty ae1}">
        <tr id="aewd-${widgetId}-tr-iae" class="tr-ae1">
            <td width="${requestScope.widthSource}" class="tr-ae1-c1">${ae1.source}</td>
            <td width="${requestScope.widthId}" class="tr-ae1-c2">${empty ae1.externalID ? '-' : ae1.externalID }</td>
            <td width="${requestScope.widthTerm}" class="tr-ae1-c3"> ${ae1.term.displayName}</td>
            <td width="${requestScope.widthGrade}" class="tr-ae1-c4">${ae1.grade}</td>
            <td width="${requestScope.widthStartDate}" class="tr-ae1-c5">${ae1.startDate}</td>
            <td width="${requestScope.widthEndDate}" class="tr-ae1-c6">${ae1.endDate}</td>
            <td width="${requestScope.widthVerbatim}" class="tr-ae1-c7">${ae1.verbatim}</td>
            <td width="${requestScope.widthWhySerious}" class="tr-ae1-c8">${ae1.whySerious}</td>
            <td width="${requestScope.widthAttribution}" class="tr-ae1-c9">${ae1.attribution}</td>
            <td width="${requestScope.widthActions}" class="actionBtns tr-ae1-c10">
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
        <tr id="aewd-${widgetId}-tr-eae" style="${empty ae2 ? 'display:none;' : ''}" class="tr-ae2">
            <td width="${requestScope.widthSource}" class="eae tr-ae2-c1">${ae2.source}</td>
            <td width="${requestScope.widthId}"  class="eae tr-ae2-c2">${ae2.externalID}</td>
            <td width="${requestScope.widthTerm}"  class="eae tr-ae2-c3">${ae2.term.displayName}</td>
            <td width="${requestScope.widthGrade}"  class="eae tr-ae2-c4">${ae2.grade}</td>
            <td width="${requestScope.widthStartDate}"  class="eae tr-ae2-c5">${ae2.startDate}</td>
            <td width="${requestScope.widthEndDate}"  class="eae tr-ae2-c6">${ae2.endDate}</td>
            <td width="${requestScope.widthVerbatim}"  class="eae tr-ae2-c7">${ae2.verbatim}</td>
            <td width="${requestScope.widthWhySerious}"  class="eae tr-ae2-c8">${ae2.whySerious}</td>
            <td width="${requestScope.widthAttribution}"  class="eae tr-ae2-c9">${ae2.attribution}</td>
            <td width="${requestScope.widthActions}" class="eae tr-ae2-c10">
              &nbsp;
            </td>
        </tr>
    <tr id="aewd-${widgetId}-tr-nomatch" style="${empty ae2 ? '' : 'display:none;'}" class="tr-nomatch">
        <td width="100%" class="tdNoMatch" colspan="10">No matching AE found</td>
    </tr>

    <tr id="aewd-${widgetId}-tr-pick" class="tr-picker">
        <td id="aewd-${widgetId}-tbl-t2td"  width="94%" colspan="9" class="tr-picker-c1">
            <div id="aewd-${widgetId}-t2div" style="display:none;" class="yui-skin-sam picker-div">
            </div>
        </td>
        <td width="${requestScope.widthActions}" class="actionBtns tr-picker-c10">
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