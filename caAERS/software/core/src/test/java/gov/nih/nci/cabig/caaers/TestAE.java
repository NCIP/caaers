package gov.nih.nci.cabig.caaers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: vijendhar
 * Date: 4/16/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestAE {

    public static String getNextDayDate(String dt) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(dt));
            c.add(Calendar.DATE, 1);  // number of days to add
            dt = sdf.format(c.getTime());  // dt is now the new date
           // System.out.println(" get the date " + dt);
        }catch (ParseException ex) {

        }

        return dt;
    }

    public static  void main(String[] args) {


        String ctepCodes = "10002272,10048580,10013442,10016288,10019491,10019515,10024378,10025182,10041633,10043648,10020751,10001723,10007839,10019150,10019483,10010276,10029458,10033557,10053698,10040741,10040752,10003130,10042777,10047281,10007196,10024119,10007612,10007613,10028601,10030114,10020772,10021097,10028606,10065848,10034474,10034578,10034902,10043607,10054692,10016825,10019126,10022095,10028694,10034966,10040829,10037087,10061103,10037767,10048038,10048031";
        String[] ctepArr = ctepCodes.split(",");
        String dt = "2012-01-01"  ;

        for ( int i = 1000; i < 1050; i ++) {
                String nextdt = getNextDayDate(dt);
            String req = "\t\t\t<saer:adverseEvent>\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <verbatim>Testing Adverse Events check "  + i + " </verbatim>\n" +
                    "                  <!--You have a CHOICE of the next 2 items at this level-->\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <adverseEventCtepTerm>\n" +
                    "                     <ctepCode>"+ ctepArr[i-1000] + "</ctepCode>\n" +
                    "                     <!--You have a CHOICE of the next 2 items at this level-->\n" +
                    "                     <!--Optional:-->\n" +
                    "                  </adverseEventCtepTerm>\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <grade>4</grade>\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <hospitalization>YES</hospitalization>\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <expected>1</expected>\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <dateFirstLearned>"+ dt + "T16:26:00</dateFirstLearned>\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <startDate>" + nextdt + "</startDate>\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <eventApproximateTime>\n" +
                    "                     <hour>10</hour>\n" +
                    "                     <minute>10</minute>\n" +
                    "                     <ampm>AM</ampm>\n" +
                    "                  </eventApproximateTime>\n" +
                    "                \n" +
                    "                  <!--Zero or more repetitions:-->\n" +
                    "                  <outcome>\n" +
                    "                     <outComeEnumType>DISABILITY</outComeEnumType>\n" +
                    "                  </outcome>\n" +
                    "                  <!--Optional:-->\n" +
                    "                  <increasedRisk>1</increasedRisk>\n" +
                    "                   <externalId>"  + i  +  "</externalId>\n" +
                    "               </saer:adverseEvent>";
            dt = nextdt;
            System.out.println(req);
        }

    }
}
