package co.edu.eci.arep.springweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
 * Class that handles the web configuration
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private BasicAuthInterceptor basicAuthInterceptor;

    /*
     * Method that adds the basic authentication interceptor
     * 
     * @param registry, the registry to add the interceptor to
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicAuthInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Sirve los archivos estáticos desde el directorio "static"
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

}
