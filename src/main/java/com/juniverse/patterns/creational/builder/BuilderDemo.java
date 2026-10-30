package com.juniverse.patterns.creational.builder;

/**
 * Builder: construye objetos complejos paso a paso; el resultado es inmutable.
 */
public final class BuilderDemo {

    private BuilderDemo() {
    }

    /** Objeto complejo e inmutable. */
    public static final class Pizza {
        private final String size;
        private final boolean cheese;
        private final boolean pepperoni;
        private final boolean mushrooms;

        private Pizza(Builder b) {
            this.size = b.size;
            this.cheese = b.cheese;
            this.pepperoni = b.pepperoni;
            this.mushrooms = b.mushrooms;
        }

        @Override
        public String toString() {
            return "Pizza " + size + " [queso=" + cheese + ", pepperoni=" + pepperoni + ", champiñones=" + mushrooms + "]";
        }

        /** Constructor paso a paso. */
        public static final class Builder {
            private final String size;
            private boolean cheese;
            private boolean pepperoni;
            private boolean mushrooms;

            /**
             * Crea el constructor con el dato obligatorio.
             *
             * @param size tamaño de la pizza
             */
            public Builder(String size) {
                this.size = size;
            }

            /**
             * Agrega queso.
             *
             * @return este constructor
             */
            public Builder cheese() {
                this.cheese = true;
                return this;
            }

            /**
             * Agrega pepperoni.
             *
             * @return este constructor
             */
            public Builder pepperoni() {
                this.pepperoni = true;
                return this;
            }

            /**
             * Agrega champiñones.
             *
             * @return este constructor
             */
            public Builder mushrooms() {
                this.mushrooms = true;
                return this;
            }

            /**
             * Construye la pizza.
             *
             * @return pizza inmutable
             */
            public Pizza build() {
                return new Pizza(this);
            }
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        System.out.println(new Pizza.Builder("grande").cheese().pepperoni().build());
        System.out.println(new Pizza.Builder("mediana").mushrooms().build());
    }
}
