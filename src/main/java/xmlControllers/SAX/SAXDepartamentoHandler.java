package xmlControllers.SAX;

import model.Color;
import model.Departamento;
import model.Gnomo;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXDepartamentoHandler extends DefaultHandler {
    private boolean hasNombre = false;
    private boolean hasGnomoNombre = false;
    private boolean hasGnomoColor = false;
    private boolean hasGnomoIq = false;

    private List<Departamento> departamentos = null;
    private Departamento departamento = null;
    private Gnomo gnomo = null;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (departamentos == null) {
            departamentos = new ArrayList<>();
        }
        if (qName.equals("departamento")) {
            departamento = new Departamento();
            departamento.setId(atts.getValue("id"));
        } else if (qName.equals("nombre_departamento")) {
            hasNombre = true;
        } else if (qName.equals("gnomos")) {
            if (departamento.getGnomos() == null) {
                departamento.setGnomos(new ArrayList<>());
            }
        } else if (qName.equals("gnomo")) {
            gnomo = new Gnomo();
            gnomo.setId(atts.getValue("id"));
        } else if (qName.equals("nombre")) {
            hasGnomoNombre = true;
        } else if (qName.equals("color")) {
            hasGnomoColor = true;
        } else if (qName.equals("iq")) {
            hasGnomoIq = true;
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("departamento")) {
            departamentos.add(departamento);
        } else if (qName.equals("gnomo")) {
            departamento.getGnomos().add(gnomo);
        }
    }

    public void characters(char ch[], int start, int length) {
        if (hasNombre) {
            departamento.setNombreDepartamento(new String(ch, start, length));
            hasNombre = false;
        }
        else if (hasGnomoNombre) {
            gnomo.setNombre(new String(ch, start, length));
            hasGnomoNombre = false;
        }
        else if (hasGnomoColor) {
            gnomo.setColor(Color.valueOf(new String(ch, start, length)));
            hasGnomoColor = false;
        }
        else if (hasGnomoIq) {
            gnomo.setIq(Integer.parseInt(new String(ch, start, length)));
            hasGnomoIq = false;
        }
    }

    public List<Departamento> getDepartamentos () {
        return this.departamentos;
    }

}
