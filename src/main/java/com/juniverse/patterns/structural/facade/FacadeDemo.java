package com.juniverse.patterns.structural.facade;


import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Facade: ofrece una interfaz simple a un subsistema complejo.
 */
public final class FacadeDemo {
    private static final Logger LOGGER = AppLogger.get(FacadeDemo.class);


    private FacadeDemo() {
    }

    /** Subsistema: inventario. */
    static class Inventory {
        boolean reserve(String product) {
            LOGGER.info("  Inventario: reservado " + product);
            return true;
        }
    }

    /** Subsistema: pagos. */
    static class Payments {
        boolean charge(double amount) {
            LOGGER.info(String.format("  Pagos: cobrado $%.2f", amount));
            return true;
        }
    }

    /** Subsistema: envíos. */
    static class Shipping {
        void send(String product) {
            LOGGER.info("  Envíos: " + product + " en camino");
        }
    }

    /** Fachada: un único método que coordina los tres subsistemas. */
    public static class OrderFacade {
        private final Inventory inventory = new Inventory();
        private final Payments payments = new Payments();
        private final Shipping shipping = new Shipping();

        /**
         * Realiza un pedido completo.
         *
         * @param product producto
         * @param amount importe
         * @return {@code true} si se completó
         */
        public boolean placeOrder(String product, double amount) {
            if (inventory.reserve(product) && payments.charge(amount)) {
                shipping.send(product);
                return true;
            }
            return false;
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        LOGGER.info("Pedido completado: " + new OrderFacade().placeOrder("Teclado", 899.0));
    }
}
