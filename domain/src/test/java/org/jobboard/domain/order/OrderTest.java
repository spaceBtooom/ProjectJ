package org.jobboard.domain.order;

import org.jobboard.domain.tag.Alias;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void givenEmptyOrder_addTagName_expectedUniqNameException(){
        Order order = TestOrderFactory.emptyOrder();
    }

}
