package core.common.pdf;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

import core.common.pdf.builder.AbstractPdfBuilder;
import core.common.utils.CommonUtils;

public class ParagraphBuilder extends AbstractPdfBuilder {
	private Paragraph element;
	
	public ParagraphBuilder() {
		this.element = new Paragraph();
		element.setAlignment(Element.ALIGN_LEFT);
	}
	
	public ParagraphBuilder(Font font) {
		this.element = new Paragraph();
		element.setAlignment(Element.ALIGN_LEFT);
		element.setFont(font);
	}
	
	public ParagraphBuilder(Font font, int align) {
		this.element = new Paragraph();
		element.setAlignment(align);
		element.setFont(font);
	}

	@Override
	protected Element doCreateContent() {
		return element;
	}
	
	public ParagraphBuilder add(String... texts) {
		element.add(CommonUtils.concat(texts));
		return this;
	}
}
