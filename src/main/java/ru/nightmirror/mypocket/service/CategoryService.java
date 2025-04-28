package ru.nightmirror.mypocket.service;

import ru.nightmirror.mypocket.entity.Category;

import java.util.List;

public interface CategoryService {
    void createCategory(String name, String username);

    List<Category> getCategories(String username);

    void updateCategoryName(Long categoryId, String newName, String username);
}
