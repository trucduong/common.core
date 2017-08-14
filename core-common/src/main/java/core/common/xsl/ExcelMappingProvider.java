package core.common.xsl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.common.convert.ConverterUtils;
import core.common.reflect.ObjectModifier;
import core.common.xsl.anotation.XSLColumn;
import core.common.xsl.anotation.XSLSheet;

public class ExcelMappingProvider {
	public static <T> List<T> read(Class<T> objClass, FileInputStream file) {
		List<T> results = new ArrayList<T>();
		try {
			// Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get sheet from the workbook
			XSLSheet sheetAnotation = AnotationProvider.getClassAnotation(objClass, XSLSheet.class);
			XSSFSheet sheet = workbook.getSheet(sheetAnotation.name());

			int rowIndex = sheetAnotation.startRow();
			while (true) {
				Row row = sheet.getRow(rowIndex);

				// check continue
				if (getCellValue(row.getCell(0)) == null) {
					break;
				}

				T obj = objClass.newInstance();
				Field fileds[] = objClass.getFields();
				for (Field field : fileds) {
					if (field.isAnnotationPresent(XSLColumn.class)) {
						XSLColumn colAnnotation = field.getAnnotation(XSLColumn.class);
						Cell cell = row.getCell(colAnnotation.index());
						ObjectModifier.set(obj, field, colAnnotation.converter().newInstance().convert(getCellValue(cell)));
					}
				}

				results.add(obj);
				rowIndex++;
			}
		} catch (Exception e) {
		}
		return results;
	}

	public static <T> boolean write(Class<T> objClass, List<T> objects, InputStream file, OutputStream out) {
		try {
			// Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			// Get sheet from the workbook
			XSLSheet sheetAnotation = AnotationProvider.getClassAnotation(objClass, XSLSheet.class);
			XSSFSheet sheet = workbook.getSheet(sheetAnotation.name());

			int rowIndex = sheetAnotation.startRow();
			for (Object obj : objects) {
				Row row = sheet.getRow(rowIndex);

				Field fileds[] = objClass.getDeclaredFields();
				for (Field field : fileds) {
					if (field.isAnnotationPresent(XSLColumn.class)) {
						XSLColumn colAnnotation = field.getAnnotation(XSLColumn.class);
						Cell cell = row.createCell(colAnnotation.index());
						setCellValue(cell, ObjectModifier.get(obj, field));
					}
				}

				rowIndex++;
			}
			
			workbook.write(out);
		} catch (Exception e) {
			return false;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
		return true;
	}

	public static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		}
		return null;
	}
	
	public static void setCellValue(Cell cell, Object obj) {
		if(obj == null) {
			return;
		}
		
		if (obj instanceof Integer || obj instanceof Long || obj instanceof Double || obj instanceof BigDecimal) {
			cell.setCellValue(ConverterUtils.toDouble(obj));
		} else if (obj instanceof String) {
			cell.setCellValue(String.valueOf(obj));
		} else {
			cell.setCellValue(String.valueOf(obj));
		}
	}
}
