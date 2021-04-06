package lv.sda.sdaonlinestore.controller;


import lv.sda.sdaonlinestore.entity.Category;
import lv.sda.sdaonlinestore.entity.StoreUser;
import lv.sda.sdaonlinestore.service.CategoryService;
import lv.sda.sdaonlinestore.service.StoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        List<Category> getAllCategories = categoryService.getAllCategories();
        return getAllCategories;
    }

    @GetMapping("/byId")
    public List<Category> displayCategoriesById(@RequestParam Long categoryId) {
        Category categoryById = categoryService.findById(categoryId);
        List<Category> lst = new ArrayList<>();
        lst.add(categoryById);
        return lst;
    }

    @PutMapping
    public List<Category> updateCategoryUpdate(@RequestBody Category category) {
        categoryService.update(category, category.getCategoryId());
        List<Category> lst = new ArrayList<>();
        lst.add(category);
        return lst;
    }

    @PostMapping
    public List<Category> addCategory(@RequestBody Category category) {
        categoryService.save(category);
        List<Category> lst = new ArrayList<>();
        lst.add(category);
        return lst;
    }

    @DeleteMapping
    public String deleteCategory(@RequestBody List<Category> category) {
        category.forEach(c -> {
            categoryService.delete(c);
        });
        return "Category deleted";

    }

}
