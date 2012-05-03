package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

/**
 * @author Biju Joseph
 */
public class StudyDeviceChildInstantiateFactory<T extends StudyDeviceChild> extends
        InstantiateFactory<T> {

    /** The study agent. */
    private StudyDevice StudyDevice;

    /**
     * Instantiates a new study agent child instantiate factory.
     *
     * @param StudyDevice the study agent
     * @param classToInit the class to init
     */
    public StudyDeviceChildInstantiateFactory(StudyDevice StudyDevice, Class<T> classToInit) {
        super(classToInit);
        this.StudyDevice = StudyDevice;
    }

    /* (non-Javadoc)
     * @see org.apache.commons.collections15.functors.InstantiateFactory#create()
     */
    @Override
    public T create() {
        T child = super.create();
        child.setStudyDevice(StudyDevice);
        return child;
    }
}
