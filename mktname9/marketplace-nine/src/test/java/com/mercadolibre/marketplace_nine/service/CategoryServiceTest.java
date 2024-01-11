package com.mercadolibre.marketplace_nine.service;


import com.mercadolibre.marketplace_nine.model.Category;
import com.mercadolibre.marketplace_nine.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCategoryNames() {
        // Crie uma lista de categorias de exemplo
        List<Category> exampleCategories = new ArrayList<>();
        exampleCategories.add(new Category(1, "Categoria A"));
        exampleCategories.add(new Category(2, "Categoria B"));
        exampleCategories.add(new Category(3, "Categoria C"));

        // Defina o comportamento esperado do repository mock quando findAll é chamado
        when(categoryRepository.findAll()).thenReturn(exampleCategories);

        // Chame o método de serviço para obter todos os nomes de categorias
        List<String> categoryNames = categoryService.getAllCategoryNames();

        // Verifique se o método findAll do repository foi chamado
        verify(categoryRepository, times(1)).findAll();

        // Verifique se a lista de nomes de categorias está correta
        assertEquals(exampleCategories.size(), categoryNames.size());
        assertTrue(categoryNames.contains("Categoria A"));
        assertTrue(categoryNames.contains("Categoria B"));
        assertTrue(categoryNames.contains("Categoria C"));
    }
}