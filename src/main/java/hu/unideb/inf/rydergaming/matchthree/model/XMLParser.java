package hu.unideb.inf.rydergaming.matchthree.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class for loading and saving the score table as an XML file.
 * @author Ryder
 *
 */
public class XMLParser {

	/**
	 * Loads the score table from an XML file.
	 * @param input File the file to load the score table from.
	 * @return List Returns a 2D ArrayList sorted by the players' score.
	 */
	public static List loadXML(File input) {
		List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
		try {	
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input);
			NodeList nList = doc.getElementsByTagName("player");			
			for (int i=0; i<nList.getLength(); i++) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String name = element.getElementsByTagName("name")
							.item(0)
							.getTextContent();
					String score = element.getElementsByTagName("score").item(0).getTextContent();
					lista.add(new ArrayList<String>(Arrays.asList(name, score)));
				}				
			}
			Collections.sort(lista, new Comparator<ArrayList<String>>() {
		        @Override
		        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
		            return Integer.parseInt(o2.get(1).toString()) - Integer.parseInt(o1.get(1).toString());
		        }   				
			});


		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
		
	}
	
	/**
	 * Saves the list to an XML file.
	 * @param lista List a 2D arraylist of the score table.
	 * @param file File the file to save the score table to.
	 */
	public static void saveXML(List<ArrayList<String>> lista, File file) {
		try {
			Document dom  = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = dom.createElement("scores");
			for (List l: lista) {
				Element e = dom.createElement("player");
				Element name = dom.createElement("name");
				name.appendChild(dom.createTextNode(l.get(0).toString()));
				Element score = dom.createElement("score");
				score.appendChild(dom.createTextNode(l.get(1).toString()));
				e.appendChild(name);
				e.appendChild(score);
				root.appendChild(e);				
			}

			dom.appendChild(root);
			try {
	            Transformer optimus = TransformerFactory.newInstance().newTransformer();
	            optimus.setOutputProperty(OutputKeys.METHOD, "xml");
	            optimus.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            //optimus.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
	            optimus.setOutputProperty(OutputKeys.INDENT, "yes");
	            optimus.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	            optimus.transform(new DOMSource(dom), 
	                                 new StreamResult(new FileOutputStream(file)));

	        } catch (TransformerException te) {
	            System.out.println(te.getMessage());
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage());
	        }
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
