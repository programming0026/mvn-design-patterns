package com.juniverse.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Observer: cuando un objeto cambia, notifica automáticamente a todos los que se suscribieron.
 */
public final class ObserverDemo {

    private ObserverDemo() {
    }

    /** Sujeto observable. */
    public static class Stock {
        private final String symbol;
        private final List<Consumer<Double>> observers = new ArrayList<>();
        private double price;

        /**
         * Crea la acción.
         *
         * @param symbol símbolo
         * @param price precio inicial
         */
        public Stock(String symbol, double price) {
            this.symbol = symbol;
            this.price = price;
        }

        /**
         * Suscribe un observador.
         *
         * @param observer función que recibe el nuevo precio
         */
        public void subscribe(Consumer<Double> observer) {
            observers.add(observer);
        }

        /**
         * Cambia el precio y avisa a los suscriptores.
         *
         * @param newPrice nuevo precio
         */
        public void setPrice(double newPrice) {
            this.price = newPrice;
            System.out.println(symbol + " cambió a $" + price);
            observers.forEach(o -> o.accept(price));
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        Stock acme = new Stock("ACME", 100);
        acme.subscribe(p -> System.out.println("  Pantalla: precio actual " + p));
        acme.subscribe(p -> {
            if (p < 90) {
                System.out.println("  Alerta: ¡caída por debajo de 90!");
            }
        });
        acme.setPrice(95);
        acme.setPrice(85);
    }
}
