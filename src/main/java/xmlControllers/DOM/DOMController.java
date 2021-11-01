package xmlControllers.DOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMController {
    private static DOMController instance;
    private String uri;
    private Document data;

    public static DOMController getInstance(String uri) {
        if (instance == null) {
            instance = new DOMController(uri);
        }
        return instance;
    }

    private DOMController (String uri) {
        this.uri = uri;
    }

    public void initData() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        this.data = docBuilder.newDocument();
        Element root = this.data.createElement("fabrica");
        root.setAttribute("id", "1");
        this.data.appendChild(root);
    }

    public void loadData() throws ParserConfigurationException, IOException, SAXException {
        File xml = new File(this.uri);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        this.data = docBuilder.parse(xml);
        this.data.getDocumentElement().normalize();
    }


}
