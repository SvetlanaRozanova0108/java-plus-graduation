package ru.practicum.interaction.api.feignClient.client.user;
import feign.FeignException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import main.java.api.dto.user.NewUserRequest;
import main.java.api.dto.user.UserDto;
import main.java.api.dto.user.UserDtoForAdmin;

import java.util.List;


@FeignClient(name = "user-service", path = "/admin/users")
public interface UserClient {

    @GetMapping
    List<UserDto> getAllUsers(@RequestParam(defaultValue = "") List<Long> ids,
                              @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                              @Positive @RequestParam(defaultValue = "10") Integer size) throws FeignException;

    @PostMapping
    UserDto saveUser(@RequestBody @Valid NewUserRequest newUserRequest) throws FeignException;

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable Long userId) throws FeignException;

    @GetMapping("/{userId}")
    UserDto findById(@PathVariable Long userId) throws FeignException;

    @GetMapping("/admin/{userId}")
    UserDtoForAdmin adminFindById(@PathVariable Long userId) throws FeignException;
}
