package com.mercadolibre.marketplace_nine.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    public void when_created_product_successfully(){
        Product product = new Product();
        BigDecimal value = BigDecimal.valueOf(1.500);
        Product product2 = new Product(1,"teste",value,"teste",  "teste", new Category(1, "teste"));
        product.setId(1);
        product.setTitle("computador");
        product.setDescription("para ser utilizado em games com alto rendimento");
        product.setPrice(BigDecimal.valueOf(1.500));
        product.setImage("computador");
        assertEquals("computador", product.getTitle());
        assertEquals("para ser utilizado em games com alto rendimento", product.getDescription());
        assertEquals((BigDecimal.valueOf(1.500)), product.getPrice());
        assertEquals("computador", product.getImage());
        assertEquals(1, product.getId());
        assertEquals(1, product2.getCategory().getId());
        assertEquals("teste", product2.getCategory().getName());
        assertInstanceOf(Product.class, product2);
    }
}
