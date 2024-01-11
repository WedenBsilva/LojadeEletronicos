package com.mercadolibre.marketplace_nine.service;

import com.mercadolibre.marketplace_nine.model.Category;
import com.mercadolibre.marketplace_nine.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Category Service.
 */
@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;

  /**
   * @param categoryRepository to be injected
   */
  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  /**
   * @return all categories
   */
  public List<String> getAllCategoryNames() {
    List<Category> categories = categoryRepository.findAll();
    return categories.stream()
            .map(Category::getName)
            .collect(Collectors.toList());
  }
}
