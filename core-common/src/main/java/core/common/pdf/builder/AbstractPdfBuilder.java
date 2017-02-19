package core.common.pdf.builder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

public abstract class AbstractPdfBuilder {
	
	protected abstract Element doCreateContent();

	public void build(Document document) throws DocumentException {
		document.add(doCreateContent());
	}
}
