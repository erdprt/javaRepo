package erdprt.personal.samples.spring.audit;

import java.text.SimpleDateFormat;

public class RecordFormater {

	private static String lineSeparator					=	System.getProperty("line.separator");
	private static final SimpleDateFormat dateFormat 	=	new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	
	public String format(Record record) {
		StringBuilder writer	=	new StringBuilder();

		writer.append("date : ");
		writer.append(dateFormat.format(record.getDate()));
		writer.append(";header : ");
		writer.append(pad(record.getHeader(), 20));
		writer.append(";content : " + record.getContent());
    	writer.append(lineSeparator);
    	
    	return writer.toString();
	}
	
	public String pad(String value, Integer length) {
		
		StringBuilder builder	=	new StringBuilder();
		if (value==null) value	=	"";
		
		builder.append(value);
		if (value.length()<length) {
			for (Integer index=value.length();index<length;index++) {
				builder.append(" ");
			}
		}
		return builder.toString();
		
	}
	
}
