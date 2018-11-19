package ro.ubb.homeWorkLibrary.repository.xmlRepository;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ro.ubb.homeWorkLibrary.domain.Sales;
import ro.ubb.homeWorkLibrary.repository.inMemoryRepo.InMemoryRepository;
import ro.ubb.homeWorkLibrary.validators.Validator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class XmlSalesFileRepository extends InMemoryRepository<String, Sales> {

    private String fileName;

    public XmlSalesFileRepository(Validator<Sales> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadBooks();
    }


    @Override
    public Optional<Sales> save(Sales entity) {
        Optional<Sales> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToXml(entity);
        return Optional.empty();
    }



    private void saveToXml(Sales sales) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);

            Element root = document.getDocumentElement();
            root.appendChild(createSaleElement(document, sales));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(new DOMSource(document),
                    new StreamResult(new File(fileName)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Node createSaleElement(Document document, Sales sales) {
        Element saleElement = document.createElement("sale");
        saleElement.setAttribute("id", String.valueOf(sales.getId()));
        appendTagWithText(document, saleElement, "id",  String.valueOf(sales.getId()));
        appendTagWithText(document, saleElement, "bookID", String.valueOf(sales.getBookIdSale()));
        appendTagWithText(document, saleElement, "clientID", String.valueOf(sales.getClientIdSale()));

        return saleElement;
    }

    private void appendTagWithText(Document document, Element parent, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }


    private void loadBooks() {

        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
            Element root = document.getDocumentElement();
            NodeList saleNodes = root.getChildNodes();
            for (int i = 0; i < saleNodes.getLength(); i++) {
                Node saleNode = saleNodes.item(i);
                if (saleNode instanceof Element) {
                    Sales sales = createSaleFromElement((Element) saleNode);
                    super.save(sales);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Sales createSaleFromElement(Element saleElement) {
        Sales sale = new Sales();

        String id = getTextFromTag(saleElement, "id");
        sale.setId(String.valueOf(id != null ? id : "0"));

        String bookID = getTextFromTag(saleElement, "bookID");
        sale.setBookIdSale(Long.valueOf(bookID != null ? bookID : "0"));

        String clientID = getTextFromTag(saleElement, "clientID");
        sale.setClientIdSale(Long.valueOf((clientID != null ? clientID : "0")));

        return sale;
    }


    private String getTextFromTag(Element saleElement, String tagName) {
        NodeList children = saleElement.getElementsByTagName(tagName);
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i) instanceof Element) {
                Element element = (Element) children.item(i);
                return element.getTextContent();
            }
        }
        return null;
    }


    @Override
    public Optional<Sales> delete(String id){
        Optional<Sales> optionalSale = super.delete(id);
        if (optionalSale.isPresent()){
            deleteNode(id);
        }
        return optionalSale;
    }


    private void deleteNode(String id){
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
            Element root = document.getDocumentElement();
            NodeList saleNodes = root.getChildNodes();
            for (int i=0; i<saleNodes.getLength(); i++){
                Node saleNode = saleNodes.item(i);
                if (saleNode instanceof Element){
                    Node artibuteFirst =  saleNode.getAttributes().item(0);
                    if (artibuteFirst != null && (artibuteFirst.getTextContent()).equals(id)){
                        saleNode.getParentNode().removeChild(saleNode);
                        i = saleNodes.getLength();
                    }
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(new DOMSource(document),
                    new StreamResult(new File(fileName)));

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        }


    }

}
