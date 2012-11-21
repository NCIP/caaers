package gov.nih.nci.cabig.caaers.web.admin;


import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.esb.client.impl.CaaersAdeersMessageBroadcastServiceImpl;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.caaers.web.listener.Event;
import gov.nih.nci.cabig.caaers.web.listener.EventMonitor;
import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperties;
import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperty;

import java.util.ArrayList;
import java.util.Collection;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;

public class DiagnosticsControllerTest extends AbstractTestCase {

	public void testFormBackingObjectHttpServletRequest1() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();
		DiagnosticsController controller = new DiagnosticsController();
		Configuration configuration = registerMockFor(Configuration.class);
		EventMonitor eventMonitor = registerMockFor(EventMonitor.class);
		EasyMock.expect(eventMonitor.getAllEvents()).andReturn(new ArrayList<Event>());
		EasyMock.expect(configuration.getProperties()).andReturn(new ConfigurationProperties() {
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getStoredDefaultFor(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getNameFor(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getDescriptionFor(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<ConfigurationProperty<?>> getAll() {
				return new ArrayList<ConfigurationProperty<?>>();
			}
			
			@Override
			public ConfigurationProperty<?> get(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsKey(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		controller.setConfiguration(configuration);
		controller.setEventMonitor(eventMonitor);
		replayMocks();
		DiagnosticsCommand command = (DiagnosticsCommand)controller.formBackingObject(request);
		assertNotNull(command);
		assertFalse(command.isSmtpTestResult());
		assertFalse(command.isServiceMixUp());
		assertNotNull(command.getEvents());
		verifyMocks();
	}
	
	public void testFormBackingObjectHttpServletRequest2() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();
		DiagnosticsController controller = new DiagnosticsController();
		Configuration configuration = registerMockFor(Configuration.class);
		EventMonitor eventMonitor = registerMockFor(EventMonitor.class);
		CaaersJavaMailSender caaersJavaMailSender = registerMockFor(CaaersJavaMailSender.class);
		CaaersAdeersMessageBroadcastServiceImpl messageBroadcastService = registerMockFor(CaaersAdeersMessageBroadcastServiceImpl.class);
		MimeMessage mimeMessage = registerMockFor(MimeMessage.class);
		EasyMock.expect(caaersJavaMailSender.createMimeMessage()).andReturn(mimeMessage);
		mimeMessage.setSubject("Test mail from caAERS Diagnostics");
		mimeMessage.setFrom(new InternetAddress("caaers.app@gmail.com"));
		mimeMessage.setContent(EasyMock.isA(Multipart.class));
		mimeMessage.setRecipient(EasyMock.isA(Message.RecipientType.class), EasyMock.isA(Address.class));
		caaersJavaMailSender.send(EasyMock.isA(MimeMessage.class));
		messageBroadcastService.initialize();
		EasyMock.expect(eventMonitor.getAllEvents()).andReturn(new ArrayList<Event>());
        EasyMock.expect(configuration.get(Configuration.SYSTEM_FROM_EMAIL)).andReturn("biju@ll.com").anyTimes();
		EasyMock.expect(configuration.getProperties()).andReturn(new ConfigurationProperties() {
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getStoredDefaultFor(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getNameFor(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getDescriptionFor(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<ConfigurationProperty<?>> getAll() {
				return new ArrayList<ConfigurationProperty<?>>();
			}
			
			@Override
			public ConfigurationProperty<?> get(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsKey(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		controller.setConfiguration(configuration);
		controller.setEventMonitor(eventMonitor);
		controller.setCaaersJavaMailSender(caaersJavaMailSender);
		controller.setMessageBroadcastService(messageBroadcastService);
		replayMocks();
		DiagnosticsCommand command = (DiagnosticsCommand)controller.formBackingObject(request);
		assertNotNull(command);
		assertTrue(command.isSmtpTestResult());
		assertTrue(command.isServiceMixUp());
		assertNotNull(command.getEvents());
		verifyMocks();
	}

}
