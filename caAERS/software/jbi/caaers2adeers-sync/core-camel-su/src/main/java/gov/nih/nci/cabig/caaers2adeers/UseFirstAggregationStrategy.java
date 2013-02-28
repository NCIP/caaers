/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class UseFirstAggregationStrategy implements AggregationStrategy {
    // only return the oldest exchange which has the out message
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
    	System.out.println("---------------EXCHANGE--------");
    	if(oldExchange == null){
    		System.out.println("OLD EXCHANGE IS null");
    		System.out.println("RETURNING NEW EXCHANGE SINCE OLD EXCHANGE IS NULL");
    		return newExchange;
        } else {
        	System.out.println("RETURNING OLD EXCHANGE SINCE IT WAS FIRST TO ARRIVE");
           return oldExchange;
        }
    }
}
