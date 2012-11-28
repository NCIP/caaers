package gov.nih.nci.cabig.caaers.utils.pdf;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 * @date 6/30/11
 */
public class MedwatchUtilsTest extends TestCase {

    public void testBefore() throws Exception {




        String s = "Hello how are you doing. Hope you are doing great. I will see you during next summer holidays!";
        String s1 = MedwatchUtils.before(s, 17);
        String s2 = MedwatchUtils.after(s, 17);

        assertEquals("Hello how are you", s1);
        assertEquals("doing. Hope you are doing great. I will see you during next summer holidays!", s2);


        s = "Hello";
        s1 = MedwatchUtils.before(s, 17);
        s2 = MedwatchUtils.after(s, 17);


        assertEquals("Hello", s1);
        assertEquals("", s2);



        s = "Hello how";
        s1 = MedwatchUtils.before(s, 6);
        s2 = MedwatchUtils.after(s, 6);


        assertEquals("Hello", s1);
        assertEquals("how", s2);


        s = "Hello how";
        s1 = MedwatchUtils.before(s, 80);
        s2 = MedwatchUtils.after(s, 80);


        assertEquals("Hello how", s1);
        assertEquals("", s2);
    }


    public void testEvalXPathOnXML(){
        String xml = "<SAEReportPriorTherapy>\n" +
                "        <id>346</id>\n" +
                "        <PriorTherapy>\n" +
                "            <id>17</id>\n" +
                "            <text>Multi Agent</text>\n" +
                "            <meddraTerm>10042609</meddraTerm>\n" +
                "            <meddraCode>Surgery</meddraCode>\n" +
                "        </PriorTherapy>\n" +
                "        <other>test 2</other>\n" +
                "        <startDate>\n" +
                "            <dayString>03</dayString>\n" +
                "            <monthString>01</monthString>\n" +
                "            <yearString>2009</yearString>\n" +
                "        </startDate>\n" +
                "        <endDate>\n" +
                "            <dayString>04</dayString>\n" +
                "            <monthString>01</monthString>\n" +
                "            <yearString>2009</yearString>\n" +
                "        </endDate>\n" +
                "        <PriorTherapyAgent>\n" +
                "            <ChemoAgent><name>Prozac</name></ChemoAgent>\n" +
                "        </PriorTherapyAgent>\n" +
                "        <PriorTherapyAgent>\n" +
                "            <ChemoAgent><name>Aspirin</name></ChemoAgent>\n" +
                "        </PriorTherapyAgent>\n" +
                        "<PriorTherapyAgent>\n" +
                "            <ChemoAgent><name>Jank</name></ChemoAgent>\n" +
                "        </PriorTherapyAgent>\n" +
                "        <PriorTherapyAgent>\n" +
                "            <ChemoAgent><name>Biju</name></ChemoAgent>\n" +
                "        </PriorTherapyAgent>\n" +
                "    </SAEReportPriorTherapy>";

        String s = MedwatchUtils.evalXPathOnXML(xml, "//PriorTherapy/text");
        assertEquals("Multi Agent", s);
        
    }


    public void testGetOfCounter() throws Exception{
        final MedwatchUtils.Counter c1 = MedwatchUtils.get();
        final MedwatchUtils.Counter c2 = MedwatchUtils.get();
        assertSame(c1, c2);



        Thread t = new Thread(new Runnable() {
            public void run() {
                final MedwatchUtils.Counter c3 = MedwatchUtils.get();
                assertFalse(c1 == c3);
                assertSame(c3, MedwatchUtils.get());
                System.out.println("thread started");
            }
        });
        t.start();

        t.join();

    }
}
