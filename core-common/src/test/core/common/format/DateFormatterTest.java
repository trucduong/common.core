package core.common.format;

import java.util.Date;
import java.util.Locale;

public class DateFormatterTest {

	public static void main(String[] args) {
		Date date = new Date();
		String dateStr = "6/8/2016 08:16 PM";
		
		Formatter formatter = DateFormatter.hmmAMPM(Locale.ENGLISH);
		
		System.out.println(formatter.format(date, null));
		System.out.println(formatter.parse(dateStr, null));
	}
	
	
	

}
 