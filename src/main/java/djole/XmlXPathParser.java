package djole;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlXPathParser {

	public XmlXPathParser() {

	}

	public Nutrition process(String xmlFilePath)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		File file = new File(xmlFilePath);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		document.getDocumentElement().normalize();

		XPath xPath = XPathFactory.newInstance().newXPath();

		String dailyValuesTargetXPath = "/nutrition/daily-values";
		String foodTargetXPath = "/nutrition/food";

		NodeList dailyValuesNodeList = (NodeList) xPath.compile(dailyValuesTargetXPath).evaluate(document,
				XPathConstants.NODESET);
		NodeList foodNodeList = (NodeList) xPath.compile(foodTargetXPath).evaluate(document, XPathConstants.NODESET);
		DailyValues dailyValues = null;
		List<Food> foodList = new LinkedList<>();

		for (int i = 0; i < dailyValuesNodeList.getLength(); i++) {
			Node node = dailyValuesNodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element xmlElement = (Element) node;

				Integer totalFat = Integer
						.parseInt(xmlElement.getElementsByTagName("total-fat").item(0).getTextContent());
				String totalFatUnits = xmlElement.getElementsByTagName("total-fat").item(0).getAttributes().item(0)
						.getTextContent();

				Integer saturatedFat = Integer
						.parseInt(xmlElement.getElementsByTagName("saturated-fat").item(0).getTextContent());
				String saturatedFatUnits = xmlElement.getElementsByTagName("saturated-fat").item(0).getAttributes()
						.item(0).getTextContent();

				Integer cholesterol = Integer
						.parseInt(xmlElement.getElementsByTagName("cholesterol").item(0).getTextContent());
				String cholesterolUnits = xmlElement.getElementsByTagName("cholesterol").item(0).getAttributes().item(0)
						.getTextContent();

				Integer sodium = Integer.parseInt(xmlElement.getElementsByTagName("sodium").item(0).getTextContent());
				String sodiumUnits = xmlElement.getElementsByTagName("sodium").item(0).getAttributes().item(0)
						.getTextContent();

				Integer carb = Integer.parseInt(xmlElement.getElementsByTagName("carb").item(0).getTextContent());
				String carbUnits = xmlElement.getElementsByTagName("carb").item(0).getAttributes().item(0)
						.getTextContent();

				Integer fiber = Integer.parseInt(xmlElement.getElementsByTagName("fiber").item(0).getTextContent());
				String fiberUnits = xmlElement.getElementsByTagName("fiber").item(0).getAttributes().item(0)
						.getTextContent();

				Integer protein = Integer.parseInt(xmlElement.getElementsByTagName("protein").item(0).getTextContent());
				String proteinUnits = xmlElement.getElementsByTagName("protein").item(0).getAttributes().item(0)
						.getTextContent();

				dailyValues = new DailyValues(totalFat, totalFatUnits, saturatedFat, saturatedFatUnits, cholesterol,
						cholesterolUnits, sodium, sodiumUnits, carb, carbUnits, fiber, fiberUnits, protein,
						proteinUnits);

			}

		}

		for (int i = 0; i < foodNodeList.getLength(); i++) {
			Node node = foodNodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element xmlElement = (Element) node;
				String name = xmlElement.getElementsByTagName("name").item(0).getTextContent();
				Double serving = Double
						.parseDouble(xmlElement.getElementsByTagName("serving").item(0).getTextContent());
				String servingUnits = xmlElement.getElementsByTagName("serving").item(0).getAttributes().item(0)
						.getTextContent();
				Integer caloriesTotal = Integer.parseInt(
						xmlElement.getElementsByTagName("calories").item(0).getAttributes().item(1).getTextContent());
				Integer caloriesFat = Integer.parseInt(
						xmlElement.getElementsByTagName("calories").item(0).getAttributes().item(0).getTextContent());
				Double totalFat = Double
						.parseDouble(xmlElement.getElementsByTagName("total-fat").item(0).getTextContent());
				Double saturatedFat = Double
						.parseDouble(xmlElement.getElementsByTagName("saturated-fat").item(0).getTextContent());
				Integer cholesterol = Integer
						.parseInt(xmlElement.getElementsByTagName("cholesterol").item(0).getTextContent());
				Integer sodium = Integer.parseInt(xmlElement.getElementsByTagName("sodium").item(0).getTextContent());
				Integer carb = Integer.parseInt(xmlElement.getElementsByTagName("carb").item(0).getTextContent());
				Integer fiber = Integer.parseInt(xmlElement.getElementsByTagName("fiber").item(0).getTextContent());
				Integer protein = Integer.parseInt(xmlElement.getElementsByTagName("protein").item(0).getTextContent());
				Integer vitaminA = Integer.parseInt(xmlElement.getElementsByTagName("a").item(0).getTextContent());
				Integer vitaminC = Integer.parseInt(xmlElement.getElementsByTagName("c").item(0).getTextContent());
				Integer mineralCa = Integer.parseInt(xmlElement.getElementsByTagName("ca").item(0).getTextContent());
				Integer mineralFe = Integer.parseInt(xmlElement.getElementsByTagName("fe").item(0).getTextContent());

				Food food = new Food(name, serving, servingUnits, caloriesTotal, caloriesFat, totalFat, saturatedFat,
						cholesterol, sodium, carb, fiber, protein, vitaminA, vitaminC, mineralCa, mineralFe);
				foodList.add(food);

			}
		}
		return new Nutrition(dailyValues, foodList);
	}

}
