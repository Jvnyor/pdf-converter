package com.Jvnyor.pdftextextractor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import io.github.jonathanlink.PDFLayoutTextStripper;

public class PDFTextExtractor {

	/**
	 * @author Josias Junior https://github.com/Jvnyor
	 */

	public static void extractText(String pdfPath, String txtPath) {

		File file = null;

		if (pdfPath != null && !pdfPath.isBlank()) {
			file = new File(pdfPath);
		}

		PDFParser pdfParser = null;

		try {

			pdfParser = new PDFParser(new RandomAccessFile(file, "r"));

			pdfParser.parse();

			PDDocument pdDocument = new PDDocument(pdfParser.getDocument());

			PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();

			String string = pdfTextStripper.getText(pdDocument);

			PrintWriter out = null;

			if (txtPath != null || !txtPath.isBlank() || !txtPath.isEmpty()) {
				out = new PrintWriter(new FileOutputStream(txtPath));
			}

			String lines[] = string.split("\\r?\\n");

			for (String line : lines) {
				out.println(line);
			}

			out.flush();

			out.close();

			pdDocument.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
