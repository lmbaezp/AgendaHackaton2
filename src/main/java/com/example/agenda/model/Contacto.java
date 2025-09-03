package com.example.agenda.model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Contacto {
    private final String nombre;
    private final String apellido;
    private String telefono;
    private static final Pattern verificacionfCel = Pattern.compile("^[0-9]{7,10}$");

    public Contacto(String nombre, String apellido, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("Ingresa un nombre.");
        if (apellido == null || apellido.trim().isEmpty()) throw new IllegalArgumentException("Ingresa un apellido.");
        if (telefono == null || !verificacionfCel.matcher(telefono).matches()) throw new IllegalArgumentException("Teléfono no válido.");
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }

    public void setTelefono(String nuevo) {
        if (nuevo == null || !verificacionfCel.matcher(nuevo).matches()) throw new IllegalArgumentException("Teléfono no válido.");
        this.telefono = nuevo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacto)) return false;
        Contacto c = (Contacto) o;
        return nombre.equalsIgnoreCase(c.nombre) && apellido.equalsIgnoreCase(c.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }
}