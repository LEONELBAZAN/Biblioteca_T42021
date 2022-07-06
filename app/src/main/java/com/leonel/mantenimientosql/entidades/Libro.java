package com.leonel.mantenimientosql.entidades;

public class Libro {

    private int id;
    private String nombre;
    private String autor;
    private String editorial;
    private double precio;


    public Libro(String nombre, String autor, String editorial, double precio) {
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.precio = precio;
    }

    public Libro(int id, String nombre, String autor, String editorial, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
