package ru.nightmirror.mypocket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nightmirror.mypocket.entity.Category;
import ru.nightmirror.mypocket.entity.User;
import ru.nightmirror.mypocket.repository.CategoryRepository;
import ru.nightmirror.mypocket.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public void createCategory(String name, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        Category category = new Category();
        category.setUser(user);
        category.setName(name);

        categoryRepo.save(category);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategories(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        return categoryRepo.findAllByUserOrderByNameAsc(user);
    }
}
