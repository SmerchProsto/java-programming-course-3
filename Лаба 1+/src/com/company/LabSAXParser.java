package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Main.*;

public class LabSAXParser {

    static String lastname;
    static ArrayList<Integer> marks = new ArrayList<>();
    static int average;


    public LabSAXParser(String fileName) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startDocument() throws SAXException {
            // Тут будет логика реакции на начало документа
        }

        @Override
        public void endDocument() throws SAXException {
            // Тут будет логика реакции на конец документа
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            // Тут будет логика реакции на начало элемента

            if (qName.equalsIgnoreCase("student")) {
                marks.clear();
                average = 0;
            }

            if (qName.equalsIgnoreCase("subject")) {
                marks.add(Integer.valueOf(attributes.getValue("mark")));
            }

            /*if (qName.equalsIgnoreCase("average")) {
                for (int i = 0; i < marks.size(); i++) {
                    average += marks.get(i);
                }

                average = Math.round(average / marks.size());
                averageMarks.add(average);

            }
*/

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            // Тут будет логика реакции на конец элемента
            if (qName.equalsIgnoreCase("student")) {
                for (int i = 0; i < marks.size(); i++) {
                    average += marks.get(i);
                }

                average = Math.round(average / marks.size());
                averageMarks.add(average);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            // Тут будет логика реакции на текст между элементами
            /*String averageXML = new String(ch, start, length);
            if (Integer.parseInt(averageXML) != average) {
                averageXML.*/

        }
    }
}
