package core.common.format;

import java.text.DecimalFormat;
import java.util.Locale;

public class NumberFormatterTest2 {

	public static void main(String[] args) {
		Number number = new Number();
					
		String numberStr = "123456.789";
		
		Formatter formatter = NumberFormatter.COMMA_SEPERATED(Locale.ENGLISH);
		
		System.out.println(formatter.format(number, null));
		System.out.println(formatter.parse(numberStr, null));
	
	}
}
 