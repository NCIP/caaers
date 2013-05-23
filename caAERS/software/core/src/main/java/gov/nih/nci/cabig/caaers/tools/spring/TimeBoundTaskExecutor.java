package gov.nih.nci.cabig.caaers.tools.spring;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: Biju
 * Date: 5/9/13
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimeBoundTaskExecutor extends SimpleAsyncTaskExecutor {

    private int timeout = 180000; //time in milli seconds

    @Override
    public void execute(Runnable task){
        Future future = super.submit(task);

        try {
            future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("cancelling");
            future.cancel(true);
        } catch(Exception e){
            throw new CaaersSystemException("Error while getting status of the Event Task (FutureTask):  ", e);
        }
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
