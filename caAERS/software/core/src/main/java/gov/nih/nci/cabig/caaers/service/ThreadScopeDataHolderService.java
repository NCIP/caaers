package gov.nih.nci.cabig.caaers.service;

/**
 * Will hold the few data elements within thread scope.
 *   requirements - disable study sync during SAE - evaluate and initiate.
 */
public class ThreadScopeDataHolderService {

    private static ThreadScopeDataHolderService INSTANCE;
    private static final ThreadLocal<Boolean> SYNC_STUDY_INDICATOR = new ThreadLocal<Boolean>();

    private ThreadScopeDataHolderService() {
    }

    public static ThreadScopeDataHolderService getInstance() {
        if(INSTANCE == null) INSTANCE = new ThreadScopeDataHolderService();
        return INSTANCE;
    }
    public Boolean isStudySyncEnabled() {
        Boolean shouldSyncStudy = SYNC_STUDY_INDICATOR.get();
        return shouldSyncStudy == null || shouldSyncStudy;
    }

    public void enableStudySync() {
        SYNC_STUDY_INDICATOR.set(true);
    }

    public void disableStudySync() {
        SYNC_STUDY_INDICATOR.set(true);
    }
}
