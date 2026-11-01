package com.juniverse.patterns.behavioral.memento;

import java.util.ArrayDeque;
import java.util.Deque;

import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Memento: guarda y restaura el estado anterior de un objeto sin romper su encapsulamiento.
 */
public final class MementoDemo {
    private static final Logger LOGGER = AppLogger.get(MementoDemo.class);


    private MementoDemo() {
    }

    /** Originador: un documento. */
    public static class Document {
        private String content = "";

        /**
         * Cambia el contenido.
         *
         * @param content nuevo texto
         */
        public void write(String content) {
            this.content = content;
        }

        /**
         * Contenido actual.
         *
         * @return texto
         */
        public String content() {
            return content;
        }

        /**
         * Guarda el estado actual.
         *
         * @return recuerdo inmutable
         */
        public Memento save() {
            return new Memento(content);
        }

        /**
         * Restaura un estado guardado.
         *
         * @param memento recuerdo
         */
        public void restore(Memento memento) {
            this.content = memento.state();
        }
    }

    /** Memento: estado inmutable. */
    public record Memento(String state) {
    }

    /** Ejecuta la demostración. */
    public static void run() {
        Document doc = new Document();
        Deque<Memento> history = new ArrayDeque<>();
        doc.write("versión 1");
        history.push(doc.save());
        doc.write("versión 2");
        history.push(doc.save());
        doc.write("versión 3 (con errores)");
        LOGGER.info("Actual: " + doc.content());
        doc.restore(history.pop());
        LOGGER.info("Restaurado: " + doc.content());
    }
}
