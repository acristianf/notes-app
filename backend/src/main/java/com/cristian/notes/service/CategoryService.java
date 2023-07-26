package com.cristian.notes.service;

import com.cristian.notes.domain.Category;
import com.cristian.notes.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    private final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getCategories() {
        LOGGER.info("get all categories");
        return (List<Category>) categoryRepository.findAll();
    }

    public Category createCategory(String name) {
        LOGGER.info("create category with name '{}'", name);
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        return optionalCategory.orElse(categoryRepository.save(new Category(name)));
    }

    public Optional<Category> getCategory(Long id) {
        LOGGER.info("get category with id {}", id);
        return categoryRepository.findById(id);
    }

    public void deleteCategory(Long id) {
        LOGGER.info("delete category with id {}", id);
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        categoryOptional.ifPresent(
                (category) -> {
                    category.getNotes().forEach(note -> note.removeCategory(category));
                    categoryRepository.delete(category);
                });
    }
}
