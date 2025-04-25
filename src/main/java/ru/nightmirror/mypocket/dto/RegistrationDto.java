package ru.nightmirror.mypocket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDto {

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(min = 3, max = 20, message = "Имя пользователя от 3 до 20 символов")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 3, message = "Пароль минимум 3 символа")
    private String password;

    @NotBlank(message = "Повтор пароля не должен быть пустым")
    private String passwordConfirm;
}