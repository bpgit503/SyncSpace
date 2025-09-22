package com.devbp.syncspace.bootstrap;

import com.devbp.syncspace.domain.UserStatus;
import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.entities.Trainer;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.repositories.TrainerRepository;
import com.devbp.syncspace.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;


    @Override
    public void run(String... args) throws Exception {
        User user1;
        User user2;
        User user3;
        User user4;
        User user5;


        if (userRepository.count() == 0) {
            user1 = User.builder()
                    .email("alice@example.com")
                    .firstName("Alice")
                    .lastName("In-wonderland")
                    .phoneNumber("123456789")
                    .dateOfBirth(LocalDate.parse("1970-10-30"))
                    .address("1 down the rabbit hole, some forest")
                    .userType(UserType.CLIENT)
                    .status(UserStatus.INACTIVE)
                    .build();

            user2 = User.builder()
                    .email("MassiveBob@jerky.com")
                    .firstName("Beefy")
                    .lastName("Bob")
                    .phoneNumber("126296969")
                    .dateOfBirth(LocalDate.parse("1969-06-09"))
                    .address("69 Buff Dabby st")
                    .userType(UserType.TRAINER)
                    .status(UserStatus.ACTIVE)
                    .build();


            user3 = new User();
            user3.setEmail("cheshire.cat@example.com");
            user3.setFirstName("Cheshire");
            user3.setLastName("Cat");
            user3.setPhoneNumber("555000111");
            user3.setDateOfBirth(LocalDate.parse("1985-05-05"));
            user3.setAddress("Mystery Tree, Wonderland");
            user3.setUserType(UserType.CLIENT);
            user3.setStatus(UserStatus.ACTIVE);

            user4 = new User();
            user4.setEmail("mad.hatter@example.com");
            user4.setFirstName("Mad");
            user4.setLastName("Hatter");
            user4.setPhoneNumber("777123456");
            user4.setDateOfBirth(LocalDate.parse("1980-03-15"));
            user4.setAddress("Tea Party Lane, Wonderland");
            user4.setUserType(UserType.CLIENT);
            user4.setStatus(UserStatus.ACTIVE);

            user5 = User.builder()
                    .email("FitJim@com")
                    .firstName("Slim")
                    .lastName("Shaddy")
                    .phoneNumber("000000000")
                    .dateOfBirth(LocalDate.parse("1969-06-09"))
                    .address("69 shaddy Ave")
                    .userType(UserType.TRAINER)
                    .status(UserStatus.ACTIVE)
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            userRepository.save(user5);

            if (trainerRepository.count() == 0) {
                Trainer trainer1 = Trainer.builder()
                        .user(user2)
                        .earningsPercentage(0.4)
                        .build();

                Trainer trainer2 = Trainer.builder()
                        .user(user5)
                        .earningsPercentage(0.4)
                        .build();

                trainerRepository.save(trainer1);
                trainerRepository.save(trainer2);
            }

        }


    }
}
