package djole;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		
		GsonWriter gsonWriter = new GsonWriter();
		gsonWriter.writeToJson(new XmlXPathParser().process(FilepathConstants.XMLFILEPATH), FilepathConstants.GSONFILEPATH);
		
		JacksonWriter jacksonWriter = new JacksonWriter();
		jacksonWriter.writeToJackson(new XmlXPathParser().process(FilepathConstants.XMLFILEPATH), FilepathConstants.JACKSONFILEPATH);

	}

}
