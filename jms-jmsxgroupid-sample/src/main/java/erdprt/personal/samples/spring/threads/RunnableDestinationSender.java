package erdprt.personal.samples.spring.threads;

import java.util.HashMap;
import java.util.Map;

import erdprt.personal.samples.spring.destination.senders.DestinationSender;

public class RunnableDestinationSender implements Runnable {

	private DestinationSender destinationSender;
	private Integer index;
	
	public RunnableDestinationSender(DestinationSender destinationSender, Integer index) {
		this.destinationSender 	=	destinationSender;
		this.index				=	index;
	}

	@Override
	public void run() {

		String content					=	"content message " + index;
		
		Map<String, String> headers	=	new HashMap<>();
		headers.put("JMSXGroupID", "JMSXGroupID_" + (index%3));	
		
		this.destinationSender.simpleSend(content, headers);

	}
	
	

}
