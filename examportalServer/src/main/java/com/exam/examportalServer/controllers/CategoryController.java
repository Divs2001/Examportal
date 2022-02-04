package com.exam.examportalServer.controllers;

import com.exam.examportalServer.entity.Category;
import com.exam.examportalServer.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //    add category
    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(this.categoryService.addCategory(category));
    }

    //    get category
    @GetMapping("/getCategory")
    public Category getCategory(@RequestParam Long catId) {
        return this.categoryService.getCategory(catId);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    @PutMapping("/updateCategory")
    public Category updateCategory(@RequestBody Category category){
        return this.categoryService.updateCategory(category);
    }

    @DeleteMapping("deleteCategory")
    public void deleteCategory(@RequestParam Long catId){
        this.categoryService.deleteCategory(catId);
    }

}
