package com.devbp.syncspace.bootstrap;

import com.devbp.syncspace.domain.ClassStatus;
import com.devbp.syncspace.domain.UserStatus;
import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.entities.ClassType;
import com.devbp.syncspace.domain.entities.Classes;
import com.devbp.syncspace.domain.entities.Trainer;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.repositories.ClassRepository;
import com.devbp.syncspace.repositories.ClassTypeRepository;
import com.devbp.syncspace.repositories.TrainerRepository;
import com.devbp.syncspace.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;
    private final ClassTypeRepository classTypeRepository;
    private final ClassRepository classRepository;


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
                    .email("slimJim@slimming.com")
                    .firstName("Slim")
                    .lastName("Jim")
                    .phoneNumber("000000000")
                    .dateOfBirth(LocalDate.parse("1969-06-09"))
                    .address("69 slim Ave")
                    .userType(UserType.TRAINER)
                    .status(UserStatus.ACTIVE)
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            userRepository.save(user5);


            Trainer trainer1 = Trainer.builder()
                    .user(user2)
                    .specializations(List.of("Massive Specializations", "More Massive Specializations"))
                    .certifications(List.of("Massive Certification", "Greater Massive Certifications"))
                    .contractDetails("Sample Contract")
                    .earningsPercentage(0.4)
                    .hourlyRate(1)
                    .bio("A Massive coach of massive standards who specialty is to get people to become massive in both mind and body")
                    .experienceYears(10)
                    .isAvailable(true)
                    .build();

            Trainer trainer2 = Trainer.builder()
                    .user(user5)
                    .specializations(List.of("Slimming Specializations", "More Slimming Specializations"))
                    .certifications(List.of("Slimming Certification", "Greater Slimming Certifications"))
                    .contractDetails("Sample Contract")
                    .earningsPercentage(0.4)
                    .hourlyRate(1)
                    .bio("A Slim coach of slim standards who specialty is to get people to become slim in both mind and body")
                    .experienceYears(10)
                    .isAvailable(true)
                    .build();

            trainerRepository.save(trainer1);
            trainerRepository.save(trainer2);


            ClassType classType1 = ClassType.builder()
                    .className("GetMassive")
                    .description("Come and get absolutely massive with massive Bob")
                    .durationMinutes(45)
                    .isGroupClass(true)
                    .maxCapacity(15)
                    .basePrice(10)
                    .isActive(true)
                    .build();

            ClassType classType2 = ClassType.builder()
                    .className("Get Slim")
                    .description("Come and slim down with slim Jim")
                    .durationMinutes(60)
                    .isGroupClass(true)
                    .maxCapacity(20)
                    .basePrice(12)
                    .isActive(true)
                    .build();

            classTypeRepository.save(classType1);
            classTypeRepository.save(classType2);


            Classes classes1 = Classes.builder()
                    .classType(classType1)
                    .trainer(trainer1)
                    .scheduledDate(LocalDate.of(2025, 10, 15))
                    .startTime(LocalTime.of(9, 0))
                    .endTime(LocalTime.of(10, 0))
                    .maxCapacity(20)
                    .currentCapacity(12)
                    .classStatus(ClassStatus.SCHEDULED)
                    .notes("Morning slimming session")
                    .build();


            Classes classes2 = Classes.builder()
                    .classType(classType2)
                    .trainer(trainer2)
                    .scheduledDate(LocalDate.of(2025, 10, 20))
                    .startTime(LocalTime.of(18, 30))
                    .endTime(LocalTime.of(20, 0))
                    .maxCapacity(15)
                    .currentCapacity(15)
                    .classStatus(ClassStatus.SCHEDULED)
                    .notes("Evening Massive gainer")
                    .build();


            classRepository.save(classes1);
            classRepository.save(classes2);


        }

    }
}
