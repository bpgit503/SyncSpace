package com.devbp.syncspace;

import com.devbp.syncspace.domain.UserStatus;
import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count()==0){
            User user1 = User.builder()
                    .email("alice@example.com")
                    .firstName("Alice")
                    .lastName("In-wonderland")
                    .phoneNumber("123456789")
                    .dateOfBirth(LocalDate.parse("1970-10-30"))
                    .address("1 down the rabbit hole, some forest")
                    .userType(UserType.CLIENT)
                    .status(UserStatus.ACTIVE)
                    .build();

            User user2 = User.builder()
                    .email("MassiveBob@jerky.com")
                    .firstName("Beefy")
                    .lastName("Bob")
                    .phoneNumber("126296969")
                    .dateOfBirth(LocalDate.parse("1969-06-09"))
                    .address("69 Buff Dabby st")
                    .userType(UserType.TRAINER)
                    .status(UserStatus.ACTIVE)
                    .build();

            User user3 = new User();
            user3.setEmail("cheshire.cat@example.com");
            user3.setFirstName("Cheshire");
            user3.setLastName("Cat");
            user3.setPhoneNumber("555000111");
            user3.setDateOfBirth(LocalDate.parse("1985-05-05"));
            user3.setAddress("Mystery Tree, Wonderland");
            user3.setUserType(UserType.CLIENT);
            user3.setStatus(UserStatus.ACTIVE);

            User user4 = new User();
            user4.setEmail("mad.hatter@example.com");
            user4.setFirstName("Mad");
            user4.setLastName("Hatter");
            user4.setPhoneNumber("777123456");
            user4.setDateOfBirth(LocalDate.parse("1980-03-15"));
            user4.setAddress("Tea Party Lane, Wonderland");
            user4.setUserType(UserType.CLIENT);
            user4.setStatus(UserStatus.ACTIVE);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
        }
    }
}
