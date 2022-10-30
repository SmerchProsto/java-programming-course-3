package com.company;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static com.company.Main.*;

public class LabDOMParser {
    NodeList students;
    NodeList studentsChilds;
    NodeList averages;



    public LabDOMParser(String fileName) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File(fileName));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(fileName);

        students = doc.getElementsByTagName("student");
        boolean hasElement = false;

        for (int i = 0; i < students.getLength(); i++) {
            studentsChilds = students.item(i).getChildNodes();
            for (int j = 0; j < studentsChilds.getLength(); j++) {
                // debug System.out.println(studentsChilds.item(j).getNodeName());
                if (studentsChilds.item(j).getNodeName().equalsIgnoreCase("average")) {
                    hasElement = true;
                    break;
                }
                hasElement = false;
            }
            if (!hasElement) {
                Element root = doc.createElement("average");
                root.appendChild(doc.createTextNode("0"));
                students.item(i).appendChild(root);
                transformer.transform(source, result);
            }
        }

        averages = doc.getElementsByTagName("average");

        for (int i = 0; i < averages.getLength(); i++) {
            try {
                if (Integer.parseInt(averages.item(i).getTextContent()) != averageMarks.get(i) || ("".equals(averages.item(i).getTextContent()))) {
                    averages.item(i).setTextContent(String.valueOf(averageMarks.get(i)));
                    transformer.transform(source, result);
                }
            }
            catch (NumberFormatException ex) {
                /*averages.item(i).setTextContent("0");*/
                averages.item(i).setTextContent(String.valueOf(averageMarks.get(i)));
                transformer.transform(source, result);
            }
        }

       /* for (int i = 0; i < averages.getLength(); i++) {
            System.out.println(averages);
            System.out.println(averageMarks.get(i));

            System.out.println("".equals(averages.item(i).getTextContent()));
            if (Integer.parseInt(averages.item(i).getTextContent()) != averageMarks.get(i) || ("".equals(averages.item(i).getTextContent()))) {
                averages.item(i).setTextContent(String.valueOf(averageMarks.get(i)));
            }
            // System.out.println(averages.item(i).getTextContent()); debug
        }


        transformer.transform(source, result);*/
    }


}
