package gov.nih.nci.cabig.caaers2adeers.cronjob;

import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLogDao;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

public class PayloadGenerator implements Processor {

    @Autowired
    IntegrationLogDao integrationLogDao;

    public void process(Exchange exchange) throws Exception {
        String message = exchange.getIn().getBody(String.class);
        for(EntityOperation op : EntityOperation.values()){
            String input = "<m>" + op.name() +  "</m>";
            if(input.equals(message)) {
                exchange.getIn().setBody(getRequest(op),String.class);
            }
        }
    }

	public  String getRequest(EntityOperation entityOperation) {

        String date = integrationLogDao.findLastRequestCompletedDatetime(entityOperation.getQualifiedName());

        return "<payload>" +
                "<system>adeers</system>" +
                "<request>" +
                "<entity>"+entityOperation.getQualifiedName()+"</entity>" +
                "<operation mode=\""+entityOperation.getMode()+"\" name=\""+entityOperation.getOperationName()+"\">" +
                "<criteria>" +
                "<criterion  name=\"createdDate\">"+date+"</criterion>" +
                "<criterion name=\"lastUpdatedDate\">"+date+"</criterion>" +
                "</criteria>" +
                "</operation>" +
                "</request>" +
                "</payload>";
    }


}
