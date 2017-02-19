package core.dao.search;

public class PaggingInfo {
	/**
	 * start from 1
	 */
	private int currentPage;
	private long totalRows;
	private int totalPage;
	private int numberRowsOfPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getNumberRowsOfPage() {
		return numberRowsOfPage;
	}

	public void setNumberRowsOfPage(int numberRowsOfPage) {
		this.numberRowsOfPage = numberRowsOfPage;
	}
	
	public int getFirstRowIndex() {
		return (currentPage * numberRowsOfPage) + 1;
	}

}
