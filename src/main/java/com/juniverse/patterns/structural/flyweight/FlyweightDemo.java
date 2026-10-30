package com.juniverse.patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight: comparte objetos inmutables para ahorrar memoria cuando hay muchísimos elementos parecidos.
 */
public final class FlyweightDemo {

    private FlyweightDemo() {
    }

    /** Estado intrínseco compartido: el tipo de árbol. */
    public record TreeType(String name, String color) {
    }

    /** Fábrica que reutiliza los tipos ya creados. */
    public static class TreeFactory {
        private final Map<String, TreeType> cache = new HashMap<>();

        /**
         * Obtiene (o crea una sola vez) el tipo de árbol.
         *
         * @param name nombre
         * @param color color
         * @return tipo compartido
         */
        public TreeType get(String name, String color) {
            return cache.computeIfAbsent(name + color, k -> new TreeType(name, color));
        }

        /**
         * Cuántos tipos distintos existen.
         *
         * @return cantidad de tipos en memoria
         */
        public int createdTypes() {
            return cache.size();
        }
    }

    /** Estado extrínseco: la posición es propia de cada árbol. */
    public record Tree(int x, int y, TreeType type) {
    }

    /** Ejecuta la demostración. */
    public static void run() {
        TreeFactory factory = new TreeFactory();
        Tree[] bosque = new Tree[1000];
        for (int i = 0; i < bosque.length; i++) {
            bosque[i] = new Tree(i, i * 2, i % 2 == 0 ? factory.get("Pino", "verde") : factory.get("Roble", "marrón"));
        }
        System.out.println("Árboles: " + bosque.length + ", tipos en memoria: " + factory.createdTypes());
    }
}
