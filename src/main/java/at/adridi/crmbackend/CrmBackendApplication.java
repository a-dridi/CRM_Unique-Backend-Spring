package at.adridi.crmbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Settings of the Web application - Mapping of the REST API. Defined in the
 * method onStartUp():
 *
 * @author ardat
 */
@SpringBootApplication
public class CrmBackendApplication extends SpringBootServletInitializer {

    /*
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//applicationContext.setConfigLocation(at.adridi.crmbackend.config);
        servletContext.addListener(new ContextLoaderListener(applicationContext));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/api/*");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CrmBackendApplication.class);
    }
     */
    
    public static void main(String[] args) {
        SpringApplication.run(CrmBackendApplication.class, args);
    }

}
