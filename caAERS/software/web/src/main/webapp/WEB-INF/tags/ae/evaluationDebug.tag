<%--
 Flow : CaptureAdverseEventController
ae_review_report.jsp uses this to display a list of serious adverse events.
--%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="result" required="true" type="gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO" description="The evaluation result" %>

<c:forEach var="r" items="${result.processingSteps}">
    <div id="dc-eval-debug-${r.key}" style="display: none;">
      <h4>Shows how caAERS came up with the above suggestions for Data Collection : ${r.key}</h4>
      <div class="dcdebug">
          <c:forEach var="m" items="${r.value}" varStatus="idx">
              <c:set var="cssClass">
                  ${idx.index % 2 eq 0 ? 'even' : 'odd'} ${fn:indexOf(m, 'RulesEngine:') gt -1 ? 'rengine' : '' }
                  ${fn:indexOf(m, 'caAERS: Final suggestion') gt -1 ? 'rsuggestion' : '' }
                  ${fn:indexOf(m, 'RuleSet:') gt -1 ? 'rset' : '' }
                  ${fn:indexOf(m,'caAERS: Submitted adverse event' )gt -1 ? 'submittedae' : '' }
                  ${fn:indexOf(m,'caAERS: Manually selected report' )gt -1 ? 'submittedae' : '' }
              </c:set>
              <div class="${cssClass} ">
              ${fn:startsWith(m,"caAERS:")  or fn:startsWith(m,"RulesEngine:" ) ? '' : "&nbsp;&nbsp;"}
              <c:out value="${m}" escapeXml="true" />
              </div>
          </c:forEach>
      </div>

    </div>
</c:forEach>