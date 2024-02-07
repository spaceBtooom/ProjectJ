package org.jobboard.domain.order;

import org.jobboard.domain.tag.Alias;

import java.time.OffsetDateTime;
import java.util.UUID;

public class TestOrderFactory {
    public static Order emptyOrder(){
        Order order = new Order(UUID.randomUUID());
        order.setTitle("Hexagonal architecture on Java");
        order.setComment("Needed to create...");
        order.setPrice(1000);
        order.setUrlSource("https://github.com/SvenWoltmann/hexagonal-architecture-java/blob/main/model/src/test/java/eu/happycoders/shop/model/cart/TestCartFactory.java");
        order.setExpireAt(OffsetDateTime.MAX);

        return order;
    }
}
