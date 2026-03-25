# Patrón Front Controller con Spring

Se Implementa el patrón de diseño Front Controller usando Spring, simulando una aplicación web donde se manejan solicitudes como HOME o PRODUCT.
El Front Controller centraliza todas las solicitudes y delega la ejecución al Dispatcher, que a su vez llama al controlador correspondiente.
Se implementa con AppConfig, usando beans de Spring, ejecutándose en consola.

## Estructura del proyecto *************
src
 └── main
      └── java
           └── frontcontroller
                 ├── HomeController.java
                 ├── ProductController.java
                 ├── Dispatcher.java
                 ├── FrontController.java
                 ├── AppConfig.java
                 └── Main.java
                 
## Clases y responsabilidades **************

# 1 HomeController
public class HomeController {
    public void showHome() {
        System.out.println("HomeController: Mostrando la página HOME.");
    }
}

-->Responsabilidad: Manejar la lógica para la sección HOME.

2 ProductController
public class ProductController {
    public void showProduct() {
        System.out.println("ProductController: Mostrando la página PRODUCT.");
    }
}

-->Responsabilidad: Manejar la lógica para la sección PRODUCT.

# 3 Dispatcher
public class Dispatcher {
    private HomeController homeController;
    private ProductController productController;

    public Dispatcher(HomeController homeController, ProductController productController) {
        this.homeController = homeController;
        this.productController = productController;
    }

    public void dispatch(String request) {
        if ("HOME".equalsIgnoreCase(request)) {
            homeController.showHome();
        } else if ("PRODUCT".equalsIgnoreCase(request)) {
            productController.showProduct();
        } else {
            System.out.println("Dispatcher: Recurso no encontrado.");
        }
    }
}


-->Responsabilidad:

Recibir la solicitud del Front Controller.
Delegar la ejecución al controlador correcto según el request (HOME o PRODUCT).

# 4 FrontController
public class FrontController {
    private Dispatcher dispatcher;

    public FrontController(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void dispatchRequest(String request) {
        System.out.println("FrontController: Recibí solicitud: " + request);
        dispatcher.dispatch(request);
    }
}

-->Responsabilidad:

Ser el punto único de entrada de todas las solicitudes.
Pasar la solicitud al Dispatcher.

# 5 AppConfig
@Configuration
public class AppConfig {

    @Bean
    public HomeController homeController() {
        return new HomeController();
    }

    @Bean
    public ProductController productController() {
        return new ProductController();
    }

    @Bean
    public Dispatcher dispatcher() {
        return new Dispatcher(homeController(), productController());
    }

    @Bean
    public FrontController frontController() {
        return new FrontController(dispatcher());
    }
}

-->Responsabilidad: Configurar los beans de Spring.
Crear los objetos y establecer las dependencias: Dispatcher recibe los controladores, FrontController recibe el Dispatcher.


# 6 Main
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        FrontController frontController = context.getBean(FrontController.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Simulación Front Controller. Escribe HOME o PRODUCT:");

        String input = scanner.nextLine();
        frontController.dispatchRequest(input);

        scanner.close();
    }
}

-->Responsabilidad:
Iniciar la aplicación y el contexto de Spring.
Obtener el FrontController desde Spring.
Solicitar al usuario un input (HOME o PRODUCT) y enviarlo al FrontController.


## Flujo de ejecución *******************
El usuario escribe HOME o PRODUCT en consola.
Main obtiene el bean FrontController desde Spring.
FrontController.dispatchRequest() recibe la solicitud y la pasa a Dispatcher.
Dispatcher verifica qué controlador corresponde:
Si es HOME → llama a HomeController.showHome().
Si es PRODUCT → llama a ProductController.showProduct().
Si no coincide → muestra “Recurso no encontrado”.
El controlador ejecuta su lógica y muestra el mensaje en consola.


## Ejemplo de ejecución ******************
Simulación Front Controller. Escribe HOME o PRODUCT:
HOME
FrontController: Recibí solicitud: HOME
HomeController: Mostrando la página HOME.
Simulación Front Controller. Escribe HOME o PRODUCT:
PRODUCT
FrontController: Recibí solicitud: PRODUCT
ProductController: Mostrando la página PRODUCT.
Simulación Front Controller. Escribe HOME o PRODUCT:
ABOUT
FrontController: Recibí solicitud: ABOUT
Dispatcher: Recurso no encontrado.


# Diagrama de clases UML **********************************
+------------------+
|  FrontController  |
+------------------+
| - dispatcher      |
+------------------+
| +dispatchRequest()|
+------------------+
          |
          v
+------------------+
|    Dispatcher     |
+------------------+
| - homeController  |
| - productController|
+------------------+
| +dispatch()       |
+------------------+
     |         |
     v         v
+-----------+  +-------------+
| HomeController | ProductController |
+-----------+  +-------------+
| +showHome()| | +showProduct() |
+-----------+  +-------------+


Se demuestraa cómo centralizar solicitudes con el patrón Front Controller donde AppConfig permite definir y conectar todos los beans de forma organizada.
