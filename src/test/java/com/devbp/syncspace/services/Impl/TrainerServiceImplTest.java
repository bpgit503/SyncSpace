package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.config.TestConfig;
import com.devbp.syncspace.domain.dtos.CreateTrainerRequest;
import com.devbp.syncspace.domain.dtos.UpdateTrainerRequestDto;
import com.devbp.syncspace.domain.entities.Trainer;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.repositories.TrainerRepository;
import com.devbp.syncspace.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class TrainerServiceImplTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    private final static String EMAIL = "abc@email.com";

    private User userOfTypeTrainer;
    private Trainer trainer;
    private Trainer updateTrainer;
    private CreateTrainerRequest createTrainerRequest;
    private UpdateTrainerRequestDto updateTrainerRequestDto;

    private TestConfig testConfig;


    @BeforeEach
    void setUp() {
        testConfig = new TestConfig();
        userOfTypeTrainer = testConfig.createUserOfTypeTrainer();
        trainer = testConfig.createTrainer();
        createTrainerRequest = testConfig.createCreateTrainerRequest();
    }

    @Nested
    @DisplayName("Create Trainer Tests")
    class createTrainerTests {


        @Test
        void shouldCreateTrainerSuccessfully() {
            //given
            when(userRepository.findUserByEmail(userOfTypeTrainer.getEmail()))
                    .thenReturn(Optional.of(userOfTypeTrainer));

            when(trainerRepository.save(any(Trainer.class))).thenReturn(trainer);

            //when
            Trainer newTrainer = trainerService.addTrainer(userOfTypeTrainer.getEmail(), createTrainerRequest);

            //then
            assertNotNull(newTrainer);
            assertEquals(trainer, newTrainer);
            assertEquals(trainer.getId(), newTrainer.getId());
            assertEquals(userOfTypeTrainer, newTrainer.getUser());
            assertEquals(trainer.getHourlyRate(), newTrainer.getHourlyRate());
            verify(userRepository, times(1)).findUserByEmail(userOfTypeTrainer.getEmail());
            verify(trainerRepository, times(1)).save(trainer);

        }
    }
}