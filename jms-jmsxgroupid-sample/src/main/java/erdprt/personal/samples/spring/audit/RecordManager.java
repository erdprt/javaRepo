package erdprt.personal.samples.spring.audit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import erdprt.personal.samples.spring.destination.listeners.DefaultMessageListener;

/**
 * 
 * @author erdprt
 *
 */
public class RecordManager {

	private static Logger logger	=	LoggerFactory.getLogger(RecordManager.class);	
	
	private List<Record> records	=	new ArrayList<>();
	private String outputDir;
	private String name;
	
	public void add(Record record) {
		this.records.add(record);
	}
	
	public void flush() {
		
		logger.debug("flush records");
		
		Collections.sort(this.records, new DateComparator());
		
		new RecordManagerWriter().flush(getName(), getOutputDir(), this.records);
		
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
