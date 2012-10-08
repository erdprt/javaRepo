package erdprt.personal.samples.spring.audit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities to generate file reporting
 * @author edrprt
 *
 */
public class RecordManagerWriter {
	
	private static final Logger logger	=	LoggerFactory.getLogger(RecordManagerWriter.class);
	
	public void flush(String name, String outputDir, List<Record> records) {
		
		String filename			=	name + ".txt";
		File file				=	new File(outputDir, filename );
		BufferedWriter writer	=	null;
		
		try {
		    writer = new BufferedWriter(new FileWriter(file));
		    
		    for (Record record: records) {
		    	String line	=	new RecordFormater().format(record);
		    	writer.write(line);
		    }
		    
		} catch (Exception e) {
			logger.error("error:", e);
		} finally {
			try {
				if (writer!=null) {
					writer.close();
				}
			} catch (IOException ioe) {
				logger.warn("Problem when closing audit file:", ioe);
			}
		}
	}
}
