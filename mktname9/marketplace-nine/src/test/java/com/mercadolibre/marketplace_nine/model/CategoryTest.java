package com.mercadolibre.marketplace_nine.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    @Test
    public void validCategory() {
        Category categoria = new Category();
        int id = 123;
        String name = "Categoria";

        categoria.setId(id);
        categoria.setName(name);

        assertEquals(id, categoria.getId());
        assertEquals(name, categoria.getName());
    }
}
