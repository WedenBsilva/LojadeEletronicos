package com.mercadolibre.marketplace_nine.controller;

import com.mercadolibre.marketplace_nine.model.Category;
import com.mercadolibre.marketplace_nine.model.Product;
import com.mercadolibre.marketplace_nine.repository.ProductRepository;
import com.mercadolibre.marketplace_nine.service.ProductService;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Product controller.
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  private final ProductRepository productRepository;

  /**
   * @return all products
   */
  @GetMapping
  public ResponseEntity<Collection<Product>> findAll(
          @RequestParam(required = false) String title,
          @RequestParam(required = false) BigDecimal minPrice,
          @RequestParam(required = false) BigDecimal maxPrice) {
    if (title != null) {
      return ResponseEntity.ok(productService.findByTitle(title));
    }

    if (minPrice != null || maxPrice != null) {
      return ResponseEntity.ok(productService.getProductsByPriceRange(minPrice, maxPrice));
    }

    return ResponseEntity.ok(productService.findAll());
  }

  /**
   * @param product to be created
   * @return created product
   */
  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product product) {
    var createdProduct = productService.create(product);
    var location = URI.create(String.format("/products/%s", createdProduct.getId()));
    return ResponseEntity.created(location).body(createdProduct);
  }

  /**
   * @param id to be searched
   * @return product
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<Product> getById(@PathVariable("id") int id) {
    return ResponseEntity.ok(productService.getById(id));
  }

  /**
   * @param category to be searched
   * @return products by category
   */
  @GetMapping(path = "category/{category}")
  public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("category") Category category) {
    List<Product> products = productService.getProductsByCategory(category);
    return ResponseEntity.ok(products);
  }

  /**
   * @param id to be updated
   * @return no content
   */
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProductTitle(@PathVariable Integer id, @Valid @RequestBody Product productUp) {
    Optional<Product> productExist = productRepository.findById(id);
    if (productExist.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Product product1 = productExist.get();
    product1.setTitle(productUp.getTitle());
    Product productUpdated = productRepository.save(product1);
    return ResponseEntity.ok(productUpdated);
  }
}
