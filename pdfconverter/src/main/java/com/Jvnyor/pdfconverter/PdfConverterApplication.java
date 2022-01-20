package com.Jvnyor.pdfconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import io.github.jonathanlink.PDFLayoutTextStripper;

public class PdfConverterApplication {

    public static void main(String[] args) throws IOException {
        
        	String string = null;
        	Scanner in = new Scanner(System.in);
            System.out.println("Insira o caminho do pdf");
            String pdfPath = in.nextLine();
            File file = new File(pdfPath);
            String txtName = file.getName();
            String txtPath = "C:\\pdfConverter\\txts\\"+txtName+".txt";
            if (pdfPath == null) {
            	System.out.println("Erro! Caminho para pdf inválido ou pdf inválido!");
            }
            try {
            	
                PDFParser pdfParser = new PDFParser(new RandomAccessFile(file, "r"));
                pdfParser.parse();
                PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
                PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
                string = pdfTextStripper.getText(pdDocument);
                
                PrintWriter out = new PrintWriter(new FileOutputStream(txtPath));
                String lines[] = string.split("\\r?\\n");
    	        for (String line : lines) {
    	        	
    	        	out.println(line);
    	        	
    	        }
    	        
                out.flush();
                out.close();
                System.out.println("Conversão completa!");
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            };
            
    }

}