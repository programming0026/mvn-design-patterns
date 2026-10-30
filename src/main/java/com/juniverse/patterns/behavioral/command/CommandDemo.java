package com.juniverse.patterns.behavioral.command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Command: convierte una acción en un objeto, lo que permite deshacerla, encolarla o registrarla.
 */
public final class CommandDemo {

    private CommandDemo() {
    }

    /** Receptor: un editor de texto. */
    public static class Editor {
        private final StringBuilder text = new StringBuilder();

        /**
         * Texto actual.
         *
         * @return contenido
         */
        public String text() {
            return text.toString();
        }
    }

    /** Comando con posibilidad de deshacer. */
    public interface Command {
        /** Ejecuta la acción. */
        void execute();

        /** Revierte la acción. */
        void undo();
    }

    /** Comando concreto: escribir texto. */
    public static class TypeCommand implements Command {
        private final Editor editor;
        private final String added;

        /**
         * Crea el comando.
         *
         * @param editor editor receptor
         * @param added texto a escribir
         */
        public TypeCommand(Editor editor, String added) {
            this.editor = editor;
            this.added = added;
        }

        @Override
        public void execute() {
            editor.text.append(added);
        }

        @Override
        public void undo() {
            editor.text.setLength(editor.text.length() - added.length());
        }
    }

    /** Invocador: guarda el historial para deshacer. */
    public static class History {
        private final Deque<Command> done = new ArrayDeque<>();

        /**
         * Ejecuta un comando y lo recuerda.
         *
         * @param command comando
         */
        public void run(Command command) {
            command.execute();
            done.push(command);
        }

        /** Deshace el último comando. */
        public void undo() {
            if (!done.isEmpty()) {
                done.pop().undo();
            }
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        Editor editor = new Editor();
        History history = new History();
        history.run(new TypeCommand(editor, "Hola"));
        history.run(new TypeCommand(editor, ", mundo"));
        System.out.println("Texto: " + editor.text());
        history.undo();
        System.out.println("Tras deshacer: " + editor.text());
    }
}
