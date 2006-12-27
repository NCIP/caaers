/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.nih.nci.ctms.demo.comp;

import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;

import javax.jbi.component.ComponentContext;
import javax.jbi.management.DeploymentException;
import javax.jbi.messaging.DeliveryChannel;
import javax.jbi.messaging.ExchangeStatus;
import javax.jbi.messaging.InOut;
import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.MessageExchangeFactory;
import javax.jbi.messaging.MessagingException;
import javax.jbi.messaging.NormalizedMessage;
import javax.jbi.messaging.MessageExchange.Role;
import javax.jbi.servicedesc.ServiceEndpoint;
import javax.xml.transform.Source;

import org.apache.servicemix.common.BaseLifeCycle;
import org.apache.servicemix.common.Endpoint;
import org.apache.servicemix.common.ExchangeProcessor;
import org.globus.gsi.GlobusCredential;

/**
 * @org.apache.xbean.XBean element="endpoint"
 */
public class GridServiceEndpoint extends Endpoint implements ExchangeProcessor {

	private ServiceSecurityClient gridServiceClient;

	private GridServiceProcessor gridServiceProcessor;

	private String gridProxyProperty;

	private ServiceEndpoint activated;

	private DeliveryChannel channel;

	private MessageExchangeFactory exchangeFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.servicemix.common.Endpoint#getRole()
	 */
	public Role getRole() {
		return Role.PROVIDER;
	}

	public void activate() throws Exception {
		logger = this.serviceUnit.getComponent().getLogger();
		ComponentContext ctx = getServiceUnit().getComponent()
				.getComponentContext();
		channel = ctx.getDeliveryChannel();
		exchangeFactory = channel.createExchangeFactory();
		activated = ctx.activateEndpoint(service, endpoint);
		start();
	}

	public void deactivate() throws Exception {
		stop();
		ServiceEndpoint ep = activated;
		activated = null;
		ComponentContext ctx = getServiceUnit().getComponent()
				.getComponentContext();
		ctx.deactivateEndpoint(ep);
	}

	public ExchangeProcessor getProcessor() {
		return this;
	}

	public void validate() throws DeploymentException {
	}

	protected void send(MessageExchange me) throws MessagingException {
		if (me.getRole() == MessageExchange.Role.CONSUMER
				&& me.getStatus() == ExchangeStatus.ACTIVE) {
			BaseLifeCycle lf = (BaseLifeCycle) getServiceUnit().getComponent()
					.getLifeCycle();
			lf.sendConsumerExchange(me, (Endpoint) this);
		} else {
			channel.send(me);
		}
	}

	protected void done(MessageExchange me) throws MessagingException {
		me.setStatus(ExchangeStatus.DONE);
		send(me);
	}

	protected void fail(MessageExchange me, Exception error)
			throws MessagingException {
		me.setError(error);
		send(me);
	}

	public void start() throws Exception {
	}

	public void stop() {
	}

	public void process(MessageExchange exchange) throws Exception {

		if (exchange.getRole() == MessageExchange.Role.PROVIDER) {
			if (exchange instanceof InOut == false) {
				throw new UnsupportedOperationException("Unsupported MEP: "
						+ exchange.getPattern());
			}
			if (exchange.getStatus() == ExchangeStatus.ACTIVE) {
				if (exchange.getMessage("in") != null) {
					NormalizedMessage in = exchange.getMessage("in");

					GlobusCredential proxy = null;
					try {
						proxy = (GlobusCredential) in
								.getProperty(getGridProxyProperty());
					} catch (ClassCastException ex) {
						throw new RuntimeException("proxy must be of type "
								+ GlobusCredential.class.getName()
								+ ", got "
								+ in.getProperty(getGridProxyProperty())
										.getClass().getName());
					} catch (Exception ex) {
						throw new RuntimeException("Error retrieving "
								+ getGridProxyProperty() + ": "
								+ ex.getMessage(), ex);
					}
					
					ServiceSecurityClient client = getGridServiceClient();
					if(proxy != null){
						client.setProxy(proxy);
					}
					getGridServiceProcessor().process(exchange, this.channel, client);
				}
			}
		}else if (exchange.getRole() == MessageExchange.Role.CONSUMER) {


			if (exchange.getStatus() == ExchangeStatus.DONE) {
				return;
			} else if (exchange.getStatus() == ExchangeStatus.ERROR) {
				return;
			} else {
				if (exchange.getMessage("out") != null) {
					exchange.setStatus(ExchangeStatus.DONE);
					channel.send(exchange);
				} else if (exchange.getFault() != null) {
					exchange.setStatus(ExchangeStatus.DONE);
					channel.send(exchange);
				} else {
					throw new IllegalStateException(
							"Consumer exchange is ACTIVE, but no out or fault is provided");
				}
			}
		} else {
			throw new IllegalStateException("Unkown role: "
					+ exchange.getRole());
		}

	}

	private Source getSource(String outMsg) {
		// TODO Auto-generated method stub
		return null;
	}

	public ServiceSecurityClient getGridServiceClient() {
		return gridServiceClient;
	}

	public void setGridServiceClient(ServiceSecurityClient gridServiceClient) {
		this.gridServiceClient = gridServiceClient;
	}

	public void setGridServiceProcessor(GridServiceProcessor processor) {
		this.gridServiceProcessor = processor;
	}

	public String getGridProxyProperty() {
		return gridProxyProperty;
	}

	public void setGridProxyProperty(String gridProxyProperty) {
		this.gridProxyProperty = gridProxyProperty;
	}
	
	public GridServiceProcessor getGridServiceProcessor(){
		return this.gridServiceProcessor;
	}
	

}
