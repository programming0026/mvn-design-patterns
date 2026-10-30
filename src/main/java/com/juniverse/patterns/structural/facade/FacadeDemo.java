package com.juniverse.patterns.structural.facade;

/**
 * Facade: ofrece una interfaz simple a un subsistema complejo.
 */
public final class FacadeDemo {

    private FacadeDemo() {
    }

    /** Subsistema: inventario. */
    static class Inventory {
        boolean reserve(String product) {
            System.out.println("  Inventario: reservado " + product);
            return true;
        }
    }

    /** Subsistema: pagos. */
    static class Payments {
        boolean charge(double amount) {
            System.out.printf("  Pagos: cobrado $%.2f%n", amount);
            return true;
        }
    }

    /** Subsistema: envíos. */
    static class Shipping {
        void send(String product) {
            System.out.println("  Envíos: " + product + " en camino");
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
        System.out.println("Pedido completado: " + new OrderFacade().placeOrder("Teclado", 899.0));
    }
}
