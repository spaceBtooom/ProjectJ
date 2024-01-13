package com.spring.web.api.backend.hex.order.api.useCase;

import com.spring.web.api.backend.hex.order.domain.Order;
import com.spring.web.api.backend.hex.order.OrderProvider;
import com.spring.web.api.backend.hex.order.api.OrderApi;
import com.spring.web.api.backend.hex.order.api.exeptions.OrderTagNoAvailableException;
import com.spring.web.api.backend.hex.order.spi.OrderSpi;
import com.spring.web.api.backend.hex.tag.api.TagApi;
import com.spring.web.api.backend.hex.tag.api.useCase.TagUseCase;
import com.spring.web.api.backend.hex.tag.spi.TagSpi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

	private OrderSpi orderSpi;
	private TagApi testedSup;
	private TagSpi tagSpi;
	private OrderApi tested;


	@BeforeEach
	void setUp(){
		orderSpi = mock(OrderSpi.class);
		tagSpi = mock(TagSpi.class);
		testedSup = new TagUseCase(tagSpi);
		tested = new OrderUseCase(orderSpi, testedSup);
	}
	@Test
	void shouldSaveAndReturnOrder() throws OrderTagNoAvailableException {
		final Order order = OrderProvider.getCreatedOrder();
		when(tagSpi.existsByNameAndAliasId(anyString(),anyInt())).thenReturn(true);
		when(orderSpi.findById(order.getId())).thenReturn(Optional.of(order));

		final Optional<Order> returned = tested.save(order);
		verify(orderSpi).save(order);// был ли вызван метод, с заданными order
		verify(orderSpi).findById(order.getId());
	}

	@Test
	void findById() {
	}

	@Test
	void findAll() {
	}

	@Test
	void addTags() {
	}

	@Test
	void deleteById() {
	}

	@Test
	void deleteTag() {
	}
}
