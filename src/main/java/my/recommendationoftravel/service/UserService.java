package my.recommendationoftravel.service;

import lombok.RequiredArgsConstructor;
import my.recommendationoftravel.domain.user.User;
import my.recommendationoftravel.domain.user.UserDTO;
import my.recommendationoftravel.repository.UserRepository;
import my.recommendationoftravel.util.AlertException;
import my.recommendationoftravel.util.ErrorMessage;
import my.recommendationoftravel.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void registerUser(UserDTO userDTO) throws NoSuchAlgorithmException {
        checkDuplicateUserId(userDTO);
        User user = new User().userDtoToEntity(userDTO);
        String salt = passwordEncoder.getSalt();
        user.setSalt(salt);
        user.setPassword(passwordEncoder.passwordEncoder(userDTO.getPassword(), salt));
        userRepository.save(user);
    }

    public void checkDuplicateUserId(UserDTO userDTO){
        Optional<User> findByUserId = userRepository.findByUserId(userDTO.getUserId());
        findByUserId.ifPresent(user ->{
            throw new AlertException(ErrorMessage.DUPLICATE_USERID);
        });
    }

    public User checkLogin(String userId, String password) throws NoSuchAlgorithmException {
        //존재 하지 않으면
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty() || !passwordEncoder.passwordEncoder(password, user.get().getSalt()).equals(user.get().getPassword())) {
            throw new AlertException(ErrorMessage.NOT_AVAILABLE_LOGIN);
        }
        return user.get();
    }
}
