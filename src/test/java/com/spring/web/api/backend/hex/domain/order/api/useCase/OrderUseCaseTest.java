package com.spring.web.api.backend.hex.domain.order.api.useCase;

import com.spring.web.api.backend.hex.domain.order.Order;
import com.spring.web.api.backend.hex.domain.order.OrderProvider;
import com.spring.web.api.backend.hex.domain.order.api.OrderApi;
import com.spring.web.api.backend.hex.domain.order.api.exeptions.OrderTagNoAvailableException;
import com.spring.web.api.backend.hex.domain.order.spi.OrderSpi;
import com.spring.web.api.backend.hex.domain.tag.Tag;
import com.spring.web.api.backend.hex.domain.tag.api.TagApi;
import com.spring.web.api.backend.hex.domain.tag.api.useCase.TagUseCase;
import com.spring.web.api.backend.hex.domain.tag.spi.TagSpi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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
