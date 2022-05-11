package com.Jvnyor.pdftextextractor;

import java.util.Scanner;

public class App {

	/**
	 * @author Josias Junior https://github.com/Jvnyor
	 */

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {

			System.out.print("Insert the .pdf file path in computer: ");

			String pdfPath = scanner.nextLine();

			System.out.print("\nInsert the .txt file path to save into computer: ");

			String txtPath = scanner.nextLine();

			PDFTextExtractor.extractText(pdfPath, txtPath);

		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

}
