package com.electrogrid.PAF;

import com.electrogrid.PAF.controller.*;
import jakarta.ws.rs.core.UriBuilder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp {

    public static final String BASE_URI = "http://0.0.0.0/";
    public static final int PORT = System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 8060;

    public static Server startServer() {

        final ResourceConfig config = new ResourceConfig()
                .registerInstances(new FeedbackController())
                .register(CORSResponseFilter.class);

        return JettyHttpContainerFactory.createServer(UriBuilder.fromUri(BASE_URI).port(PORT).build(), config);

    }

    public static void main(String[] args) {

        try {

            final Server server = startServer();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down the application...");
                    server.stop();
                    System.out.println("Done, exit.");
                } catch (Exception e) {
                    Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, e);
                }
            }));

            System.out.printf("Application started.%nStop the application using CTRL+C%n");

            Thread.currentThread().join();

        } catch (InterruptedException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
