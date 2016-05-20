package hu.unideb.inf.rydergaming.matchthree.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.Rule;

import hu.unideb.inf.rydergaming.matchthree.model.XMLParser;

public class XMLParserTest {
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Test
	public void testLoadXML() throws URISyntaxException, IOException {
		File file = tempFolder.newFile("score.xml");
		PrintWriter pw = new PrintWriter(file);
	    List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
		lista = XMLParser.loadXML(file);
		List<ArrayList<String>> l = new ArrayList<ArrayList<String>>();
		/*l.add(new ArrayList<String>(Arrays.asList("A","1")));
		l.add(new ArrayList<String>(Arrays.asList("B","2")));
		l.add(new ArrayList<String>(Arrays.asList("C","3")));*/
		assertEquals(l, lista);
	}

	@Test
	public void testSaveXML() {
		//fail("Not yet implemented");
	}

}
