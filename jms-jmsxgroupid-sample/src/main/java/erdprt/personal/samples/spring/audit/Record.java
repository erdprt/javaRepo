package erdprt.personal.samples.spring.audit;

import java.util.Date;

public class Record {

	private String content;
	private String header;
	private Date date;
	private String threadId;
	
	public Record(String content, String header, Date date, String threadId) {
		super();
		this.content 	= content;
		this.header 	= header;
		this.date		=	date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getThreadId() {
		return threadId;
	}
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	
	
}
