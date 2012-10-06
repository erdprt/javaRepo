package erdprt.personal.samples.spring.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DirectoryFactory {

	private String outputDir;

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	
	
	public synchronized String createDirectory() throws IOException {
		String dirName		=	UUID.randomUUID().toString();
		File file			=	new File(getOutputDir(), dirName);
		file.mkdir();
		return file.getCanonicalPath();
	}
	
}
