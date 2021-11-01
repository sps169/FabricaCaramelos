import org.xml.sax.SAXException;
import xmlControllers.SAX.SAXController;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Christmas!");
        SAXController sax =  SAXController.getInstance("data/FabricaCaramelos.xml");
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
}
