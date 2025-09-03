package com.example.agenda;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contacto> contacts;
    private int maxSize;

    public Agenda(int maxSize) {
        this.maxSize = maxSize;
        this.contacts = new ArrayList<>();
    }

    public Agenda() {
        this(10); // tamaño por defecto
    }

    public String añadirContacto(Contacto c) {
        if (c.getNombre().isEmpty() || c.getApellido().isEmpty()) {
            return "Nombre o apellido no pueden estar vacíos.";
        }
        if (contacts.size() >= maxSize) {
            return "La agenda está llena.";
        }
        if (existeContacto(c)) {
            return "El contacto ya existe.";
        }
        contacts.add(c);
        return "Contacto agregado: " + c;
    }

    public boolean existeContacto(Contacto c) {
        return contacts.contains(c);
    }

    public String listarContactos() {
        if (contacts.isEmpty()) {
            return "No hay contactos.";
        }
        return contacts.stream()
                .sorted((a, b) -> (a.getNombre() + " " + a.getApellido())
                        .compareToIgnoreCase(b.getNombre() + " " + b.getApellido()))
                .map(Contacto::toString)
                .reduce("", (a, b) -> a + b + "\n");
    }

    public String buscaContacto(String nombre, String apellido) {
        for (Contacto c : contacts) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                return "Teléfono de " + nombre + " " + apellido + ": " + c.getTelefono();
            }
        }
        return "Contacto no encontrado.";
    }

    public String eliminarContacto(Contacto c) {
        if (contacts.remove(c)) {
            return "Eliminado correctamente.";
        }
        return "El contacto no existe.";
    }

    public String modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        for (Contacto c : contacts) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                try {
                    c.setTelefono(nuevoTelefono);
                    return "Teléfono actualizado: " + c;
                } catch (IllegalArgumentException e) {
                    return e.getMessage();
                }
            }
        }
        return "Contacto no encontrado.";
    }

    public String agendaLlena() {
        return contacts.size() >= maxSize ? "La agenda está llena." : "Aún hay espacio.";
    }

    public String espaciosLibres() {
        return "Espacios disponibles: " + (maxSize - contacts.size());
    }
}