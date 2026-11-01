package com.juniverse.patterns.behavioral.chainofresponsibility;


import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Chain of Responsibility: la petición pasa por una cadena de manejadores hasta que uno la atiende.
 */
public final class ChainOfResponsibilityDemo {
    private static final Logger LOGGER = AppLogger.get(ChainOfResponsibilityDemo.class);


    private ChainOfResponsibilityDemo() {
    }

    /** Manejador de aprobaciones de gasto. */
    public abstract static class Approver {
        private Approver next;

        /**
         * Enlaza el siguiente manejador.
         *
         * @param next siguiente en la cadena
         * @return el siguiente (para encadenar llamadas)
         */
        public Approver then(Approver next) {
            this.next = next;
            return next;
        }

        /**
         * Aprueba la compra o la pasa al siguiente.
         *
         * @param amount importe
         * @return quién la aprobó
         */
        public String approve(double amount) {
            if (canApprove(amount)) {
                return name() + " aprobó $" + amount;
            }
            return next != null ? next.approve(amount) : "Nadie puede aprobar $" + amount;
        }

        protected abstract boolean canApprove(double amount);

        protected abstract String name();
    }

    /** Aprueba hasta $1,000. */
    public static class Supervisor extends Approver {
        @Override
        protected boolean canApprove(double amount) {
            return amount <= 1000;
        }

        @Override
        protected String name() {
            return "Supervisor";
        }
    }

    /** Aprueba hasta $10,000. */
    public static class Manager extends Approver {
        @Override
        protected boolean canApprove(double amount) {
            return amount <= 10_000;
        }

        @Override
        protected String name() {
            return "Gerente";
        }
    }

    /** Aprueba hasta $100,000. */
    public static class Director extends Approver {
        @Override
        protected boolean canApprove(double amount) {
            return amount <= 100_000;
        }

        @Override
        protected String name() {
            return "Director";
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        Approver chain = new Supervisor();
        chain.then(new Manager()).then(new Director());
        for (double amount : new double[]{500, 7_500, 50_000, 250_000}) {
            LOGGER.info(chain.approve(amount));
        }
    }
}
