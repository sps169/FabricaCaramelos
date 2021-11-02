import model.Color;
import model.Departamento;
import model.Gnomo;
import org.xml.sax.SAXException;
import xmlControllers.DOM.DOMController;
import xmlControllers.SAX.SAXController;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Christmas!");
        String uri = "data/FabricaCaramelos.xml";
        String uriMod = "data/FabricaCaramelosMod.xml";
        //saxReading(uri);
        //domReading(uri);
        domModifying(uriMod);
        domReading(uriMod);
    }

    public static void saxReading(String uri) {
        SAXController sax =  SAXController.getInstance(uri);
        try {
            sax.loadData();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        sax.getDepartamentos().forEach(System.out::println);
    }

    public static void domReading(String uri) {
        DOMController dom = DOMController.getInstance(uri);
        try {
            dom.loadData();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        dom.getDepartamentos().forEach(System.out::println);
    }

    private static void domModifying(String uriMod) {
        List<Gnomo> gnomos = new ArrayList<>();
        gnomos.add(new Gnomo("g17", "Faker", Color.azul, 167));
        gnomos.add(new Gnomo("g18", "Choumaker", Color.blanco, 168));
        gnomos.add(new Gnomo("g19", "Pocho", Color.negro, 10));
        Departamento departamento = new Departamento("5", "Investigar caramelos", gnomos);
        DOMController dom = DOMController.getInstance(uriMod);
        try {
            dom.loadData();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        dom.insertarDepartamento(departamento);
        Gnomo gnomo = new Gnomo("g20", "Ezekiel", Color.blanco,101);
        dom.insertarGnomo(gnomo, "5");
        dom.updateGnomo("g20");
        try {
            dom.writeXMLFile(uriMod);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
