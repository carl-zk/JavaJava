package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hero on 10/04/2018.
 */
@Configuration
@ComponentScan(basePackages = {"soundsystem"})
public class CDPlayerConfig {
    @Bean
    public CompactDisc getCD(Voice voice) {
        return new SoloAlbum();
    }

    @Bean
    public Voice getV() {
        return new Voice();
    }

}
