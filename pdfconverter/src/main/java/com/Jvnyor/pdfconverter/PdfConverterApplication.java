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

public class PdfConverterApplication {
	
	public static void main(String[] args) {
    	
        try (Scanner in = new Scanner(System.in)) {
			System.out.println("Insira o caminho do pdf:");
			String pdfPath = in.nextLine();
			byte[] pdfPathBytes = pdfPath.getBytes();
			String pdfPathToUTF8 = new String(pdfPathBytes, StandardCharsets.UTF_8);
			
			File file = new File(pdfPathToUTF8);
			System.out.println("Insira o caminho do txt:");
			String txtPath = in.nextLine();
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
			System.out.println("Conversão completa!");
			System.out.println("Arquivo criado em "+txtPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
            
}