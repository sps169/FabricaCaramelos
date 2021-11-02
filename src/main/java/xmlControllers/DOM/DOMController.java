package xmlControllers.DOM;

import model.Color;
import model.Departamento;
import model.Gnomo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Departamento> getDepartamentos() {
        List<Departamento> departamentos = new ArrayList<>();
        NodeList nodes = data.getElementsByTagName("departamento");
        for (int i = 0; i < nodes.getLength(); i++) {
            departamentos.add(getDepartamento(nodes.item(i)));
        }
        return departamentos;
    }

    private Departamento getDepartamento(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("departamento")) {
            Element element = (Element) node;
            return new Departamento(element.getAttribute("id"),
                    getTagValue("nombre_departamento", element),
                    getGnomos(element.getElementsByTagName("gnomos").item(0)));
        }
        else return null;
    }

    private String getTagValue (String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        if(node != null)
            return node.getNodeValue();
        else return null;
    }

    private List<Gnomo> getGnomos (Node node) {
        List<Gnomo> gnomos = new ArrayList<>();
        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("gnomos")) {
            NodeList nodosGnomo = ((Element) node).getElementsByTagName("gnomo");
            for (int i = 0; i < nodosGnomo.getLength(); i++) {
                gnomos.add(getGnomo(nodosGnomo.item(i)));
            }
        }
        return gnomos;
    }

    private Gnomo getGnomo (Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("gnomo")) {
            Element element = (Element) node;
            return new Gnomo(element.getAttribute("id"),
                    getTagValue("nombre", element),
                    Color.valueOf(getTagValue("color", element)),
                    Integer.parseInt(getTagValue("iq", element)));
        }
        else return null;
    }

    private Node createElement (String tag, String value) {
        Element node = data.createElement(tag);
        node.appendChild(data.createTextNode(value));
        return node;
    }

    private Node createElementGnomos (List<Gnomo> gnomos) {
        Element node = data.createElement("gnomos");
        gnomos.stream().forEach(s -> {
            Node gnomeNode = createElementGnomo(s);
            node.appendChild(gnomeNode);
        });
        return node;
    }
    public void insertarDepartamento(Departamento departamento) {
        Element departamentoElement = this.data.createElement("departamento");
        departamentoElement.setAttribute("id", departamento.getId());
        departamentoElement.appendChild(createElement("nombre_departamento", departamento.getNombreDepartamento()));
        departamentoElement.appendChild(createElementGnomos(departamento.getGnomos()));
        this.data.getDocumentElement().appendChild(departamentoElement);
    }

    public void updateGnomo(String idGnomo) {
        NodeList list = this.data.getElementsByTagName("gnomo");
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(idGnomo)) {
                ((Element)list.item(i)).getElementsByTagName("iq").item(0).getFirstChild().setNodeValue("0");
            }
        }
    }

    public void deleteElement(String idElement, String tag) {
        NodeList list = this.data.getElementsByTagName(tag);
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(idElement)) {
                list.item(i).getParentNode().removeChild(list.item(i));
            }
        }
    }

    public void insertarGnomo(Gnomo gnomo, String idDepartamento) {
        NodeList list = this.data.getElementsByTagName("departamento");
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeType() == Node.ELEMENT_NODE){
                if (((Element) list.item(i)).getAttribute("id").equals(idDepartamento)) {
                    Node gnomeNode = createElementGnomo(gnomo);
                    ((Element) list.item(i)).getElementsByTagName("gnomos").item(0).appendChild(gnomeNode);
                }
            }
        }
    }

    private Node createElementGnomo(Gnomo gnomo) {
        Element gnomeNode = data.createElement("gnomo");
        gnomeNode.setAttribute("id", gnomo.getId());
        gnomeNode.appendChild(createElement("nombre", gnomo.getNombre()));
        gnomeNode.appendChild(createElement("color", gnomo.getColor().name()));
        gnomeNode.appendChild(createElement("iq", gnomo.getIq() + ""));
        return gnomeNode;
    }

    private Transformer getPreprocessor() throws TransformerConfigurationException {
        TransformerFactory trFactory = TransformerFactory.newInstance();
        Transformer tr = trFactory.newTransformer();
        tr.setOutputProperty(OutputKeys.INDENT, "yes");
        return tr;
    }

    public void writeXMLFile(String uri) throws TransformerException {
        Transformer transformer = getPreprocessor();
        DOMSource source = new DOMSource(this.data);
        StreamResult file = new StreamResult(new File(uri));
        transformer.transform(source, file);
    }
}
