package erdprt.personal.samples.spring.audit;

import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<Record> {

	@Override
	public int compare(Record record1, Record record2) {
		return record1.getDate().compareTo(record2.getDate());
	}


	
	
}
