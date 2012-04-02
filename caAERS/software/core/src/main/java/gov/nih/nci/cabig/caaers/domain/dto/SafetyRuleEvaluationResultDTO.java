package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.NotificationStatus;

import java.util.List;

/**
 * @author Biju Joseph
 * @date 3/29/12
 */
public class SafetyRuleEvaluationResultDTO {
    
    private List<String> rulesMatched;
    
    private String notificationName;

    private NotificationStatus notificationStatus;

    public List<String> getRulesMatched() {
        return rulesMatched;
    }

    public void setRulesMatched(List<String> rulesMatched) {
        this.rulesMatched = rulesMatched;
    }

    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString() {
        return "SafetyRuleEvaluationResultDTO{" +
                "rulesMatched=" + String.valueOf(rulesMatched) +
                ", notificationName='" + notificationName + '\'' +
                ", notificationStatus=" + notificationStatus +
                '}';
    }
}
