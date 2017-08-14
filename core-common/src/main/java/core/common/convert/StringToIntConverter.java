package core.common.convert;

public class StringToIntConverter implements Converter<Integer> {

	@Override
	public Integer convert(Object value) {
		return ConverterUtils.toInt(value);
	}
	
}
