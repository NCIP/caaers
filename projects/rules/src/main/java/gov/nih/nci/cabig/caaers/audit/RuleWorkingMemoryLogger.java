package gov.nih.nci.cabig.caaers.audit;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.List;

import org.drools.FactHandle;
import org.drools.WorkingMemory;

import org.drools.WorkingMemory;

import org.drools.audit.event.ActivationLogEvent;

import org.drools.audit.event.ILogEventFilter;

import org.drools.audit.event.LogEvent;

import org.drools.audit.event.ObjectLogEvent;

import org.drools.common.InternalFactHandle;

import org.drools.event.ActivationCancelledEvent;
import org.drools.event.ActivationCreatedEvent;
import org.drools.event.AfterActivationFiredEvent;
import org.drools.event.BeforeActivationFiredEvent;

import org.drools.event.ActivationCreatedEvent;

import org.drools.event.AfterActivationFiredEvent;

import org.drools.event.AgendaEventListener;

import org.drools.event.BeforeActivationFiredEvent;

import org.drools.event.ObjectAssertedEvent;

import org.drools.event.ObjectModifiedEvent;

import org.drools.event.ObjectRetractedEvent;

import org.drools.event.WorkingMemoryEventListener;

import org.drools.rule.Declaration;
import org.drools.rule.Rule;

import org.drools.spi.Activation;

import org.drools.spi.Tuple;

public class RuleWorkingMemoryLogger

implements

WorkingMemoryEventListener,

