package lv.sda.sdaonlinestore.service;

import lv.sda.sdaonlinestore.entity.Category;
import lv.sda.sdaonlinestore.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    static CategoryRepository categoryRepository;

    public static List<Category> getAllCategories() {
        List<Category> category = new ArrayList<Category>();
        categoryRepository.findAll().forEach(e -> category.add(e));
        return category;
    }
    @Transactional
    public Category saveOrUpdate(Category category) {

        return categoryRepository.save(category);
    }
    @Transactional
    public Category update(Category category, Long categoryId) {

        return categoryRepository.save(category);
    }
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    @Transactional
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public Category findById(Long categoryId) {
        return findById(categoryId);
    }
    @Transactional
    public void deleteById(Long orderId) {
        categoryRepository.deleteById(orderId);
    }
}


