package br.com.ecommerce.user.resource;

import br.com.ecommerce.user.dto.UserDto;
import br.com.ecommerce.user.entity.User;
import br.com.ecommerce.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> usersList = userService.findAllUsers();
        return ResponseEntity.ok().body(usersList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable UUID userId) {
        UserDto dto = userService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/find-user/{login}")
    public ResponseEntity<UserDto> findUserByLogin(@PathVariable String login) {
        UserDto userDto = userService.findByLogin(login);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable UUID userId) {
        userService.removeUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário removido com sucesso");
    }
}
