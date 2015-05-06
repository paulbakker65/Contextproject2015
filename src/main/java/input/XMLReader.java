package input;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLReader {
	public static Settings readXMLFile(String xmlFile) throws WrongXMLException {		
		Settings res = new Settings();
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new XMLReader().getClass().getResourceAsStream(xmlFile));
			
			Element rootElement = doc.getDocumentElement();
			rootElement.normalize();
			
			readSettings(res, rootElement);			
			
			NodeList columns = doc.getElementsByTagName("column");						
			readColumns(res, columns);			
		} 
		catch (SAXException | IOException | ParserConfigurationException e) {
			throw new WrongXMLException(e.getMessage());
		}
		
		return res;
	}
	
	public static void readSettings(Settings settings, Element element) throws WrongXMLException {
		if (!element.getNodeName().equals("settings"))
			throw new WrongXMLException();
		
		String startLine = element.getAttribute("startLine");
		
		if (startLine.isEmpty())
			throw new WrongXMLException();
		
		try {
			settings.setStartLine(Integer.parseInt(startLine));
		}
		catch (NumberFormatException e) {
			throw new WrongXMLException();
		}
		
		String delimiter = element.getAttribute("delimiter");
		
		if (delimiter.isEmpty())
			throw new WrongXMLException();
		
		settings.setDelimiter(delimiter);
	}
	
	public static void readColumns(Settings settings, NodeList columns) throws WrongXMLException {
		for (int i = 0; i < columns.getLength(); i++) {				 
			Node node = columns.item(i);
	 
			if (node.getNodeType() != Node.ELEMENT_NODE) 
				throw new WrongXMLException();
	 
			readColumn(settings, (Element) node);			
		}		
	}
	
	public static void readColumn(Settings settings, Element column) throws WrongXMLException {
		String name = column.getAttribute("name");
		
		if (name.isEmpty())
			throw new WrongXMLException();
		
		String type = column.getAttribute("type");
		
		Column newCol = null;
		
		switch (type) {			
			case "number": 	newCol = new NumberColumn(name);
							break;
			case "date":	newCol = new DateColumn(name);
							setDateFormat((DateColumn) newCol, column);
							break;
			default: 		newCol = new StringColumn(name);
							break;
		}
		
		settings.addColumn(newCol);
	}
	
	public static void setDateFormat(DateColumn date, Element column) throws WrongXMLException {
		String format = column.getAttribute("format");
		
		if (format.isEmpty())
			throw new WrongXMLException();
		
		date.setFormat(format);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(readXMLFile("/settings.xml"));
		} 
		catch (WrongXMLException e) {
			e.printStackTrace();
		}
	}
}
