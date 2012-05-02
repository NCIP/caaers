package gov.nih.nci.cabig.caaers2adeers.cronjob;

import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLogDao;

import java.util.List;

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
		StringBuilder payloads = new StringBuilder("<payloads>");
		if(entityOperation ==EntityOperation.MERGED_ORGANIZATION){
			List<String> nciCodes = integrationLogDao.findInactiveOrganizationCTEPIds();
			for(String nciCode : nciCodes){
				payloads.append(getPayloadForMergedOrg(entityOperation.getQualifiedName(), entityOperation.getOperationName(), entityOperation.getMode(), nciCode));
			}
		}else{
			String date = integrationLogDao.findLastRequestCompletedDatetime(entityOperation.getQualifiedName());
			payloads.append(getPayloadForDate(entityOperation.getQualifiedName(), entityOperation.getOperationName(), entityOperation.getMode(), date));
		}
		payloads.append("</payloads>");
		return payloads.toString();
    }
	
	public String getPayloadForDate(String entity, String operation, String mode, String date){
		return "<payload>" +
                "<system>adeers</system>" +
                "<request>" +
                "<entity>"+entity+"</entity>" +
                "<operation mode=\""+mode+"\" name=\""+operation+"\">" +
                "<criteria>" +
                "<criterion  name=\"createdDate\">"+date+"</criterion>" +
                "<criterion name=\"lastUpdatedDate\">"+date+"</criterion>" +
                "</criteria>" +
                "</operation>" +
                "</request>" +
                "</payload>";
	}
	
	public String getPayloadForMergedOrg(String entity, String operation, String mode, String ctepId){
		return "<payload>" +
                "<system>adeers</system>" +
                "<request>" +
                "<entity>"+entity+"</entity>" +
                "<operation mode=\""+mode+"\" name=\""+operation+"\">" +
                "<criteria>" +
                "<criterion  name=\"CTEPId\">"+ctepId+"</criterion>" +
                "</criteria>" +
                "</operation>" +
                "</request>" +
                "</payload>";
	}

}
