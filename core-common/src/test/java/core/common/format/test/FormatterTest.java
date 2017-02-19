package core.common.format.test;
import core.common.format.NumberFormatter;

public class FormatterTest {

	public static void main(String[] args) {
		NumberFormatter numf = NumberFormatter.get(-1, 2, ",", ".");
//		NumberFormatter numf = NumberFormatter.D();
		System.out.print(numf.format(554657.7687, "0"));
	}

}
