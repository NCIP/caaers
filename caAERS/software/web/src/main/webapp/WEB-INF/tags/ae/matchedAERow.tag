<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="ae1" required="false" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
<%@attribute name="ae2" required="false" type="gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO" %>
<%@attribute name="fillerRow" required="false" type="java.lang.Boolean" %>

<c:if test="<c:out value="${fillerRow}" escapeXml="true"/>">
    <tr>
        <td colspan="9" class="fillerRow">
            <hr class="hrfiller"  />
        </td>
    </tr>
</c:if>

<c:set var="widgetId" value="${ae1.id}" />

<c:if test="${not fillerRow}">
    <c:if test="${not empty ae1}">
        <tr id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-tr-iae" class="tr-ae1">
            <td width="${requestScope.widthSource}" class="tr-ae1-c1">${ae1.source}</td>
            <td width="${requestScope.widthTerm}" class="tr-ae1-c2"> ${caaers:escapeJS(ae1.term.displayName)}</td>
            <td width="${requestScope.widthGrade}" class="tr-ae1-c3">${caaers:escapeJS(ae1.grade)}</td>
            <td width="${requestScope.widthStartDate}" class="tr-ae1-c4">${ae1.startDate}</td>
            <td width="${requestScope.widthEndDate}" class="tr-ae1-c5">${ae1.endDate}</td>
            <td width="${requestScope.widthVerbatim}" class="tr-ae1-c6">${caaers:escapeJS(ae1.verbatim)}</td>
            <td width="${requestScope.widthWhySerious}" class="tr-ae1-c7">${caaers:escapeJS(ae1.whySerious)}</td>
            <td width="${requestScope.widthAttribution}" class="tr-ae1-c8">${caaers:escapeJS(ae1.attribution)}</td>
            <td width="${requestScope.widthActions}" class="actionBtns tr-ae1-c9">
                <div id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-div-edit" style="${empty ae2 ? 'display:none;' : ''}" class="wgtBtnDiv">
                    <tags:button  id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-btn-edit" value="Edit" color="blue" size="small" cssClass="wgtBtn wgtBtnEdit" type="button" icon="edit"
                                  onclick="wt<c:out value="${widgetId}" escapeXml="true"/>.onEditBtnClick(this);" />
                </div>
                <div id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-div-cancel" style="display:none;" class="wgtBtnDiv">
                    <tags:button  id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-btn-cancel" value="Save" color="blue" size="small" cssClass="wgtBtn wgtBtnCancel" type="button" icon="save"
                                  onclick="wt<c:out value="${widgetId}" escapeXml="true"/>.onCancelBtnClick(this);" />
                </div>
                <div id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-div-find" style="${empty ae2 ? '' :'display:none;'}"  class="wgtBtnDiv" >
                    <tags:button  id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-btn-find" value="Find" color="orange" cssClass="wgtBtn wgtBtnFind" type="button"  size="small" icon="search"
                                  onclick="wt<c:out value="${widgetId}" escapeXml="true"/>.onFindBtnClick(this);" />
                </div>
                <div id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-div-link" style="display:none;"  class="wgtBtnDiv" >
                    <tags:button  id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-btn-link" value="Link" color="green" cssClass="wgtBtn wgtBtnLink" type="button"  size="small" icon="add"
                                  onclick="wt<c:out value="${widgetId}" escapeXml="true"/>.onLinkBtnClick(this)" />
                </div>
                <div id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-div-unlink" style="display:none;"  class="wgtBtnDiv" >
                    <tags:button  id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-btn-unlink" value="Unlink" color="red" cssClass="wgtBtn wgtBtnUnlink" type="button"  size="small" icon="delete"
                                  onclick="wt<c:out value="${widgetId}" escapeXml="true"/>.onUnlinkBtnClick(this)" />
                </div>

                <a name="a<c:out value="${widgetId}" escapeXml="true"/>" />
            </td>
        </tr>
    </c:if>
        <tr id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-tr-eae" style="${empty ae2 ? 'display:none;' : ''}" class="tr-ae2">
            <td width="${requestScope.widthSource}" class="eae tr-ae2-c1">${ae2.source}</td>
            <td width="${requestScope.widthTerm}"  class="eae tr-ae2-c2">${caaers:escapeJS(ae2.term.displayName)}</td>
            <td width="${requestScope.widthGrade}"  class="eae tr-ae2-c3">${caaers:escapeJS(ae2.grade)}</td>
            <td width="${requestScope.widthStartDate}"  class="eae tr-ae2-c4">${ae2.startDate}</td>
            <td width="${requestScope.widthEndDate}"  class="eae tr-ae2-c5">${ae2.endDate}</td>
            <td width="${requestScope.widthVerbatim}"  class="eae tr-ae2-c6">${caaers:escapeJS(ae2.verbatim)}</td>
            <td width="${requestScope.widthWhySerious}"  class="eae tr-ae2-c7">${caaers:escapeJS(ae2.whySerious)}</td>
            <td width="${requestScope.widthAttribution}"  class="eae tr-ae2-c8">${caaers:escapeJS(ae2.attribution)}</td>
            <td width="${requestScope.widthActions}" class="eae tr-ae2-c9">
              &nbsp;
            </td>
        </tr>
    <tr id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-tr-nomatch" style="${empty ae2 ? '' : 'display:none;'}" class="tr-nomatch">
        <td width="100%" class="tdNoMatch" colspan="9">No matching AE found</td>
    </tr>

    <tr id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-tr-pick" class="tr-picker">
        <td id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-tbl-t2td"  width="94%" colspan="9" class="tr-picker-c1">
            <div id="aewd-<c:out value="${widgetId}" escapeXml="true"/>-t2div" style="display:none;" class="yui-skin-sam picker-div">
            </div>
        </td>
    </tr>
    <script type="text/javascript">
       var wt<c:out value="${widgetId}" escapeXml="true"/> = new AE.aeWidget(<c:out value="${widgetId}" escapeXml="true"/>, ${ae1.id}, ${empty ae2 ? -1 : ae2.id});
    </script>
</c:if>