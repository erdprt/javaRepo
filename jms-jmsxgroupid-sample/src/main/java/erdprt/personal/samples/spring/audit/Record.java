package erdprt.personal.samples.spring.audit;

public class Record {

	private String content;
	private String header;
	
	public Record(String content, String header) {
		super();
		this.content = content;
		this.header = header;
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
	
	
	
}
