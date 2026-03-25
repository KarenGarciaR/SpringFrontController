package frontcontroller;

/**
 * Dispatcher: Se encarga de despachar la solicitud al controlador correspondiente.
 */
public class Dispatcher {

    private HomeController homeController;
    private ProductController productController;

    // Constructor recibe los controladores desde AppConfig
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