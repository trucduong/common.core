package core.common.pdf;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import core.common.pdf.builder.PhraseBuilder;
import core.common.pdf.builder.TableBuilder;


public abstract class PdfGenerator<M extends PdfGeneratorModel> {
	protected Logger LOGGER = LoggerFactory.getLogger(PdfGenerator.class);
	protected static final int FONT_SIZE = 12;

	private static final String FONT = "/fonts/tahoma/tahoma.ttf";
	private static final String FONT_BOLD = "/fonts/tahoma/tahomabd.ttf";

	protected abstract void generate(M model, Document document) throws Exception;

	protected boolean before(M model) {
		return true;
	}

	protected void after(M model) { }

	protected void onError(Exception ex) {
		LOGGER.error("Can not genereate PDF file: ", ex);
	}

	public boolean generate(M model, OutputStream out) {
		try {
			if (!before(model)) {
				return false;
			}

			Document document = new Document();
			PdfWriter.getInstance(document, out);
			document.open();

			generate(model, document);

			after(model);
			document.close();
			return true;
		} catch (Exception e) {
			onError(e);
		}
		return false;
	}

	protected Font getFont() throws DocumentException, IOException {
		return this.getFont(FONT_SIZE);
	}

	protected Font getFont(int fontSize) throws DocumentException, IOException {
		BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bf, fontSize);
		return font;
	}

	protected Font getFontBold() throws DocumentException, IOException {
		return this.getFontBold(FONT_SIZE);
	}

	protected Font getFontBold(int fontSize) throws DocumentException, IOException {
		BaseFont bf = BaseFont.createFont(FONT_BOLD, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bf, fontSize);
		return font;
	}
	
	protected ParagraphBuilder createParagraphBuilder(Font font, int align) throws DocumentException {
		ParagraphBuilder builder = new ParagraphBuilder(font, align);
		return builder;
	}
	
	protected ParagraphBuilder createParagraphBuilder(Font font) throws DocumentException {
		ParagraphBuilder builder = new ParagraphBuilder(font);
		return builder;
	}
	
	protected PhraseBuilder createPhraseBuilder() throws DocumentException {
		PhraseBuilder builder = new PhraseBuilder();
		return builder;
	}
	
	protected TableBuilder createTableBuilder() {
		TableBuilder builder = new TableBuilder();
		return builder;
	}
}
