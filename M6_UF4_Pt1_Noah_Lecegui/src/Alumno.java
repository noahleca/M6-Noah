import java.io.Serializable;

public class Alumno implements Serializable {
    private int codigo;
    private String nombre;
    private int edad;
    private double mediaNotas;

    public Alumno() {
    }

    public Alumno(int codigo, String nombre, int edad, double mediaNotas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.mediaNotas = mediaNotas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public void setMediaNotas(double mediaNotas) {
        this.mediaNotas = mediaNotas;
    }
}
