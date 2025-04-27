package ru.nightmirror.mypocket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nightmirror.mypocket.dto.OperationDto;
import ru.nightmirror.mypocket.entity.Category;
import ru.nightmirror.mypocket.entity.Operation;
import ru.nightmirror.mypocket.entity.OperationType;
import ru.nightmirror.mypocket.entity.User;
import ru.nightmirror.mypocket.repository.CategoryRepository;
import ru.nightmirror.mypocket.repository.OperationRepository;
import ru.nightmirror.mypocket.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository opRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    @Override
    @Transactional
    public void addOperation(OperationDto dto, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        Operation op = new Operation();
        op.setUser(user);

        BigDecimal amt = dto.getAmount();
        if (dto.getType() == OperationType.EXPENSE) {
            amt = amt.negate();
        }
        op.setAmount(amt);

        op.setDescription(dto.getDescription());
        op.setType(dto.getType());
        op.setDate(LocalDateTime.now());

        if (dto.getCategoryId() != null) {
            Category cat = categoryRepo.findById(dto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            op.setCategory(cat);
        }

        opRepo.save(op);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getCurrentBalance(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        return opRepo.findAllByUserOrderByDateDesc(user).stream()
                .map(Operation::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Operation> getOperations(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        return opRepo.findAllByUserOrderByDateDesc(user);
    }
}
