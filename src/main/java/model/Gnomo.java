package model;

public class Gnomo {
    private String id;
    private String nombre;
    private Color color;
    private int iq;

    public Gnomo(String id, String nombre, Color color, int iq) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.iq = iq;
    }

    public Gnomo () {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    @Override
    public String toString() {
        return "Gnomo{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", color=" + color +
                ", iq=" + iq +
                '}';
    }
}
