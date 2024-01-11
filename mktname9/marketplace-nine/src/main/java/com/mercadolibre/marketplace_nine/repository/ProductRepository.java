package com.mercadolibre.marketplace_nine.repository;

import com.mercadolibre.marketplace_nine.model.Category;
import com.mercadolibre.marketplace_nine.model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for the {@link Product} repository.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

  /**
   * @param title to be searched
   * @return products containing the title
   */
  List<Product> findByTitleContainingIgnoreCase(String title);

  /**
   * @param category to be searched
   * @return products containing the category
   */
  List<Product> findByCategory(Category category);

  /**
   * @param minPrice to be searched
   * @param maxPrice to be searched
   * @return products containing the price between minPrice and maxPrice
   */
  List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

  /**
   * @param minPrice to be searched
   * @return products containing the price greater than minPrice
   */
  List<Product> findByPriceGreaterThanEqual(BigDecimal minPrice);

  /**
   * @param max to be searched
   * @return products containing the price less than max
   */
  List<Product> findByPriceLessThanEqual(BigDecimal max);
}
