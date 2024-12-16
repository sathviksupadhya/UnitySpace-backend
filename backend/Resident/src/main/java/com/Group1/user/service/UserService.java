package com.Group1.user.service;
import com.Group1.user.dto.UserDto;
import com.Group1.user.dto.Response;
import com.Group1.user.dto.authentication;
import com.Group1.user.dto.userFullDetails;
import com.Group1.user.feign.authClient;
import com.Group1.user.model.User;
import com.Group1.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private authClient authclient;

    public Response<UserDto> approveResidentRequest(String userId, String action) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Resident not found with ID: " + userId));
        if (!user.getStatus().equalsIgnoreCase("PENDING")) {
            throw new RuntimeException("Only pending residents can be approved or rejected."+ userId + user.getStatus());
        }

        if ("Approve".equalsIgnoreCase(action)) {
            user.setStatus("APPROVED");
        } else if ("Reject".equalsIgnoreCase(action)) {
            user.setStatus("REJECTED");
        } else {
            throw new RuntimeException("Invalid action. Use 'Approve' or 'Reject'.");
        }

        userRepository.save(user);
        String message = "Approve".equalsIgnoreCase(action) ? "Resident approved successfully" : "Resident rejected successfully";
        return new Response<>(message, mapToDTO(user));
    }


    public Response<UserDto> updateResident(String userid, UserDto userDto) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("Resident not found with ID: " + userid));


        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setImage(userDto.getImage());

        user = userRepository.save(user);
        return new Response<>("Resident updated successfully", mapToDTO(user));
    }

    public Response<String> deleteResident(String userid) {
        userRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("Resident not found with ID: " + userid));
        userRepository.deleteById(userid);
        authclient.deleteUser(userid);
        return new Response<>("Resident deleted successfully", null);
    }

    public userFullDetails getResidentById(String userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("Resident not found with ID: " + userid));

        if (!user.getStatus().equalsIgnoreCase("APPROVED")) {
            throw new RuntimeException("Resident is not approved.");
        }
        authentication a = authclient.getUser(userid);
        userFullDetails userFullDetails = new userFullDetails();
        userFullDetails.setUserId(userid);
        userFullDetails.setRole(a.getRole());
        userFullDetails.setUserName(a.getUserName());
        userFullDetails.setEmail(user.getEmail());
        userFullDetails.setStatus(user.getStatus());
        userFullDetails.setPhoneNumber(user.getPhoneNumber());
        userFullDetails.setImage(user.getImage());


        return userFullDetails;
    }

    public List<userFullDetails> getAllResidents() {

        return userRepository.findAll()
                .stream()
                .map(user -> {
                    authentication a = authclient.getUser(user.getUserId());
                    userFullDetails userFullDetails = new userFullDetails();
                    userFullDetails.setUserId(user.getUserId());
                    userFullDetails.setRole(a.getRole());
                    userFullDetails.setUserName(a.getUserName());
                    userFullDetails.setEmail(user.getEmail());
                    userFullDetails.setStatus(user.getStatus());
                    userFullDetails.setPhoneNumber(user.getPhoneNumber());
                    userFullDetails.setImage(user.getImage());
                    return userFullDetails;
                })
                .collect(Collectors.toList());
    }


    private UserDto mapToDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setImage(user.getImage());
        userDto.setStatus(user.getStatus());
        return userDto;
    }

    private User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setImage(userDto.getImage());
        user.setStatus(userDto.getStatus());
        return user;
    }

    public List<User> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .filter(user -> user.getStatus().equalsIgnoreCase("APPROVED"))
                .toList();
    }

    public String createtuple(String id) {
        User user = new User();
        user.setUserId(id);
        authentication a = authclient.getUser(id);
        if(a.getUserName().equalsIgnoreCase("ADMIN")){
            user.setStatus("APPROVED");
            user.setEmail("admin@gmail.com");
            user.setPhoneNumber("+919121340152");
            user.setImage("https://media.istockphoto.com/id/1768858548/photo/dark-backlight-shadow-silhouette-of-male-person-incognito-unknown-profile.webp?a=1&b=1&s=612x612&w=0&k=20&c=nO5vniMxFB_BbOSkk2llnbzRsG6byoZpHG23xHgAyf4=");
        }else{
            user.setStatus("PENDING");
        }
        userRepository.save(user);
        return "tuple created";
    }
}
