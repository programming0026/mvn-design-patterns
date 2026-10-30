package com.juniverse.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite: trata de la misma forma a objetos individuales y a grupos de objetos (estructura en árbol).
 */
public final class CompositeDemo {

    private CompositeDemo() {
    }

    /** Componente común. */
    public interface FileSystemItem {
        /**
         * Tamaño en KB.
         *
         * @return tamaño total
         */
        int size();

        /**
         * Imprime el árbol.
         *
         * @param indent sangría actual
         */
        void print(String indent);
    }

    /** Hoja: un archivo. */
    public record FileItem(String name, int size) implements FileSystemItem {
        @Override
        public void print(String indent) {
            System.out.println(indent + name + " (" + size + " KB)");
        }
    }

    /** Compuesto: una carpeta que contiene más elementos. */
    public static class Folder implements FileSystemItem {
        private final String name;
        private final List<FileSystemItem> children = new ArrayList<>();

        /**
         * Crea la carpeta.
         *
         * @param name nombre
         */
        public Folder(String name) {
            this.name = name;
        }

        /**
         * Agrega un elemento.
         *
         * @param item archivo o carpeta
         * @return esta carpeta
         */
        public Folder add(FileSystemItem item) {
            children.add(item);
            return this;
        }

        @Override
        public int size() {
            return children.stream().mapToInt(FileSystemItem::size).sum();
        }

        @Override
        public void print(String indent) {
            System.out.println(indent + name + "/ (" + size() + " KB)");
            children.forEach(c -> c.print(indent + "  "));
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        Folder raiz = new Folder("proyecto")
                .add(new FileItem("pom.xml", 4))
                .add(new Folder("src").add(new FileItem("Main.java", 2)).add(new FileItem("App.java", 6)));
        raiz.print("");
    }
}
