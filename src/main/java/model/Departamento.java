package model;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String id;
    private String nombreDepartamento;
    private List<Gnomo> gnomos;

    public Departamento(String id, String nombreDepartamento, List<Gnomo> gnomos) {
        this.id = id;
        this.nombreDepartamento = nombreDepartamento;
        this.gnomos = gnomos;
    }

    public Departamento(String id, String nombreDepartamento) {
        this.id = id;
        this.nombreDepartamento = nombreDepartamento;
        this.gnomos = new ArrayList<>();
    }

    public Departamento() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public List<Gnomo> getGnomos() {
        return gnomos;
    }

    public void setGnomos(List<Gnomo> gnomos) {
        this.gnomos = gnomos;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id='" + id + '\'' +
                ", nombreDepartamento='" + nombreDepartamento + '\'' +
                ", gnomos=" + gnomos +
                '}';
    }
}
