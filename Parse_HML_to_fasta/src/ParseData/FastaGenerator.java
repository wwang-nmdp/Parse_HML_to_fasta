package ParseData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import databaseAccess.DatabaseUtil;

public class FastaGenerator {
	// The input file
	private File input;
	
	private String sampleID;

	/**
	 * The method to parse the input file from xml to fasta.
	 * 
	 * @param intput The input file.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void run(File intput)
			throws ParserConfigurationException, SAXException, IOException {

		// Setup input and connect to database
		this.input = intput;
		
	
		// Initialize doc
		Document doc = getDoc();

		// Get all sample nodes
		NodeList sampleList = doc.getElementsByTagName("sample");
		for (int i = 0; i < sampleList.getLength(); i++) {
			parseSample(sampleList.item(i));
		}
		

	}

	/**
	 * Get doc from input file.
	 * 
	 * @return A document.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private Document getDoc() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(input);
		doc.getDocumentElement().normalize();
		return doc;
	}


	/**
	 * Parse the sample node.
	 * 
	 * @param node The sample node.
	 */
	private void parseSample(Node node) {
		
		setSampleID(node);
		Element sample = (Element) node;
		// Get all typing nodes
		NodeList typingList = sample.getElementsByTagName("typing");
		for (int j = 0; j < typingList.getLength(); j++) {
			parseTyping(typingList.item(j));
		}
	
	}
	
	private void setSampleID(Node smapleNode){
		Element sample = (Element) smapleNode;
		sampleID = sample.getAttribute("id");
	}
	
	

	/**
	 * Parse the typing node.
	 * 
	 * @param hla The typing node.
	 */
	public void parseTyping(Node hla) {
		Element element = (Element) hla;
		NodeList haploids = element.getElementsByTagName("haploid");
		NodeList  sequenceList = element.getElementsByTagName("consensus-sequence-block");
		List<String> Gls = getGls(element);
		// Print haploid 1
	
		Element haplod1 = (Element) haploids.item(0);
	    
	    SequenceData data1 = new SequenceData(sampleID);
	    data1.setLocus(haplod1.getAttribute("locus"));
	    data1.setType(haplod1.getAttribute("type"));
	    data1.setGls(Gls.get(0));
	    data1.setPhaseSet("PS1");
	    data1.setSequence(getPs1(sequenceList));
	    try {
			DatabaseUtil.insertRow(data1);
		} catch (SQLException e) {
				
				System.out.printf("Cant insert duplicate data, sample id is %s, gls is %s  in file %s \n", data1.getSampleId(), data1.getGls(), input.getName());
			}
		
	    
	    
	    
	    //Print haploid 2
	   
	    Element haplod2 = (Element) haploids.item(1);
	    SequenceData data2 = new SequenceData(sampleID);
	    data2.setLocus(haplod2.getAttribute("locus"));
	    data2.setType(haplod2.getAttribute("type"));
	    data2.setGls(Gls.get(1));
	    data2.setPhaseSet("PS2");
	    data2.setSequence(getPs2(sequenceList));
	    try {
			DatabaseUtil.insertRow(data2);
		} catch (SQLException e) {
			System.out.printf("Cant insert duplicate data, sample id is %s, gls is %s  in file %s \n", data1.getSampleId(), data1.getGls(), input.getName());
		}
	   

	}
	
	
		
	//connect multiple phase-sets by '-' indicates a gap
	private String getPs1(NodeList list){
		if(list.getLength() == 2){
			Element seq1 = (Element) list.item(0);
			return seq1.getElementsByTagName("sequence").item(0).getTextContent();
		}else{
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i< list.getLength(); i++){
				Element temp = (Element) list.item(i);
				if(temp.getAttribute("phase-set").equals("1")){
					if(sb.length() > 0){
						sb.append("-");
						sb.append(temp.getElementsByTagName("sequence").item(0).getTextContent());
					}else{
						sb.append(temp.getElementsByTagName("sequence").item(0).getTextContent());
					}
					
				}
			}
			return sb.toString();
		}
	}
	
	private String getPs2(NodeList list){
		if(list.getLength() == 2){
			Element seq1 = (Element) list.item(1);
			return seq1.getElementsByTagName("sequence").item(0).getTextContent();
		}else{
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i< list.getLength(); i++){
				Element temp = (Element) list.item(i);
				if(temp.getAttribute("phase-set").equals("2")){
					if(sb.length() > 0){
						sb.append("-");
						sb.append(temp.getElementsByTagName("sequence").item(0).getTextContent());
					}else{
						sb.append(temp.getElementsByTagName("sequence").item(0).getTextContent());
					}
					
				}
			}
			return sb.toString();
		}
	}



	/**
	 * Get gls strings.
	 * 
	 * @param e The element to abstract gls string.
	 * @return The array of gls string.
	 */
	public List<String> getGls(Element e) {
		String myString = e.getElementsByTagName("glstring").item(0).getTextContent();
		if(myString.contains("|")){
			return parseGls(myString);
		}else{
			return parseSimpleGls(myString);
		}
		
	}
	
	/**
	 * Get gls strings.
	 * 
	 * @param source The element to abstract gls string.
	 * @return The array of gls string.
	 */
	public List<String> parseGls(String source) {
			String [] ambigus = source.split("\\|");
			List<List<String>> glsList = new ArrayList<List<String>>();
			for(int i = 0 ; i < ambigus.length; i++){
				glsList.add(parseSimpleGls(ambigus[i]));
			}
			StringBuilder sbGls1 = new StringBuilder();
			StringBuilder sbGls2 = new StringBuilder();
			for(List<String> glsPair : glsList){
				sbGls1.append(glsPair.get(0));
				sbGls1.append("/");
				sbGls2.append(glsPair.get(1));
				sbGls2.append("/");
				
			}
			sbGls1.deleteCharAt(sbGls1.length()-1);
			sbGls2.deleteCharAt(sbGls2.length()-1);
			
			ArrayList<String> result = new ArrayList<String>();
			result.add(sbGls1.toString());
			result.add(sbGls2.toString());
			return result;
		
	}
	
	/**
	 * Simple gls string contains two gls which is seperated by +
	 * @param source The source gls string
	 * @return The array of gls strings
	 */
	public List<String> parseSimpleGls(String source){
		String[] gls = source.split("\\+");
		List<String> result = new ArrayList<String>();
		for(int i = 0 ; i < gls.length; i++){
			result.add(gls[i].trim());
		}
		return result;
		
	}

}

