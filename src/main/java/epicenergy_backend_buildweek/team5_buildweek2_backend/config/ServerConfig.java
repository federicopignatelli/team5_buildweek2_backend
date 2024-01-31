package epicenergy_backend_buildweek.team5_buildweek2_backend.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfig {

    @Bean
    public Cloudinary cloudinary(@Value("${CLOUDINARY_NAME}") String name,
                                 @Value("${CLOUDINARY_API_KEY}") String apikey,
                                 @Value("${CLOUDINARY_SECRET}") String secret){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", name);
        config.put("cloud_key", apikey);
        config.put("cloud_secret", secret);

        return new Cloudinary(config);

    }
}
