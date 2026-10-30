package com.juniverse.patterns.behavioral.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator: recorre los elementos de una colección sin exponer su estructura interna.
 */
public final class IteratorDemo {

    private IteratorDemo() {
    }

    /** Colección propia: un rango de enteros que se puede usar en un for-each. */
    public static class Range implements Iterable<Integer> {
        private final int from;
        private final int to;

        /**
         * Crea el rango.
         *
         * @param from primer valor (inclusive)
         * @param to último valor (exclusivo)
         */
        public Range(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<>() {
                private int current = from;

                @Override
                public boolean hasNext() {
                    return current < to;
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return current++;
                }
            };
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        StringBuilder sb = new StringBuilder();
        for (int n : new Range(1, 6)) {
            sb.append(n).append(' ');
        }
        System.out.println("Recorrido: " + sb.toString().trim());
    }
}
