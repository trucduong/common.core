package core.common.pdf.builder;

import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;

public class TableBuilder extends AbstractPdfBuilder {
	private int numColumns = 1;
	private List<String[]> headers;
	private Font headerFont;
	private List<String[]> rows;
	private Font normalFont;
	
	public TableBuilder() {
		this.headers = new ArrayList<String[]>();
		this.rows = new ArrayList<String[]>();
	}
	
	public TableBuilder(int numColumns) {
		this.numColumns = numColumns;
	}
	
	@Override
	protected Element doCreateContent() {
		updateColumnNum();
		PdfPTable element = new PdfPTable(numColumns);
		element.setHeaderRows(this.headers.size());
		for (String[] header : headers) {
			for (String string : header) {
				element.addCell(new Phrase(string, headerFont));
			}
		}
		
		for (String[] row : rows) {
			for (String string : row) {
				element.addCell(new Phrase(string, normalFont));
			}
		}
		element.setWidthPercentage(100);
		return element;
	}

	public TableBuilder addHeader(Font font, String[] header) {
		this.headers.add(header);
		this.headerFont = font;
		return this;
	}
	
	public TableBuilder addRow(Font font, String[] row) {
		this.rows.add(row);
		this.normalFont = font;
		return this;
	}
	
	private void updateColumnNum() {
		if (this.headers.size() > 0) {
			this.numColumns = this.headers.get(0).length;
			
		} else if (this.rows.size() > 0) {
			this.numColumns = this.rows.get(0).length;
		}
	}
	
}
