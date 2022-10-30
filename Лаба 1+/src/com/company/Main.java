package com.company;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Integer> averageMarks = new ArrayList<>();



    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {


        System.out.print("Введите название файла(пример: file.xml): ");
        Scanner scan = new Scanner(System.in);
        String fileName;

        fileName = scan.next();
        LabSAXParser parserXMLSAX = new LabSAXParser(fileName);
        LabDOMParser parserXMLDOM = new LabDOMParser(fileName);

        System.out.println("Измененный xml файл " + fileName + " готов!");


    }
}
