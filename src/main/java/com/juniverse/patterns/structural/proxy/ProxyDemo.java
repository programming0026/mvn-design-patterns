package com.juniverse.patterns.structural.proxy;

/**
 * Proxy: un sustituto controla el acceso a otro objeto (aquí: carga perezosa y caché de una imagen costosa).
 */
public final class ProxyDemo {

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
            System.out.println("  (cargando " + file + " del disco...)");
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
        Image foto = new ImageProxy("vacaciones.png");
        System.out.println("Proxy creado (aún no se cargó nada)");
        System.out.println(foto.display());
        System.out.println(foto.display());
    }
}
