package ru.nightmirror.mypocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nightmirror.mypocket.entity.Operation;
import ru.nightmirror.mypocket.entity.User;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findAllByUserOrderByDateDesc(User user);

    @Query("SELECT o FROM Operation o LEFT JOIN FETCH o.category WHERE o.user = :user ORDER BY o.date DESC")
    List<Operation> findAllByUserWithCategoryOrderByDateDesc(@Param("user") User user);
}
