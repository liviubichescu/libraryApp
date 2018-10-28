package ro.ubb.homeWorkLibrary.Repository;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ro.ubb.homeWorkLibrary.Domain.Book;
import ro.ubb.homeWorkLibrary.Exceptions.ValidatorException;
import ro.ubb.homeWorkLibrary.Validators.Validator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class XmlFileRepository extends InMemoryRepository<Long, Book> {

    private String fileName;

    public XmlFileRepository(Validator<Book> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadBooks();
    }


    @Override
    public Optional<Book> save(Book entity) throws ValidatorException {
        Optional<Book> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToXml(entity);
        return Optional.empty();
    }

    private void saveToXml(Book book) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);

            Element root = document.getDocumentElement();
            root.appendChild(createBookElement(document, book));


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(new DOMSource(document),
                    new StreamResult(new File(fileName)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Node createBookElement(Document document, Book book) {
        Element bookElement = document.createElement("book");
        bookElement.setAttribute("id", String.valueOf(book.getId()));
        appendTagWithText(document, bookElement, "id",  String.valueOf(book.getId()));
        appendTagWithText(document, bookElement, "title", book.getTitle());
        appendTagWithText(document, bookElement, "author", book.getAuthor());
        appendTagWithText(document, bookElement, "publishHouse", book.getPublishHouse());
        appendTagWithText(document, bookElement, "year",  String.valueOf(book.getRelesedYear()));
        appendTagWithText(document, bookElement, "price", String.valueOf(book.getPrice()));

        return bookElement;
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
            NodeList bookNodes = root.getChildNodes();
            for (int i = 0; i < bookNodes.getLength(); i++) {
                Node bookNode = bookNodes.item(i);
                if (bookNode instanceof Element) {
                    Book book = createBookFromElement((Element) bookNode);
                    super.save(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Book createBookFromElement(Element bookElement) {
        Book book = new Book();

        String id = getTextFromTag(bookElement, "id");
        book.setId(Long.parseLong(id != null ? id : "0"));

        String title = getTextFromTag(bookElement, "title");
        book.setTitle(title);

        String author = getTextFromTag(bookElement, "author");
        book.setAuthor(author);

        String publishHouse = getTextFromTag(bookElement, "publishHouse");
        book.setPublishHouse(publishHouse);

        String year = getTextFromTag(bookElement, "year");
        book.setRelesedYear(Integer.parseInt(year != null ? year : "0"));

        String price = getTextFromTag(bookElement, "price");
        book.setPrice(Float.parseFloat(price != null ? price : "0"));

        return book;
    }


    private String getTextFromTag(Element bookElement, String tagName) {
        NodeList children = bookElement.getElementsByTagName(tagName);
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i) instanceof Element) {
                Element element = (Element) children.item(i);
                return element.getTextContent();
            }
        }
        return null;
    }


    @Override
    public Optional<Book> delete(Long id){
        Optional<Book> optionalBook = super.delete(id);
        if (optionalBook.isPresent()){
            deleteNode(id);
        }
        return optionalBook;
    }


    private void deleteNode(Long id){
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
            Element root = document.getDocumentElement();
            NodeList bookNodes = root.getChildNodes();
            for (int i=0; i<bookNodes.getLength(); i++){
                Node bookNode = bookNodes.item(i);
                if (bookNode instanceof Element){
                    Node artibuteFirst =  bookNode.getAttributes().item(0);
                    if (artibuteFirst != null && Long.parseLong(artibuteFirst.getTextContent())==id){
                        bookNode.getParentNode().removeChild(bookNode);
                        i = bookNodes.getLength();
                    }
                }
            }


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(new DOMSource(document),
                    new StreamResult(new File(fileName)));

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }

}
