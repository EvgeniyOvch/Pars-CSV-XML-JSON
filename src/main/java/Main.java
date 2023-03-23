import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws  Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("config.xml");


        XPath xPath = XPathFactory.newInstance().newXPath();

        String loadFileName = xPath
                .compile("config/load/fileName")
                .evaluate(doc);
        String loadFormat = xPath
                .compile("config/load/format")
                .evaluate(doc);
        Boolean loadEnabled = Boolean.parseBoolean(xPath
                .compile("config/load/enabled")
                .evaluate(doc));

        Basket basket;
        if (loadEnabled){
            File loadFile = new File(loadFileName);
            switch (loadFormat){
                case "json" : basket = Basket.loadFromJson(loadFile); break;
                case "txt" : basket = Basket.loadFromTxt(loadFile); break;
                case  "bin" : basket = Basket.loadFromBin(loadFile); break;
            }
        } else {
            basket = new Basket();
        }

    }

    }
    

