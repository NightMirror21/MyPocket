package ru.nightmirror.mypocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nightmirror.mypocket.entity.Operation;
import ru.nightmirror.mypocket.entity.User;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findAllByUserOrderByDateDesc(User user);
}
