package frontcontroller;

/**
 * FrontController: Patrón Front Controller.
 * Recibe la solicitud y la pasa al Dispatcher.
 */
public class FrontController {

    private Dispatcher dispatcher;

    // Constructor recibe Dispatcher desde AppConfig
    public FrontController(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void dispatchRequest(String request) {
        System.out.println("FrontController: Recibí solicitud: " + request);
        dispatcher.dispatch(request);
    }
}