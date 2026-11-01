package com.juniverse.patterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Prototype: crea nuevos objetos copiando uno existente, sin depender de su clase concreta.
 */
public final class PrototypeDemo {
    private static final Logger LOGGER = AppLogger.get(PrototypeDemo.class);


    private PrototypeDemo() {
    }

    /** Documento clonable (copia profunda de sus listas). */
    public static class Document {
        private final String title;
        private final List<String> sections;

        /**
         * Crea un documento.
         *
         * @param title título
         * @param sections secciones
         */
        public Document(String title, List<String> sections) {
            this.title = title;
            this.sections = new ArrayList<>(sections);
        }

        /**
         * Copia el documento; la lista de secciones no se comparte.
         *
         * @return copia independiente
         */
        public Document copy() {
            return new Document(title, sections);
        }

        /**
         * Agrega una sección.
         *
         * @param section texto de la sección
         */
        public void addSection(String section) {
            sections.add(section);
        }

        @Override
        public String toString() {
            return title + " " + sections;
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        Document template = new Document("Informe", List.of("Introducción", "Conclusiones"));
        Document copy = template.copy();
        copy.addSection("Anexo");
        LOGGER.info("Plantilla: " + template);
        LOGGER.info("Copia:     " + copy);
    }
}
