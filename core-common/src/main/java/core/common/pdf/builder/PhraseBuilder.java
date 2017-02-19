package core.common.pdf.builder;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;

import core.common.utils.CommonUtils;

public class PhraseBuilder extends AbstractPdfBuilder {
	private Phrase phrase;
	
	public PhraseBuilder() {
		this.phrase = new Phrase();
	}
	
	public PhraseBuilder add(Font font, String...strings) {
		Chunk chunk = new Chunk(CommonUtils.concat(strings), font);
		phrase.add(chunk);
		return this;
	}

	@Override
	protected Element doCreateContent() {
		return phrase;
	}
	
	@Override
	public void build(Document document) throws DocumentException {
		document.add(doCreateContent());
		document.add(Chunk.NEWLINE);
	}
}
