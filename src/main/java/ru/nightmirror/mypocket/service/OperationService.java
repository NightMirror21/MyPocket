package ru.nightmirror.mypocket.service;

import ru.nightmirror.mypocket.dto.OperationDto;
import ru.nightmirror.mypocket.entity.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface OperationService {
    void addOperation(OperationDto dto, String username);

    BigDecimal getCurrentBalance(String username);

    List<Operation> getOperations(String username);
}
