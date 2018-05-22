
/**
 * Write a description of class ParseUsers here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseUsers extends DefaultHandler{
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("row")) {
            String id = attributes.getValue("Id");
            String rep = attributes.getValue("Reputation");
            String date = attributes.getValue("CreationDate");
            String name = attributes.getValue("DisplayName");
            String bio = attributes.getValue("AboutMe");
            
            System.out.println("Id:"+id);
            System.out.println("Reputation:"+rep);
            System.out.println("CreationDate:"+date);
            System.out.println("DisplayName:"+name);
            System.out.println("About me:"+bio);
        } 
    }
}
