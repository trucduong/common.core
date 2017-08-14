package core.common.compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZipper {
	private String sourceFolder;
	private List<String> fileList;

	public FileZipper(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	// private static final String OUTPUT_ZIP_FILE = "C:\\MyFile.zip";
	// private static final String SOURCE_FOLDER = "C:\\testzip";
	// public static void main(String[] args) {
	// AppZip appZip = new AppZip();
	// appZip.generateFileList(new File(SOURCE_FOLDER));
	// appZip.zipIt(OUTPUT_ZIP_FILE);
	// }

	public void zipIt(List<String> files, String zipFile) {
		byte[] buffer = new byte[1024];

		try {

			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (String file : files) {
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);

				FileInputStream in = new FileInputStream(sourceFolder + File.separator + file);

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
			}

			zos.closeEntry();
			// remember close it
			zos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Zip it
	 * 
	 * @param zipFile
	 *            output ZIP file location
	 */
	public void zipIt(String zipFile) {
		this.fileList = new ArrayList<String>();
		generateFileList(new File(sourceFolder));
		this.zipIt(fileList, zipFile);
	}

	/**
	 * Traverse a directory and get all files, and add the file into fileList
	 * 
	 * @param node
	 *            file or directory
	 */
	private void generateFileList(File node) {

		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename));
			}
		}

	}

	/**
	 * Format the file path for zip
	 * 
	 * @param file
	 *            file path
	 * @return Formatted file path
	 */
	private String generateZipEntry(String file) {
		return file.substring(sourceFolder.length() + 1, file.length());
	}
}
