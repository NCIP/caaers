package gov.nih.nci.cabig.caaers.tools.spring;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.io.File;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Biju
 * Date: 5/10/13
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class TimeBoundTaskExecutorTest extends AbstractTestCase {
    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testExecute() throws Exception {
        final HashMap<String, Boolean> result = new HashMap<String , Boolean>();

        Runnable r = new Runnable() {
            public void run(){
                System.out.println("Starting job...");
                try{
                    Thread.currentThread().sleep(2000);
                }catch (InterruptedException e) {
                    System.out.println("I am interrupted")   ;
                    result.put("interrupted", true);
                    System.out.println(result) ;
                }
                System.out.println("finished") ;
            }
        };

        //long running job, will get interrupted
        System.out.println(result);
        result.put("interrupted", false) ;
        TimeBoundTaskExecutor executor = new TimeBoundTaskExecutor();
        executor.setTimeout(500);
        executor.execute(r);
        Thread.sleep(300);
        System.out.println(" caller " + result.toString());
        assertTrue(result.get("interrupted"));

        //job will finish before future checks that status
        result.put("interrupted", false) ;
        executor.setTimeout(15000000);   //crazy long timeout
        executor.execute(r);
        Thread.sleep(3000);
        assertFalse(result.get("interrupted"));
    }
}
