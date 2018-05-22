
/**
 * Write a description of class ParsePosts here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;

public class ParsePosts extends DefaultHandler{
    
    private void parseTagsPost(String tags){
        HashSet<String> set = new HashSet<String>();
        
        StringBuilder sb = new StringBuilder(tags);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length()-1);
        
        String[] parts = sb.toString().split("><");
        
        for(int i=0; i<parts.length; i++){
            set.add(parts[i]);
        }
    }
    
    private void parseTitle(String str){
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("row")) {
            int postType = Integer.parseInt(attributes.getValue("PostTypeId"));
            
            if(postType == 1){
                parseTagsPost(attributes.getValue("Tags"));
                parseTitle(attributes.getValue("Title"));
            }
            else{
                if(postType == 2){
                }
            }
            
            /*
             * Guardar postType
             * attributes.getValue("Id")
             * attributes.getValue("CreationDate")
             * attributes.getValue("Owner")
             */
        } 
    }
}
