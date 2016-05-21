package hu.unideb.inf.rydergaming.matchthree.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;

import hu.unideb.inf.rydergaming.matchthree.model.XMLParser;

public class XMLParserTest {
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	
	public File createFile() throws IOException, ParserConfigurationException {
		File file = tempFolder.newFile("testScore.xml");
		Document dom  = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = dom.createElement("scores");
		Element e = dom.createElement("player");
		Element name = dom.createElement("name");
		name.appendChild(dom.createTextNode("A"));
		Element score = dom.createElement("score");
		score.appendChild(dom.createTextNode("1"));
		e.appendChild(name);
		e.appendChild(score);
		root.appendChild(e);
		
		e = dom.createElement("player");
		name = dom.createElement("name");
		name.appendChild(dom.createTextNode("B"));
		score = dom.createElement("score");
		score.appendChild(dom.createTextNode("3"));
		e.appendChild(name);
		e.appendChild(score);
		root.appendChild(e);
		
		e = dom.createElement("player");
		name = dom.createElement("name");
		name.appendChild(dom.createTextNode("C"));
		score = dom.createElement("score");
		score.appendChild(dom.createTextNode("2"));
		e.appendChild(name);
		e.appendChild(score);
		root.appendChild(e);
		
		
		
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
		return file;
	}
	
	@Test
	public void testLoadXML() throws URISyntaxException, IOException, ParserConfigurationException {
		
		//PrintWriter pw = new PrintWriter(file);
	    List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
		lista = XMLParser.loadXML(createFile());
		List<ArrayList<String>> l = new ArrayList<ArrayList<String>>();
		l.add(new ArrayList<String>(Arrays.asList("B","3")));
		l.add(new ArrayList<String>(Arrays.asList("C","2")));
		l.add(new ArrayList<String>(Arrays.asList("A","1")));
		assertEquals(l, lista);
	}

	@Test
	public void testSaveXML() throws IOException {
		List<ArrayList<String>> l = new ArrayList<ArrayList<String>>();
		l.add(new ArrayList<String>(Arrays.asList("B","3")));
		l.add(new ArrayList<String>(Arrays.asList("C","2")));
		l.add(new ArrayList<String>(Arrays.asList("A","1")));
		File file = tempFolder.newFile("testScore.xml");
		XMLParser.saveXML(l, file);
		
	    List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
	    lista = XMLParser.loadXML(file);
		assertEquals(l, lista);
	}

}
