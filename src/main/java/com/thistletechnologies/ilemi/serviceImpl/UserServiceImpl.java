package com.thistletechnologies.ilemi.serviceImpl;

import com.thistletechnologies.ilemi.dto.request.JoinWaitingListRequest;
import com.thistletechnologies.ilemi.dto.response.JoinWaitingListResponse;
import com.thistletechnologies.ilemi.exceptions.InvalidInputException;
import com.thistletechnologies.ilemi.exceptions.NoCategorySelectedException;
import com.thistletechnologies.ilemi.exceptions.UserException;
import com.thistletechnologies.ilemi.exceptions.UserExistException;
import com.thistletechnologies.ilemi.model.Category;
import com.thistletechnologies.ilemi.model.User;
import com.thistletechnologies.ilemi.repository.UserRepository;
import com.thistletechnologies.ilemi.service.UserService;
import com.thistletechnologies.ilemi.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    @Autowired
     UserRepository userRepository;

    @Override
    public JoinWaitingListResponse joinWaitingList(JoinWaitingListRequest waitingListRequest) {
        if (!UserValidator.isValidEmail(waitingListRequest.getEmail())) {
            throw new InvalidInputException("Invalid email format");
        }

        if (!UserValidator.isValidPhoneNumber(waitingListRequest.getPhoneNumber())) {
            throw new InvalidInputException("Invalid phone number format");
        }
        if (!UserValidator.isValidWhatsappNumber(waitingListRequest.getWhatsappNumber())) {
            throw new InvalidInputException("Invalid phone number format");
        }

        if (userRepository.findUserByEmail(waitingListRequest.getEmail().toLowerCase()).isPresent()){

            return JoinWaitingListResponse.builder()
                    .message("user is already registered")
                    .build();
        }

//            throw new UserExistException("user already exist");
//        String categoryString = waitingListRequest.getCategory().toString();
        User user = User.builder()
                .fullName(waitingListRequest.getFullName())
                .phoneNumber(waitingListRequest.getPhoneNumber())
                .whatsappNumber(waitingListRequest.getWhatsappNumber())
                .email(waitingListRequest.getEmail())
                .category(convertToEnum(waitingListRequest.getCategory()))
                .build();
        userRepository.save(user);


        return JoinWaitingListResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .message("user registered successfully")
                .build();
    }
    private Category convertToEnum(String selectedCategory){
        return Arrays.stream(Category.values())
                .filter(category -> category.toString().equals(selectedCategory.strip().toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid category string"));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        if (userRepository.findUserByEmail(email).isEmpty())
            throw new UserException("user does not exist");
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Optional<User> getUserById(String id) {
        if (userRepository.findUserById(id).isEmpty()) throw new UserException("user id not found")
;        return userRepository.findUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        if (userRepository.findAll().size() == 0) throw new UserException("no user found");
        return userRepository.findAll();
    }
}
