package core.common.convert;

public class StringConverter implements Converter<String> {

	@Override
	public String convert(Object value) {
		return ConverterUtils.toString(value);
	}
	
}
