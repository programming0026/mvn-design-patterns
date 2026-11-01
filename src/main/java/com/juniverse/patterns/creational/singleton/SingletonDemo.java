package com.juniverse.patterns.creational.singleton;


import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Singleton: garantiza una única instancia y un punto de acceso global.
 * Un {@code enum} es la forma más segura en Java: es hilo-seguro y resiste la serialización y la reflexión.
 */
public final class SingletonDemo {
    private static final Logger LOGGER = AppLogger.get(SingletonDemo.class);


    private SingletonDemo() {
    }

    /** Configuración compartida por toda la aplicación. */
    public enum AppConfig {
        INSTANCE;

        private String environment = "desarrollo";

        /**
         * Entorno actual.
         *
         * @return nombre del entorno
         */
        public String getEnvironment() {
            return environment;
        }

        /**
         * Cambia el entorno.
         *
         * @param environment nuevo entorno
         */
        public void setEnvironment(String environment) {
            this.environment = environment;
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        AppConfig a = AppConfig.INSTANCE;
        AppConfig b = AppConfig.INSTANCE;
        a.setEnvironment("producción");
        LOGGER.info("¿Misma instancia? " + (a == b));
        LOGGER.info("Entorno visto desde b: " + b.getEnvironment());
    }
}
