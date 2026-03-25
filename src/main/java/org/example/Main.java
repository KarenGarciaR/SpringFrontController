package frontcontroller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;

/**
 * Main: Clase principal que inicia la simulación del Front Controller.
 */
public class Main {

    public static void main(String[] args) {
        // Creamos el contexto de Spring usando AppConfig
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Obtenemos el FrontController del contexto
        FrontController frontController = context.getBean(FrontController.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Simulación Front Controller (Spring + AppConfig). Escribe HOME o PRODUCT:");

        String input = scanner.nextLine();
        frontController.dispatchRequest(input);

        scanner.close();
    }
}