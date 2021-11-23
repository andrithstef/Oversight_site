@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry）{
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}