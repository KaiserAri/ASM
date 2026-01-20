package poly.edu.asmn1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import poly.edu.asmn1.interceptor.AuthInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired AuthInterceptor auth;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Chặn các link nhạy cảm (Slide 8)
        registry.addInterceptor(auth)
                .addPathPatterns("/admin/**", "/order/**", "/cart/checkout");
    }
}