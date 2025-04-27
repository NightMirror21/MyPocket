package ru.nightmirror.mypocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nightmirror.mypocket.entity.Category;
import ru.nightmirror.mypocket.entity.User;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUserOrderByNameAsc(User user);
}
