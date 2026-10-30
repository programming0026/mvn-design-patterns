package com.juniverse.patterns.behavioral.strategy;

import java.util.List;

/**
 * Strategy: define una familia de algoritmos intercambiables en tiempo de ejecución.
 */
public final class StrategyDemo {

    private StrategyDemo() {
    }

    /** Estrategia de descuento. */
    @FunctionalInterface
    public interface Discount {
        /**
         * Aplica el descuento.
         *
         * @param total importe original
         * @return importe final
         */
        double apply(double total);
    }

    /** Contexto: usa la estrategia que se le entregue. */
    public static class Cart {
        private final List<Double> prices;

        /**
         * Crea el carrito.
         *
         * @param prices precios de los artículos
         */
        public Cart(List<Double> prices) {
            this.prices = prices;
        }

        /**
         * Calcula el total con la estrategia elegida.
         *
         * @param discount estrategia de descuento
         * @return total a pagar
         */
        public double total(Discount discount) {
            return discount.apply(prices.stream().mapToDouble(Double::doubleValue).sum());
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        Cart cart = new Cart(List.of(200.0, 350.0, 450.0));
        System.out.printf("Sin descuento: $%.2f%n", cart.total(t -> t));
        System.out.printf("10%% de descuento: $%.2f%n", cart.total(t -> t * 0.90));
        System.out.printf("$100 de descuento: $%.2f%n", cart.total(t -> t - 100));
    }
}
