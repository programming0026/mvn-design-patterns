package com.juniverse.patterns.structural.decorator;


import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Decorator: añade responsabilidades a un objeto de forma dinámica envolviéndolo, sin usar herencia.
 */
public final class DecoratorDemo {
    private static final Logger LOGGER = AppLogger.get(DecoratorDemo.class);


    private DecoratorDemo() {
    }

    /** Componente. */
    public interface Coffee {
        /**
         * Descripción.
         *
         * @return texto
         */
        String description();

        /**
         * Precio.
         *
         * @return precio en pesos
         */
        double cost();
    }

    /** Componente concreto. */
    public static class SimpleCoffee implements Coffee {
        @Override
        public String description() {
            return "Café";
        }

        @Override
        public double cost() {
            return 30;
        }
    }

    /** Decorador base: envuelve otro café. */
    public abstract static class CoffeeDecorator implements Coffee {
        protected final Coffee inner;

        /**
         * Crea el decorador.
         *
         * @param inner café envuelto
         */
        protected CoffeeDecorator(Coffee inner) {
            this.inner = inner;
        }
    }

    /** Decorador concreto. */
    public static class Milk extends CoffeeDecorator {
        /**
         * Crea el decorador.
         *
         * @param inner café envuelto
         */
        public Milk(Coffee inner) {
            super(inner);
        }

        @Override
        public String description() {
            return inner.description() + " + leche";
        }

        @Override
        public double cost() {
            return inner.cost() + 8;
        }
    }

    /** Decorador concreto. */
    public static class Sugar extends CoffeeDecorator {
        /**
         * Crea el decorador.
         *
         * @param inner café envuelto
         */
        public Sugar(Coffee inner) {
            super(inner);
        }

        @Override
        public String description() {
            return inner.description() + " + azúcar";
        }

        @Override
        public double cost() {
            return inner.cost() + 2;
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        Coffee pedido = new Sugar(new Milk(new SimpleCoffee()));
        LOGGER.info(String.format("%s = $%.2f", pedido.description(), pedido.cost()));
    }
}
