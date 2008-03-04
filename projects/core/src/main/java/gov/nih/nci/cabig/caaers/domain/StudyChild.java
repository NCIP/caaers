package gov.nih.nci.cabig.caaers.domain;

/**
 * This interface must be implemented by all those sub fields of Study, which are to be wrapped by
 * LasyList. Infact the StudyChildInstantiateFactory will create the new StudyChild instance, then
 * calls the setStudy() method.
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : Jul 10, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public interface StudyChild {
    public void setStudy(Study study);

    public Study getStudy();
}
