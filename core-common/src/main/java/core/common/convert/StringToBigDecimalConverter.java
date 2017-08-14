package core.common.convert;

import java.math.BigDecimal;

public class StringToBigDecimalConverter implements Converter<BigDecimal> {

	@Override
	public BigDecimal convert(Object value) {
		return ConverterUtils.toBigDecimal(value);
	}
	
}
