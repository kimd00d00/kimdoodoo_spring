package doodoo.dev.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//<context:component-scan base-package="doodoo.dev.main"></context:component-scan>
@Configuration
@ComponentScan(basePackages= {"doodoo.dev.main"})
public class MovieConfig {
	
}
