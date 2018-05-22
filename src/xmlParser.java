
/**
 * Write a description of class xmlParser here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class xmlParser{
   
   public static void parser(String dump_path){
       SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
       try{
           SAXParser saxParser = saxParserFactory.newSAXParser();
           ParseUsers users = new ParseUsers();
           ParsePosts posts = new ParsePosts();
           ParseTags tags = new ParseTags();
           System.out.println("\nUsers:");
           saxParser.parse(new File(dump_path+"/Users.xml"), users);
           System.out.println("\nPosts:");           
           saxParser.parse(new File(dump_path+"/Posts.xml"), posts);
           System.out.println("\nTags:");           
           saxParser.parse(new File(dump_path+"/Tags.xml"), tags);
       }
       catch(ParserConfigurationException | SAXException | IOException e){
           e.printStackTrace();
       }
   }  
}
