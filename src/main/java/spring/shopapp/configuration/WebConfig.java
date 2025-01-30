package spring.shopapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.shopapp.services.file_store.FileStorageService;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + FileStorageService.UPLOAD_DIR + "/**")
                .addResourceLocations("file:" + FileStorageService.UPLOAD_DIR + "/")
                .setCachePeriod(3600);
    }
}