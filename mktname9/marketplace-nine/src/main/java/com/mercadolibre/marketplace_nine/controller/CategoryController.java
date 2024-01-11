package com.mercadolibre.marketplace_nine.controller;

import com.mercadolibre.marketplace_nine.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Category controller.
 */
@RestController
@RequestMapping("/products")
public class CategoryController {
  private final CategoryService categoryService;

  /**
   * Constructor.
   * @param categoryService category service.
   */
  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  /**
   * Get all category names.
   * @return list of category names.
   */
  @GetMapping("/category")
  public List<String> getAllCategoryNames() {
    return categoryService.getAllCategoryNames();
  }
}
