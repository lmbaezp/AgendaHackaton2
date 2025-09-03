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

    public boolean addContact(Contacto contact) {
        if (contacts.size() >= maxSize || contacts.contains(contact)) {
            return false;
        }
        return contacts.add(contact);
    }

    public List<Contacto> getContacts() {
        return contacts;
    }
    public boolean exists(String nombre, String apellido) {
        return contacts.contains(new Contacto(nombre, apellido, "1234567"));
        // el teléfono aquí es irrelevante, equals() solo compara nombre y apellido
    }

    public Contacto search(String nombre, String apellido) {
        return contacts.stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido))
                .findFirst()
                .orElse(null);
    }

    public boolean delete(String nombre, String apellido) {
        return contacts.removeIf(c -> c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido));
    }

    public boolean isFull() {
        return contacts.size() >= maxSize;
    }

    public int freeSpace() {
        return maxSize - contacts.size();
    }
}