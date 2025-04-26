package ru.nightmirror.mypocket.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import ru.nightmirror.mypocket.entity.OperationType;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor
public class OperationDto {

    @NotNull(message = "Сумма обязательна")
    @DecimalMin(value = "0.01", message = "Минимальная сумма — 0.01")
    private BigDecimal amount;

    @Size(max = 255, message = "Описание не более 255 символов")
    private String description;

    @NotNull(message = "Укажите тип операции")
    private OperationType type;
}
