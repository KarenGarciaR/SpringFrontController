package frontcontroller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig: Configuración de Spring con beans.
 */
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