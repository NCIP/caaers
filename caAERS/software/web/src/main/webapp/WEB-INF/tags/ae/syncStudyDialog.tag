<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<caaers:message var="_syncpopmsg" code="ae.study.sync.message" text="Study is being synchronized with CTEP-ESYS. This popup will automatically close once the data is in sync. Thank you for your patience." />
<c:if test="${command.studyOutOfSync}">
    <div id="divStudySync" style="display:none;">
        <div id="flash-message" class="${empty warningMessageClass ? 'warning' : warningMessageClass}">
            <img src= "<chrome:imageUrl name="../error-yellow.png"/>" />${_syncpopmsg}
        </div>
    </div>
    <script type="text/javascript">

        var studyIdentifierValue = '${command.aeReport.study.fundingSponsorIdentifierValue}';
        var sponsorNCICode = '${command.aeReport.study.primarySponsorCode}';
        Event.observe(window, "load", function(){
            Dialog.info($('divStudySync').innerHTML, {className: "alphacube", width:600, title:'<img height="13px" width="13px" src="<c:url value="/images/indicator.gif" />" alt="activity indicator" width="20px" height="20px"/> '});
            //
            createAE.syncStudyWithAdEERS(studyIdentifierValue, sponsorNCICode ,"UPDATE", function(_resultId) {
                Dialog.closeInfo();
            });

        });

    </script>
</c:if>
