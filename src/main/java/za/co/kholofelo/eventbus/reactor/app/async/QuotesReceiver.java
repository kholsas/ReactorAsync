package za.co.kholofelo.eventbus.reactor.app.async;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;
import reactor.fn.Consumer;
import za.co.kholofelo.eventbus.reactor.app.resource.QuoteResource;

import java.util.concurrent.CountDownLatch;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */
@Service
public class QuotesReceiver implements Consumer<Event<Integer>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuotesReceiver.class);


    @Autowired
    CountDownLatch latch;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void accept(Event<Integer> event) {

        LOGGER.info(" ########## Quotes Listing ##########");

        QuoteResource quoteResource = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", QuoteResource.class);
        LOGGER.info("Quote " + event.getData() + ": " + quoteResource.getValue().getQuote());

    }
}
