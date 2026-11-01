package com.juniverse.patterns.behavioral.strategy;

import java.util.List;

import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Strategy: define una familia de algoritmos intercambiables en tiempo de ejecución.
 */
public final class StrategyDemo {
    private static final Logger LOGGER = AppLogger.get(StrategyDemo.class);


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
        LOGGER.info(String.format("Sin descuento: $%.2f", cart.total(t -> t)));
        LOGGER.info(String.format("10%% de descuento: $%.2f", cart.total(t -> t * 0.90)));
        LOGGER.info(String.format("$100 de descuento: $%.2f", cart.total(t -> t - 100)));
    }
}
