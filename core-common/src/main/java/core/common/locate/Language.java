package core.common.locate;

public enum Language {
	ENGLISH("en"), VIET_NAM("vi");

	private String value;

	Language(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Language fromString(String value) {
		if (value != null) {
			for (Language b : Language.values()) {
				if (value.equalsIgnoreCase(b.value)) {
					return b;
				}
			}
		}
		return Language.ENGLISH;
	}
}
