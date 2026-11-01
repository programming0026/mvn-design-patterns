package com.juniverse.patterns.structural.proxy;


import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Proxy: un sustituto controla el acceso a otro objeto (aquí: carga perezosa y caché de una imagen costosa).
 */
public final class ProxyDemo {
    private static final Logger LOGGER = AppLogger.get(ProxyDemo.class);


    private ProxyDemo() {
    }

    /** Sujeto común. */
    public interface Image {
        /**
         * Muestra la imagen.
         *
         * @return descripción
         */
        String display();
    }

    /** Objeto real: cargarlo es lento. */
    public static class RealImage implements Image {
        private final String file;

        /**
         * Carga la imagen (simulado).
         *
         * @param file nombre del archivo
         */
        public RealImage(String file) {
            this.file = file;
            LOGGER.info("  (cargando " + file + " del disco...)");
        }

        @Override
        public String display() {
            return "Mostrando " + file;
        }
    }

    /** Proxy: carga la imagen real solo la primera vez que se usa. */
    public static class ImageProxy implements Image {
        private final String file;
        private RealImage real;

        /**
         * Crea el proxy sin cargar nada todavía.
         *
         * @param file nombre del archivo
         */
        public ImageProxy(String file) {
            this.file = file;
        }

        @Override
        public String display() {
            if (real == null) {
                real = new RealImage(file);
            }
            return real.display();
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        Image photo = new ImageProxy("vacaciones.png");
        LOGGER.info("Proxy creado (aún no se cargó nada)");
        LOGGER.info(photo.display());
        LOGGER.info(photo.display());
    }
}
