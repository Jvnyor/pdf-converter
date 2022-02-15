package com.Jvnyor.pdfconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import io.github.jonathanlink.PDFLayoutTextStripper;

public class PdfConverterApp {
	
	/**
	 * @author Josias Junior https://github.com/Jvnyor
	 */
	
	public static void main(String[] args) {

		System.out.println("\n"
				+".---. .---. .---.    .--.  .--. .-..-..-..-. .--. .---. .-----. .--. .---. \r\n"
				+ ": .; :: .  :: .--'  : .--': ,. :: `: :: :: :: .--': .; :`-. .-': .--': .; :\r\n"
				+ ":  _.': :: :: `;    : :   : :: :: .` :: :: :: `;  :   .'  : :  : `;  :   .'\r\n"
				+ ": :   : :; :: :     : :__ : :; :: :. :: `' ;: :__ : :.`.  : :  : :__ : :.`.\r\n"
				+ ":_;   :___.':_;     `.__.'`.__.':_;:_; `.,' `.__.':_;:_;  :_;  `.__.':_;:_;");
		
	     try (Scanner scanner = new Scanner(System.in)) {
	        	
	        char nextLine;
	        	
	        do {
		        	
				System.out.print("\nInsert the .pdf file path in computer: ");
					
				String pdfPath = scanner.nextLine();
					
				byte[] pdfPathBytes = pdfPath.getBytes();
					
				String pdfPathToUTF8 = new String(pdfPathBytes, StandardCharsets.UTF_8);
					
				File file = new File(pdfPathToUTF8);
				
				System.out.print("\nInsert the .txt file path to save into computer: ");
					
				String txtPath = scanner.nextLine();
					
				byte[] txtPathBytes = txtPath.getBytes();
					
				String txtPathToUTF8 = new String(txtPathBytes, StandardCharsets.UTF_8);
					
				PDFParser pdfParser = new PDFParser(new RandomAccessFile(file, "r"));
					
				pdfParser.parse();
					
				PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
					
				PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
					
				String string = pdfTextStripper.getText(pdDocument);
					        
				PrintWriter out = new PrintWriter(new FileOutputStream(txtPathToUTF8));
					
				String lines[] = string.split("\\r?\\n");
					
				for (String line : lines) {	
						
					out.println(line);
						
				}
					    
				out.flush();
					
				out.close();
					
				pdDocument.close();
					
				System.out.println("\nFile created in "+txtPath);
					
				System.out.print("\nDo you wants new conversion? Y/N: ");
					
				nextLine = scanner.nextLine().charAt(0);
					
	        } while (nextLine == 'Y' || nextLine == 'y');
	        	
	        scanner.close();
	        	
		} catch (FileNotFoundException e) {
				
			e.printStackTrace();
				
		} catch (IOException e) {
				
			e.printStackTrace();
				
		}
		
	}

}
