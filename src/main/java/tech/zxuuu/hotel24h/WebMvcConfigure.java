package tech.zxuuu.hotel24h;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.zxuuu.hotel24h.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500", "http://127.0.0.1:5500", "http://127.0.0.1:5500")
      .allowCredentials(true)
      .allowedMethods("*")
      .maxAge(3600);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/emp/**", "/room/**", "/reserve/**", "/comment/**", "/check/**", "/", "/indexPage", "/adminPage")
            .excludePathPatterns("/login", "/changePwdPage", "/changePwd");
  }

}
