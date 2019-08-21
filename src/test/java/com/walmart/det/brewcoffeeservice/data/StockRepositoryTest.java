package com.walmart.det.brewcoffeeservice.data;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StockRepositoryTest {
	
	@Autowired
	private
	StockRepository repository;

	@Test
	public void testFindAll() {
		assertEquals(2, repository.findAll().size());
	}

}
