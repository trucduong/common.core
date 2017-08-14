package core.common.convert;

public class StringToLongConverter implements Converter<Long> {

	@Override
	public Long convert(Object value) {
		return ConverterUtils.toLong(value);
	}
	
}
