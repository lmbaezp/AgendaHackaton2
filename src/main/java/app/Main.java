package app;

import service.AdministrarAgendaService;

public class Main {
    public static void main(String[] args) {
        Contacto c1 = new Contacto("prueba", "Prueba", "2314567");
        Contacto c2 = new Contacto("Prueba", "Prueba", "2314567");
        Agenda a1 = new Agenda();
        AdministrarAgendaService nuevo = new AdministrarAgendaService();
        a1.agregarContacto(c1);
//        a1.agregarContacto(c2);
//        System.out.println(a1.listarContactos());
        System.out.println(a1.buscarContactoPorNombre("prueba"));
    }
}
