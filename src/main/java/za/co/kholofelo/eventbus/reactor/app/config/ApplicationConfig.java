package za.co.kholofelo.eventbus.reactor.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.EventBus;
import za.co.kholofelo.eventbus.reactor.app.ConsumerRegistry;

import java.util.concurrent.CountDownLatch;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/13.
 */

@Configuration
@EnableAutoConfiguration
public class ApplicationConfig {

    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(3);
    }

    @Bean
    Environment env() {
        return Environment.initializeIfEmpty()
                .assignErrorJournal();
    }

    @Bean
    EventBus createEventBus(Environment env) {
        return EventBus.create(env, Environment.THREAD_POOL);
    }

    @Bean
    ConsumerRegistry createConsumerRegistry(){
        return new ConsumerRegistry();
    }
}
