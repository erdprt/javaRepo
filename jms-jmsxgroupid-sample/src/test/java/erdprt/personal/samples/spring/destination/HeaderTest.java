package erdprt.personal.samples.spring.destination;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import erdprt.personal.samples.spring.destination.senders.DestinationSender;
import erdprt.personal.samples.spring.threads.RunnableDestinationSender;

public class HeaderTest {
	
	private static final Logger logger	=	LoggerFactory.getLogger(HeaderTest.class);
	
	private static final Integer COUNT	=	60;

	private ConfigurableApplicationContext context;
	
	
	@BeforeMethod
	public void setUp() {
		this.context	=	new ClassPathXmlApplicationContext("spring-config.xml");		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		
		Thread.sleep(2000);
		logger.info("context=" + this.context);
		if (this.context!=null) {
			this.context.close();
		}
	}

	/**
	 * No JMSXGroupID header: the messages are distributed among listeners
	*/
	@Test
	public void testWithoutHeader() {
		
		Map<String, String> headers	=	new HashMap<>();
		proceed(headers);
	}
	
	/**
	 * send message with JMSXGroupID header, the same for all messages:
	 * only one listener receives all the messages
	 */
	@Test
	public void testWithOneHeaderValue() {
		
		Map<String, String> headers	=	new HashMap<>();
		headers.put("JMSXGroupID", "JMSXGroupID_1");	
		
		proceed(headers);
	}

	@Test
	public void testWithMultipleHeaderValue() {
		
		for (Integer index=0;index<COUNT;index++) {
			DestinationSender sender	=	this.context.getBean("destinationSender", DestinationSender.class);
			String content					=	"content message " + index;
			
			Map<String, String> headers	=	new HashMap<>();
			headers.put("JMSXGroupID", "JMSXGroupID_" + (index%3));	
			
			sender.simpleSend(content, headers);
		}
	}

	@Test
	public void testMultiThreadedWithMultipleHeaderValue() {
		
		for (Integer index=0;index<COUNT;index++) {
			DestinationSender sender	=	this.context.getBean("destinationSender", DestinationSender.class);
			
			Runnable runnable	=	new RunnableDestinationSender(sender, index);
			new Thread(runnable).start();
		}
	}
	
	public void proceed(Map<String, String> headers) {

		String status					=	"";
		
		for (Integer index=0;index<COUNT;index++) {
			DestinationSender sender	=	this.context.getBean("destinationSender", DestinationSender.class);
			String content					=	"content message " + index/2;
			if (index%2==0) {
				status					=	"START";
			} else {
				status					=	"END";
			}
			content	+=	":" + status;
			sender.simpleSend(content, headers);
		}
	}
	
}
