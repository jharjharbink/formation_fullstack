package org.example.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionfactorySingleton {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private SessionfactorySingleton (){
        registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static synchronized SessionFactory getSessionFactory (){
        if(sessionFactory == null){
            new SessionfactorySingleton();
        }
        return sessionFactory;
    }
}