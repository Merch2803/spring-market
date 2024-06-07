package com.merch.spring.market;

import com.merch.spring.market.models.ProductDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
		ProductDTO productDTO = ProductDTO.builder()
				.id(1L)
				.title("title")
				.price(20)
				.count(1)
				.build();


		ProductDTO productDTO2 = ProductDTO.builder()
				.id(1L)
				.title("title")
				.price(20)
				.count(2)
				.build();


		System.out.println(productDTO2.equals(productDTO));
	}

}
