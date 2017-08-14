package core.common.query;

public class PagingInfo {
	private int firstIndex;
	private int maxRecord;

	public PagingInfo() {
		
	}
	
	public PagingInfo(int firstIndex, int maxRecord) {
		this.firstIndex = firstIndex;
		this.maxRecord = maxRecord;
	}
	
	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getMaxRecord() {
		return maxRecord;
	}

	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(firstIndex).append(",").append(maxRecord);
		return builder.toString();
	}
}
