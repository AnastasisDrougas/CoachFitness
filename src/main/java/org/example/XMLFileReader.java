package org.example;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.FileInputStream;

public class XMLFileReader {

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

    public XMLFileReader() {}

    public Activities fileReader(String filename){
        Activities list = null;
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new FileInputStream(filename));
            list = new Activities(doc.getElementsByTagName("Activity"));
        } catch (Exception e) {
            System.out.println("Wrong file type!");
            e.printStackTrace();
        }
        return list;
    }

    public static String getNodeValue(NodeList n) {
        return n.item(0).getChildNodes().item(0).getNodeValue();
    }


}
