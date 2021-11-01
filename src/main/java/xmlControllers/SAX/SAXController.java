package xmlControllers.SAX;

import model.Departamento;
import model.Fabrica;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXController {
    private String uri;
    private List<Departamento> departamentos;
    private static SAXController instance;

    private SAXController (String uri) {
        this.uri = uri;
        this.departamentos = new ArrayList<>();
    }

    public static SAXController getInstance(String uri) {
        if (instance == null) {
            instance = new SAXController(uri);
        }
        return instance;
    }

    public void loadData() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        SAXDepartamentoHandler handler = new SAXDepartamentoHandler();
        saxParser.parse(this.uri, handler);
        this.departamentos = handler.getDepartamentos();
    }

    public List<Departamento> getDepartamentos () {
        return this.departamentos;
    }
}
