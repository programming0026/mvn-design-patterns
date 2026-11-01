package com.juniverse.patterns.behavioral.templatemethod;


import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Template Method: la clase base fija el esqueleto de un algoritmo y las subclases personalizan algunos pasos.
 */
public final class TemplateMethodDemo {
    private static final Logger LOGGER = AppLogger.get(TemplateMethodDemo.class);


    private TemplateMethodDemo() {
    }

    /** Esqueleto del proceso de generar un informe. */
    public abstract static class Report {
        /**
         * Método plantilla: el orden de los pasos no se puede cambiar.
         *
         * @return informe completo
         */
        public final String generate() {
            return header() + "\n" + body() + "\n" + footer();
        }

        protected String header() {
            return "=== INFORME ===";
        }

        protected abstract String body();

        protected String footer() {
            return "=== FIN ===";
        }
    }

    /** Variante: informe de ventas. */
    public static class SalesReport extends Report {
        @Override
        protected String body() {
            return "Ventas del mes: $120,000";
        }
    }

    /** Variante: informe de inventario con pie propio. */
    public static class InventoryReport extends Report {
        @Override
        protected String body() {
            return "Artículos en almacén: 3,450";
        }

        @Override
        protected String footer() {
            return "=== Revisar el stock mínimo ===";
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        LOGGER.info(new SalesReport().generate());
        LOGGER.info(new InventoryReport().generate());
    }
}
