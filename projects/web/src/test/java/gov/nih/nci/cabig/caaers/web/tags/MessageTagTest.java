package gov.nih.nci.cabig.caaers.web.tags;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import org.easymock.classextension.EasyMock;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
/**
 * 
 * @author Biju Joseph
 *
 */
public class MessageTagTest extends AbstractTestCase {
	MessageTag tag;
	MessageSource messageSource;
	
	protected void setUp() throws Exception {
		super.setUp();
		messageSource = registerMockFor(MessageSource.class);
		
		tag = new MessageTag(){
			@Override
			protected MessageSource getMessageSource() {
				return  messageSource;
			}
			
		};
		
	}

	public void testResolveMessage() throws Exception {
		EasyMock.expect(messageSource.getMessage("joel", null, null)).andReturn("hello");
		replayMocks();
		tag.setCode("joel");
		String msg = tag.resolveMessage();
		verifyMocks();
		assertEquals("hello", msg);
	}
	public void testResolveMessage_LookingForArrayProperty() throws Exception {
		EasyMock.expect(messageSource.getMessage("joel[5].abc", null, null)).andThrow(new NoSuchMessageException("x"));
		EasyMock.expect(messageSource.getMessage("joel.abc", null,null, null)).andReturn("hello");
		replayMocks();
		tag.setCode("joel[5].abc");
		String msg = tag.resolveMessage();
		verifyMocks();
		assertEquals("hello", msg);
	}
	
	
}
