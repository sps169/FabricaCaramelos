package model;

import java.util.ArrayList;
import java.util.List;

public class Fabrica {
    private String id;
    private List<Departamento> departamentos;

    public Fabrica(String id, List<Departamento> departamentos) {
        this.id = id;
        this.departamentos = departamentos;
    }

    public Fabrica(String id) {
        this.id = id;
        this.departamentos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
}
