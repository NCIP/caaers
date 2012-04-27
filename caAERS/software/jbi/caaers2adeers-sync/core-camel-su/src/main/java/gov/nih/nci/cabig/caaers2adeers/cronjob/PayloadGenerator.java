package gov.nih.nci.cabig.caaers2adeers.cronjob;

import org.apache.commons.lang.RandomStringUtils;


public class PayloadGenerator {


	public  String getRequest(String system, String entity, String operationName, String mode, String date) {

        return "<payload correlationId=\""+RandomStringUtils.randomAlphanumeric(10)+"\">" +
                "<system>"+system+"</system>" +
                "<request>" +
                "<entity>"+entity+"</entity>" +
                "<operation mode=\""+mode+"\" name=\""+operationName+"\">" +
                "<criteria>" +
                "<criterion  name=\"createdDate\">"+date+"</criterion>" +
                "<criterion name=\"lastUpdatedDate\">"+date+"</criterion>" +
                "</criteria>" +
                "</operation>" +
                "</request>" +
                "</payload>";
    }

	
	public  String getAgentsRequest() {

        return "<payload>" +
                "<system>adeers</system>" +
                "<request>" +
                "<entity>agent</entity>" +
                "<operation mode=\"async\" name=\"getAgentsLOV\">" +
                "<criteria>" +
                "<criterion  name=\"createdDate\">04-10-2001 14:17:38</criterion>" +
                "<criterion name=\"lastUpdatedDate\">04-10-2001 14:17:38</criterion>" +
                "</criteria>" +
                "</operation>" +
                "</request>" +
                "</payload>";
    }

    public  String getStudySearchRequest() {

        return "<payload>" +
                "<system>adeers</system>" +
                "<request>" +
                "<entity>study</entity>" +
                "<operation mode=\"sync\" name=\"searchStudy\">" +
                "<criteria>" +
                "<criterion  name=\"documentTitle\">Adjuvant Chemotherapy</criterion>" +
                "<criterion  name=\"createdDate\">04-10-2001 14:17:38</criterion>" +
                "<criterion name=\"lastUpdatedDate\">04-10-2001 14:17:38</criterion>" +
                "</criteria>" +
                "</operation>" +
                "</request>" +
                "</payload>";
    }


    public  String getStudyDetails(String nciDocumentNumber) {

        return "<payload>" +
                "<system>adeers</system>" +
                "<request>" +
                "<entity>study</entity>" +
                "<operation mode=\"sync\" name=\"getStudyDetails\">" +
                "<criteria>" +
                "<criterion  name=\"nciDocumentNumber\">" +  nciDocumentNumber + "</criterion>" +
                "<criterion  name=\"createdDate\">04-10-2001 14:17:38</criterion>" +
                "<criterion name=\"lastUpdatedDate\">04-10-2001 14:17:38</criterion>" +
                "</criteria>" +
                "</operation>" +
                "</request>" +
                "</payload>";
    }
}