AgendaEventListener {

    private RuleLogger logger;

    private final List filters = new ArrayList();

    private WorkingMemory workingMemory;

    private DecisionContext dc;

    private String bindUri;

    /**
     * 
     * Creates a new working memory logger for the given working memory.
     * 
     * 
     * 
     * @param workingMemory
     * 
     */

    public RuleWorkingMemoryLogger(final WorkingMemory workingMemory, String bindUri)
                    throws Exception {

        this.workingMemory = workingMemory;

        workingMemory.addEventListener((WorkingMemoryEventListener) this);

        workingMemory.addEventListener((AgendaEventListener) this);

        dc = new DecisionContext();

        this.bindUri = bindUri;

        try {
            logger = RuleLogger.getInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }

    }

    /**
     * 
     * This method is invoked every time a new log event is created.
     * 
     * Subclasses should implement this method and store the event,
     * 
     * like for example log to a file or database.
     * 
     * 
     * 
     * @param logEvent
     * 
     */

    public void logEventCreated(LogEvent logEvent) {
        ;
        ;
    }

    /**
     * 
     * This method is invoked every time a new log event is created.
     * 
     * It filters out unwanted events.
     * 
     * 
     * 
     * @param logEvent
     * 
     */

    private void filterLogEvent(final LogEvent logEvent) {
        ;
        ;

    }

    /**
     * 
     * Adds the given filter to the list of filters for this event log.
     * 
     * A log event must be accepted by all the filters to be entered in
     * 
     * the event log.
     * 
     * 
     * 
     * @param filter
     *                The filter that should be added.
     * 
     */

    public void addFilter(final ILogEventFilter filter) {

        ;
        ;

    }

    /**
     * 
     * Removes the given filter from the list of filters for this event log.
     * 
     * If the given filter was not a filter of this event log, nothing
     * 
     * happens.
     * 
     * 
     * 
     * @param filter
     *                The filter that should be removed.
     * 
     */

    public void removeFilter(final ILogEventFilter filter) {

        ;
        ;

    }

    /**
     * 
     * Clears all filters of this event log.
     * 
     */

    public void clearFilters() {

        ;
        ;

    }

    /**
     * 
     * @see org.drools.event.WorkingMemoryEventListener
     * 
     */

    public void objectAsserted(final ObjectAssertedEvent event) {

        // filterLogEvent(new ObjectLogEvent(LogEvent.OBJECT_ASSERTED, ((InternalFactHandle)
        // event.getFactHandle()).getId(),event.getObject().toString()));

        // logger.logMessage("Object is asserted:"+event.toString());

    }

    /**
     * 
     * @see org.drools.event.WorkingMemoryEventListener
     * 
     */

    public void objectModified(final ObjectModifiedEvent event) {

        // filterLogEvent(new ObjectLogEvent(LogEvent.OBJECT_MODIFIED,((InternalFactHandle)
        // event.getFactHandle()).getId(),event.getObject().toString()));
        // logger.logMessage("Object is modified");

    }

    /**
     * 
     * @see org.drools.event.WorkingMemoryEventListener
     * 
     */

    public void objectRetracted(final ObjectRetractedEvent event) {

        // filterLogEvent(new ObjectLogEvent(LogEvent.OBJECT_RETRACTED, ((InternalFactHandle)
        // event.getFactHandle()).getId(),event.getOldObject().toString()));
        // logger.logMessage("Object is retracted:"+event.toString());

    }

    /**
     * 
     * @see org.drools.event.AgendaEventListener
     * 
     */

    public void activationCreated(final ActivationCreatedEvent event) {

        // filterLogEvent(new
        // ActivationLogEvent(LogEvent.ACTIVATION_CREATED,getActivationId(event.getActivation()),event.getActivation().getRule().getName(),extractDeclarations(event.getActivation())));
        // logger.logMessage("Object is created");
    }

    /**
     * 
     * @see org.drools.event.AgendaEventListener
     * 
     */

    public void activationCancelled(final ActivationCancelledEvent event) {

        // filterLogEvent(new
        // ActivationLogEvent(LogEvent.ACTIVATION_CANCELLED,getActivationId(event.getActivation()),event.getActivation().getRule().getName(),extractDeclarations(event.getActivation())));
        // logger.logMessage("activation is cancelled");
    }

    /**
     * 
     * @see org.drools.event.AgendaEventListener
     * 
     */

    public void beforeActivationFired(final BeforeActivationFiredEvent event) {

        // filterLogEvent(new
        // ActivationLogEvent(LogEvent.BEFORE_ACTIVATION_FIRE,getActivationId(event.getActivation()),event.getActivation().getRule().getName(),extractDeclarations(event.getActivation())));
        // logger.logMessage("Before activation is fired");
    }

    /**
     * 
     * @see org.drools.event.AgendaEventListener
     * 
     */

    public void afterActivationFired(final AfterActivationFiredEvent event) {

        // filterLogEvent(new
        // ActivationLogEvent(LogEvent.AFTER_ACTIVATION_FIRE,getActivationId(event.getActivation()),event.getActivation().getRule().getName(),extractDeclarations(event.getActivation())));
        // logger.logMessage("after tthe action fired");
    }

    /**
     * 
     * Creates a string representation of the declarations of an activation.
     * 
     * This is a list of name-value-pairs for each of the declarations in the
     * 
     * tuple of the activation. The name is the identifier (=name) of the
     * 
     * declaration, and the value is a toString of the value of the
     * 
     * parameter, followed by the id of the fact between parentheses.
     * 
     * 
     * 
     * @param activation
     *                The activation from which the declarations should be extracted
     * 
     * @return A String represetation of the declarations of the activation.
     * 
     */

    private String extractDeclarations(final Activation activation) {

        final StringBuffer result = new StringBuffer();

        final Tuple tuple = activation.getTuple();

        final Declaration[] declarations = activation.getRule().getDeclarations();

        for (int i = 0, length = declarations.length; i < length; i++) {

            final Declaration declaration = declarations[i];

            final FactHandle handle = tuple.get(declaration);

            if (handle instanceof InternalFactHandle) {

                final InternalFactHandle handleImpl = (InternalFactHandle) handle;

                if (handleImpl.getId() == -1) {

                    // This handle is now invalid, probably due to an fact
                    // retraction

                    continue;

                }

                final Object value = declaration.getValue(this.workingMemory.getObject(handle));

                result.append(declaration.getIdentifier());

                result.append("=");

                if (value == null) {

                    // this should never occur

                    result.append("null");

                } else {

                    result.append(value);

                    result.append("(");

                    result.append(handleImpl.getId());

                    result.append(")");

                }

            }

            if (i < declarations.length - 1) {

                result.append("; ");

            }

        }

        return result.toString();

    }

    /**
     * 
     * Returns a String that can be used as unique identifier for an
     * 
     * activation. Since the activationId is the same for all assertions
     * 
     * that are created during a single assert, modify or retract, the
     * 
     * key of the tuple of the activation is added too (which is a set
     * 
     * of fact handle ids).
     * 
     * 
     * 
     * @param activation
     *                The activation for which a unique id should be generated
     * 
     * @return A unique id for the activation
     * 
     */

    private static String getActivationId(final Activation activation) {

        final StringBuffer result = new StringBuffer(activation.getRule().getName());

        result.append(" [");

        final Tuple tuple = activation.getTuple();

        final FactHandle[] handles = tuple.getFactHandles();

        for (int i = 0; i < handles.length; i++) {

            result.append(((InternalFactHandle) handles[i]).getId());

            if (i < handles.length - 1) {

                result.append(", ");

            }

        }

        return result.append("]").toString();

    }

    public void activationCancelled(ActivationCancelledEvent arg0, WorkingMemory arg1) {
        // TODO Auto-generated method stub
        // logger.logMessage("Activation cancelled with working memory");
    }

    public void activationCreated(ActivationCreatedEvent arg0, WorkingMemory arg1) {
        // TODO Auto-generated method stub
        // logger.logMessage("Activation created with working memory");
    }

    public void afterActivationFired(AfterActivationFiredEvent event, WorkingMemory arg1) {
        // TODO Auto-generated method stub
        // logger.logMessage("After Activation fired with working memory");
        dc.getFiredRuleNames().add(event.getActivation().getRule().getName());
    }

    public void beforeActivationFired(BeforeActivationFiredEvent arg0, WorkingMemory arg1) {
        // TODO Auto-generated method stub
        // logger.logMessage("Before Activation fired with working memory");
    }

    public void logExecutionSummary(List<Object> assertedObjects) throws Exception {
        FiredRuleSetInfoBuilder fsb = new FiredRuleSetInfoBuilder();
        FiredRuleSetInfo firedRuleSetInfo = fsb.build(bindUri);
        dc.setFiredRuleSetInfo(firedRuleSetInfo);
        dc.setAssertedObjects(assertedObjects);
        dc.buildExecutionSummary();
        LogObjectFormatter lof = new LogObjectFormatter();
        String logRecord = lof.toTextTable(dc);
        logger.logMessage(logRecord);

    }

}
