package com.mercadolibre.marketplace_nine.repository;

import com.mercadolibre.marketplace_nine.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for the {@link Category} repository.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> { }
