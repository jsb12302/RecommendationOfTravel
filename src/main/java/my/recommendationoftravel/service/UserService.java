package my.recommendationoftravel.service;

import my.recommendationoftravel.domain.user.User;
import my.recommendationoftravel.domain.user.UserDTO;
import my.recommendationoftravel.repository.UserRepository;
import my.recommendationoftravel.util.AlertException;
import my.recommendationoftravel.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDTO userDTO) {
        checkDuplicateUserId(userDTO);
        User user = new User().userDtoToEntity(userDTO);
        userRepository.save(user);
    }

    public void checkDuplicateUserId(UserDTO userDTO){
        Optional<User> findByUserId = userRepository.findByUserId(userDTO.getUserId());
        findByUserId.ifPresent(user ->{
            throw new AlertException(ErrorMessage.DUPLICATE_USERID);
        });
    }
}
