package com.mercadolibre.marketplace_nine.service;

import com.mercadolibre.marketplace_nine.model.Category;
import com.mercadolibre.marketplace_nine.model.Product;
import com.mercadolibre.marketplace_nine.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

/**
 * Product Service.
 */
@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  /**
   * @return all products
   */
  public Collection<Product> findAll() {
    return productRepository.findAll();
  }

  /**
   * @param product to be created
   * @return created product
   */
  public Product create(Product product) {
    return productRepository.save(product);
  }

  /**
   * @param id to be searched
   * @return product
   */
  public Product getById(int id) {
    return productRepository.findById(id)
            .orElseThrow(() -> new ExpressionException("Produto não encontrado com o ID:" + id));
  }

  /**
   * @param title to be searched
   * @return products containing the title
   */
  public List<Product> findByTitle(String title) {
    return productRepository.findByTitleContainingIgnoreCase(title);
  }

  /**
   * @param category to be searched
   * @return products containing the category
   */
  public List<Product> getProductsByCategory(Category category) {
    return productRepository.findByCategory(category);
  }

  /**
   * @param minPrice to be searched
   * @param maxPrice to be searched
   * @return products containing the price range
   */
  public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
    if (minPrice != null && maxPrice != null) {
      return productRepository.findByPriceBetween(minPrice, maxPrice);
    } else if (minPrice != null) {
      return productRepository.findByPriceGreaterThanEqual(minPrice);
    } else if (maxPrice != null) {
      return productRepository.findByPriceLessThanEqual(maxPrice);
    } else {
      return productRepository.findAll();
    }
  }
}
