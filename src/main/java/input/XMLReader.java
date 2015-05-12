package input;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
			
			res.readSettings(rootElement);			
			
			NodeList columns = doc.getElementsByTagName("column");						
			readColumns(res, columns);			
		} 
		catch (SAXException | IOException | ParserConfigurationException e) {
			throw new WrongXMLException(e.getMessage());
		}
		
		return res;
	}
	
	private static void readColumns(Settings settings, NodeList columns) throws WrongXMLException {
		List<String> checkContains = new ArrayList<String>();
		
		for (int i = 0; i < columns.getLength(); i++) {				 
			Node node = columns.item(i);
	 
			Column newColumn = Column.readColumn((Element) node);	
			
			if (newColumn instanceof TimeColumn)
				checkContains.add(((TimeColumn) newColumn).getTargetDate());
			
			settings.addColumn(newColumn);
		}		
		
		for (Column column : settings.getColumns()) 
			checkContains.remove(column.getName());
		
		if (!checkContains.isEmpty())
			throw new WrongXMLException("Target column(s) not found!");
	}
}
