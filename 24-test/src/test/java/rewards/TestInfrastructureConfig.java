package rewards;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import config.RewardsConfig;
import org.springframework.context.annotation.Profile;

@Configuration
@Import({
	TestInfrastructureLocalConfig.class,
	TestInfrastructureJndiConfig.class,
	RewardsConfig.class })
@Profile("local")
public class TestInfrastructureConfig {

	/**
	 * The bean logging post-processor from the bean lifecycle slides.
	 */
	@Bean
	public static LoggingBeanPostProcessor loggingBean(){
		return new LoggingBeanPostProcessor();
	}
}
