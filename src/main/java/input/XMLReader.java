package input;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class for reading and parsing an xml file.
 * @author Robin
 *
 */
public class XMLReader {
	
	/**
	 * Reads an xml file and transforms it into a Settings object.
	 * @param xmlFile the filename.
	 * @return a Settings object representing the file.
	 * @throws WrongXMLException when the file is wrongly formatted.
	 */
	public static Settings readXMLFile(String xmlFile) throws WrongXMLException {		
		Settings res = new Settings();		
		
		try {
			// Parse the file.
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new XMLReader().getClass().getResourceAsStream(xmlFile));
			
			// Get the root element.
			Element rootElement = doc.getDocumentElement();
			rootElement.normalize();
			

			// Let Settings decide what needs to be present.
			res.readSettings(rootElement);			
			
			// Get the list of columns
			NodeList columns = doc.getElementsByTagName("column");						
			readColumns(res, columns);	
			
			// Check if duplicate column names exist.
			checkDuplicateColumns(res.getColumns());
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
	 
			// Let each column decide how it is specified.
			Column newColumn = Column.readColumn((Element) node);	
			
			// If a time column is present, the target column should be present as well.
			if (newColumn instanceof TimeColumn)
				checkContains.add(((TimeColumn) newColumn).getTargetDate());
			
			settings.addColumn(newColumn);
		}		
		
		for (Column column : settings.getColumns())
			if (column instanceof DateColumn)
				checkContains.remove(column.getName());
		
		// If the list is not empty, one or more target column are not present.
		if (!checkContains.isEmpty())
			throw new WrongXMLException("Target column(s) not found!");
	}
	
	private static void checkDuplicateColumns(ArrayList<Column> columns) throws WrongXMLException {
		Set<String> columnsSet = new HashSet<String>();
		
		for (Column column : columns) {
			columnsSet.add(column.getName());
		}
		
		if (columnsSet.size() != columns.size())
			throw new  WrongXMLException("Duplicate column names found!"); 
	}
}
