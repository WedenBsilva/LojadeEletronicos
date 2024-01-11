package com.mercadolibre.marketplace_nine.controller;
import com.mercadolibre.marketplace_nine.model.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CategoryControllerTest {

        @Test
        public void testCategory() {
        // Crie uma instância de Category
        Category category = new Category();

        // Defina valores para id e nome
        category.setId(1); // Substitua 1 pelo valor desejado
        category.setName("HD");

        // Faça asserções para verificar se os valores estão corretos
        assertEquals(1, category.getId());
        assertEquals("HD",category.getName());
    }
}
