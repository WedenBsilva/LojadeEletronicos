package com.mercadolibre.marketplace_nine.controller;

import com.mercadolibre.marketplace_nine.model.Category;
import com.mercadolibre.marketplace_nine.model.Product;
import com.mercadolibre.marketplace_nine.repository.ProductRepository;
import com.mercadolibre.marketplace_nine.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

  private ProductController productController;

  @Mock
  private ProductService productService;

  @Mock
  ProductRepository productRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    productController = new ProductController(productService,productRepository);
  }

  @BeforeEach
  public void setUpTitle() {
    MockitoAnnotations.openMocks(this);
  }



  @Test
  public void findAll() {
    List<Product> productList = Arrays.asList(
            new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", new Category(1, "Test")),
            new Product(2, "Produto 2", BigDecimal.ONE, "Imagem 2", "test", new Category(1, "Test"))
    );

    when(productService.findAll()).thenReturn(productList);

    ResponseEntity<Collection<Product>> responseEntity = productController.findAll(null, null, null);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    Collection<Product> result = responseEntity.getBody();
    assertEquals(productList, result);
  }

  @Test
  public void create() {
    Product createdProduct = new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", new Category(1, "Test"));

    when(productService.create(createdProduct)).thenReturn(createdProduct);

    ResponseEntity<Product> responseEntity = productController.create(createdProduct);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    Product result = responseEntity.getBody();
    assertEquals(createdProduct, result);
  }

  @Test
  public void findByTitle() {
    List<Product> productList = Arrays.asList(
            new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", new Category(1, "Test")),
            new Product(2, "Produto 2", BigDecimal.ONE, "Imagem 2", "test", new Category(1, "Test"))
    );

    when(productService.findByTitle("produto")).thenReturn(productList);

    ResponseEntity<Collection<Product>> responseEntity = productController.findAll("produto", null, null);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    Collection<Product> result = responseEntity.getBody();
    assertEquals(productList, result);
  }
  @Test
  public void testGetProductsByCategory() {
    Category testCategory = new Category(1, "Test"); // Crie um objeto de categoria
    List<Product> productList = Arrays.asList(
            new Product(1, "Produto 1", BigDecimal.ONE, "Imagem 1", "test", testCategory),
            new Product(2, "Produto 2", BigDecimal.ONE, "Imagem 2", "test", testCategory)
    );

    when(productService.getProductsByCategory(testCategory)).thenReturn(productList);

    ResponseEntity<List<Product>> responseEntity = productController.getProductsByCategory(testCategory);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    List<Product> result = responseEntity.getBody();
    assertEquals(productList, result);
  }

  @Test
  public void testGetProductsByPriceRange() {
    BigDecimal minPrice = BigDecimal.valueOf(50);
    BigDecimal maxPrice = BigDecimal.valueOf(100);

    List<Product> productList = new ArrayList<>();

    productList.add(new Product(1, "Produto 1", BigDecimal.valueOf(75), "Description 1", "Image 1", new Category(1, "Categoria 1")));
    productList.add(new Product(2, "Produto 2", BigDecimal.valueOf(90), "Description 2", "Image 2", new Category(1, "Categoria 1")));

    when(productService.getProductsByPriceRange(minPrice, maxPrice)).thenReturn(productList);

    ResponseEntity<Collection<Product>> responseEntity = productController.findAll(null, minPrice, maxPrice);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    Collection<Product> result = responseEntity.getBody();
    assertEquals(productList, result);
  }


  @Test
  public void testUpdateProductTitle() {
    // Cria um objeto Product para atualização
    Product productToUpdate = new Product();
    productToUpdate.setTitle("computador2");

    Integer productId = 1;
    //  produto com o ID fornecido no repositório
    Product existingProduct = new Product();
    existingProduct.setId(productId);
    existingProduct.setTitle("Título Antigo");

    when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
    // Simule o comportamento do repository ao salvar o produto atualizado
    when(productRepository.save(existingProduct)).thenReturn(productToUpdate);
    // Chame o método para atualizar o título do produto
    ResponseEntity<Product> response = productController.updateProductTitle(productId, productToUpdate);
    // Verifique se a resposta  (200 OK)
    assertEquals(HttpStatus.OK, response.getStatusCode());
    // corpo da resposta contém o produto atualizado com o novo título
    Product updatedProduct = response.getBody();
    assertEquals("computador2", updatedProduct.getTitle());
    // Verifique se o método findById foi chamado
    verify(productRepository, times(1)).findById(productId);
    // Verifique se o método save foi chamado uma vez
    verify(productRepository, times(1)).save(existingProduct);
  }
  @Test
  public void testUpdateProductTitle_ProductNotFound() {
    Integer nonExistentProductId = 999;
    // Crie um objeto Product para atualização
    Product productToUpdate = new Product();
    productToUpdate.setTitle("computador2");
    // (produto não encontrado)
    when(productRepository.findById(nonExistentProductId)).thenReturn(Optional.empty());
    // Chame o método para atualizar o título do produto
    ResponseEntity<Product> response = productController.updateProductTitle(nonExistentProductId, productToUpdate);
    // Verifique se a resposta é 404 Not Found
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    // Certifique-se de que o método findById foi chamado com o ID correto
    verify(productRepository, times(1)).findById(nonExistentProductId);
    // Certifique-se de que o método save não foi chamado (pois o produto não foi encontrado)
    verify(productRepository, times(0)).save(any());
  }

  @Test
  public void testGetProductsByMinPrice() {
    BigDecimal minPrice = BigDecimal.valueOf(50);

    List<Product> productList = new ArrayList<>();

    productList.add(new Product(1, "Produto 1", BigDecimal.valueOf(75), "Description 1", "Image 1", new Category(1, "Categoria 1")));
    productList.add(new Product(2, "Produto 2", BigDecimal.valueOf(90), "Description 2", "Image 2", new Category(1, "Categoria 1")));

    when(productService.getProductsByPriceRange(minPrice, null)).thenReturn(productList);

    ResponseEntity<Collection<Product>> responseEntity = productController.findAll(null, minPrice, null);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    Collection<Product> result = responseEntity.getBody();
    assertEquals(productList, result);
  }

  @Test
  public void testGetProductsByMaxPrice() {
    BigDecimal maxPrice = BigDecimal.valueOf(100);

    List<Product> productList = new ArrayList<>();

    productList.add(new Product(1, "Produto 1", BigDecimal.valueOf(75), "Description 1", "Image 1", new Category(1, "Categoria 1")));
    productList.add(new Product(2, "Produto 2", BigDecimal.valueOf(90), "Description 2", "Image 2", new Category(1, "Categoria 1")));

    when(productService.getProductsByPriceRange(null, maxPrice)).thenReturn(productList);

    ResponseEntity<Collection<Product>> responseEntity = productController.findAll(null, null, maxPrice);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    Collection<Product> result = responseEntity.getBody();
    assertEquals(productList, result);
  }
}
