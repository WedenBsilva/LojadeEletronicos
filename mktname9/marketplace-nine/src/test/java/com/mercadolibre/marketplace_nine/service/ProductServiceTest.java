package com.mercadolibre.marketplace_nine.service;

import com.mercadolibre.marketplace_nine.model.Category;
import com.mercadolibre.marketplace_nine.model.Product;
import com.mercadolibre.marketplace_nine.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    public void findAll() {
        List<Product> productList = Arrays.asList(
                new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", new Category(1, "Test")),
                new Product(2, "Produto 2", BigDecimal.ONE, "Imagem 2", "test", new Category(1, "Test"))
        );

        when(productRepository.findAll()).thenReturn(productList);

        Collection<Product> result = productService.findAll();

        assertEquals(productList, result);
    }

    @Test
    public void create() {
        Product createdProduct = new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", new Category(1, "Test"));

        when(productRepository.save(createdProduct)).thenReturn(createdProduct);

        Product result = productService.create(createdProduct);

        assertEquals(createdProduct, result);
    }

    @Test
    public void findAllByTitle() {
        List<Product> productList = Arrays.asList(
                new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", new Category(1, "Test")),
                new Product(2, "Produto 2", BigDecimal.ONE, "Imagem 2", "test", new Category(1, "Test")));

        when(productRepository.findByTitleContainingIgnoreCase("produto")).thenReturn(productList);

        Collection<Product> result = productService.findByTitle("produto");

        assertEquals(productList, result);
    }
    @Test
    public void testGetProductsByCategory() {
        Category testCategory = new Category(1, "Test"); // Crie um objeto de categoria
        List<Product> productList = Arrays.asList(
                new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", testCategory),
                new Product(2, "Produto 2", BigDecimal.ONE, "Imagem 2", "test", testCategory)
        );

        when(productRepository.findByCategory(testCategory)).thenReturn(productList);

        Collection<Product> result = productService.getProductsByCategory(testCategory);

        assertNotNull(result);
        assertEquals(productList.size(), result.size());
        assertEquals(productList, result);
    }
    @Test
    public void testGetProductsByPriceRange_WithMinAndMaxPrice() {
        BigDecimal minPrice = BigDecimal.valueOf(50);
        BigDecimal maxPrice = BigDecimal.valueOf(100);

        List<Product> productList = Arrays.asList(
                new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", new Category(1, "Test")),
                new Product(2, "Produto 2", BigDecimal.ONE, "Imagem 2", "test", new Category(1, "Test"))
        );

        when(productRepository.findByPriceBetween(minPrice, maxPrice)).thenReturn(productList);

        List<Product> result = productService.getProductsByPriceRange(minPrice, maxPrice);

        assertNotNull(result);
        assertEquals(productList.size(), result.size());
        assertEquals(productList, result);
    }

    @Test
    public void testGetProductsByPriceRange_WithMinPrice() {
        BigDecimal minPrice = BigDecimal.valueOf(50);

        List<Product> productList = Arrays.asList(
                new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", new Category(1, "Test")),
                new Product(2, "Produto 2", BigDecimal.ONE, "Imagem 2", "test", new Category(1, "Test"))
        );

        when(productRepository.findByPriceGreaterThanEqual(minPrice)).thenReturn(productList);

        List<Product> result = productService.getProductsByPriceRange(minPrice, null);

        assertNotNull(result);
        assertEquals(productList.size(), result.size());
        assertEquals(productList, result);
    }

    @Test
    public void testGetProductsByPriceRange_WithMaxPrice() {
        BigDecimal maxPrice = BigDecimal.valueOf(100);

        List<Product> productList = Arrays.asList(
                new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", new Category(1, "Test")),
                new Product(2, "Produto 2", BigDecimal.ONE, "Imagem 2", "test", new Category(1, "Test"))
        );

        when(productRepository.findByPriceLessThanEqual(maxPrice)).thenReturn(productList);

        List<Product> result = productService.getProductsByPriceRange(null, maxPrice);

        assertNotNull(result);
        assertEquals(productList.size(), result.size());
        assertEquals(productList, result);
    }

    @Test
    public void testGetProductsByPriceRange_WithNoPriceFilter() {
        List<Product> productList = Arrays.asList(
                new  Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", new Category(1, "Test")),
                new Product(2, "Produto 2", BigDecimal.ONE, "Imagem 2", "test", new Category(1, "Test"))
        );

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getProductsByPriceRange(null, null);

        assertNotNull(result);
        assertEquals(productList.size(), result.size());
        assertEquals(productList, result);
    }

}


