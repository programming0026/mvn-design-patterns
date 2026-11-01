package com.juniverse.patterns;

import com.juniverse.patterns.creational.singleton.SingletonDemo;
import com.juniverse.patterns.creational.factorymethod.FactoryMethodDemo;
import com.juniverse.patterns.creational.abstractfactory.AbstractFactoryDemo;
import com.juniverse.patterns.creational.builder.BuilderDemo;
import com.juniverse.patterns.creational.prototype.PrototypeDemo;
import com.juniverse.patterns.structural.adapter.AdapterDemo;
import com.juniverse.patterns.structural.bridge.BridgeDemo;
import com.juniverse.patterns.structural.composite.CompositeDemo;
import com.juniverse.patterns.structural.decorator.DecoratorDemo;
import com.juniverse.patterns.structural.facade.FacadeDemo;
import com.juniverse.patterns.structural.flyweight.FlyweightDemo;
import com.juniverse.patterns.structural.proxy.ProxyDemo;
import com.juniverse.patterns.behavioral.chainofresponsibility.ChainOfResponsibilityDemo;
import com.juniverse.patterns.behavioral.command.CommandDemo;
import com.juniverse.patterns.behavioral.interpreter.InterpreterDemo;
import com.juniverse.patterns.behavioral.iterator.IteratorDemo;
import com.juniverse.patterns.behavioral.mediator.MediatorDemo;
import com.juniverse.patterns.behavioral.memento.MementoDemo;
import com.juniverse.patterns.behavioral.observer.ObserverDemo;
import com.juniverse.patterns.behavioral.state.StateDemo;
import com.juniverse.patterns.behavioral.strategy.StrategyDemo;
import com.juniverse.patterns.behavioral.templatemethod.TemplateMethodDemo;
import com.juniverse.patterns.behavioral.visitor.VisitorDemo;

import java.util.logging.Logger;
/**
 * Ejecuta un ejemplo de cada uno de los 23 patrones de diseño del GoF, organizados en tres grupos:
 * creacionales, estructurales y de comportamiento.
 */
public final class Main {
    private static final Logger LOGGER = AppLogger.get(Main.class);


    private Main() {
    }

    private static void section(String title) {
        LOGGER.info("\n############ " + title.toUpperCase() + " ############");
    }

    private static void demo(String name, Runnable demo) {
        LOGGER.info("\n--- " + name + " ---");
        demo.run();
    }

    /**
     * Punto de entrada.
     *
     * @param args no se usan
     */
    public static void main(String[] args) {
        // Salida en UTF-8 para que se vean bien los acentos en cualquier consola
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        section("Patrones creacionales");
        demo("Singleton", SingletonDemo::run);
        demo("FactoryMethod", FactoryMethodDemo::run);
        demo("AbstractFactory", AbstractFactoryDemo::run);
        demo("Builder", BuilderDemo::run);
        demo("Prototype", PrototypeDemo::run);
        section("Patrones estructurales");
        demo("Adapter", AdapterDemo::run);
        demo("Bridge", BridgeDemo::run);
        demo("Composite", CompositeDemo::run);
        demo("Decorator", DecoratorDemo::run);
        demo("Facade", FacadeDemo::run);
        demo("Flyweight", FlyweightDemo::run);
        demo("Proxy", ProxyDemo::run);
        section("Patrones de comportamiento");
        demo("ChainOfResponsibility", ChainOfResponsibilityDemo::run);
        demo("Command", CommandDemo::run);
        demo("Interpreter", InterpreterDemo::run);
        demo("Iterator", IteratorDemo::run);
        demo("Mediator", MediatorDemo::run);
        demo("Memento", MementoDemo::run);
        demo("Observer", ObserverDemo::run);
        demo("State", StateDemo::run);
        demo("Strategy", StrategyDemo::run);
        demo("TemplateMethod", TemplateMethodDemo::run);
        demo("Visitor", VisitorDemo::run);
    }
}
