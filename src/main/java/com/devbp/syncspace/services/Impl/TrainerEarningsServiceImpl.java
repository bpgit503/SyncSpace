package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.ClassStatus;
import com.devbp.syncspace.domain.PaymentStatus;
import com.devbp.syncspace.domain.TrainerPaymentStatus;
import com.devbp.syncspace.domain.dtos.CreateTrainerEarningRequest;
import com.devbp.syncspace.domain.dtos.UpdateTrainerEarningRequest;
import com.devbp.syncspace.domain.entities.Booking;
import com.devbp.syncspace.domain.entities.Classes;
import com.devbp.syncspace.domain.entities.Trainer;
import com.devbp.syncspace.domain.entities.TrainerEarnings;
import com.devbp.syncspace.exceptions.InvalidTrainerEarningsException;
import com.devbp.syncspace.exceptions.InvalidUserTypeException;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.*;
import com.devbp.syncspace.services.TrainerEarningsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerEarningsServiceImpl implements TrainerEarningsService {

    private final TrainerEarningsRepository trainerEarningsRepository;
    private final TrainerRepository trainerRepository;
    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Override
    public List<TrainerEarnings> getAllTrainerEarnings() {
        return trainerEarningsRepository.findAll();
    }

    @Override
    public List<TrainerEarnings> getAllTrainerEarningsByTrainerID(long id) {
        if (!trainerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trainer not found with id: " + id);
        }
        return trainerEarningsRepository.findAllByTrainerId(id);
    }

    @Override
    public List<TrainerEarnings> getAllTrainerEarningsByClassName(String className) {
        return trainerEarningsRepository.findAllByClazzClassType_ClassName(className);
    }

    @Override
    public TrainerEarnings getTrainerEarningById(long id) {
        return trainerEarningsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer earnings not found with id: " + id));
    }

    @Transactional
    @Override
    public TrainerEarnings createTrainerEarning(CreateTrainerEarningRequest requestDto) {
        Trainer trainer = trainerRepository.findById(requestDto.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + requestDto.getTrainerId(), "Trainer Earnings"));

        if (!userRepository.existsById_AndUserType_Trainer(trainer.getUser().getId())) {
            throw new InvalidUserTypeException("User is not of type trainer", "TrainerEarnings");
        }

        Classes clazz = classRepository.findById(requestDto.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + requestDto.getClassId(), "Trainer Earnings"));

        if (clazz.getTrainer().getId() != trainer.getId()) {
            throw new InvalidTrainerEarningsException("Given Trainer is not assigned to the given class.", "TrainerEarnings");
        }

        if (clazz.getClassStatus() != ClassStatus.COMPLETED) {
            throw new InvalidTrainerEarningsException("Class is not completed yet", "TrainerEarnings");
        }

        TrainerEarnings trainerEarnings = new TrainerEarnings();
        trainerEarnings.setTrainer(trainer);
        trainerEarnings.setClazz(clazz);
        trainerEarnings.setBaseAmount(requestDto.getBaseAmount() != null
                ? requestDto.getBaseAmount()
                : 0);
        trainerEarnings.setEarningPercentage(requestDto.getEarningPercentage() != null
                ? requestDto.getEarningPercentage()
                : trainer.getEarningsPercentage());

        trainerEarnings.setPaymentStatus(TrainerPaymentStatus.PENDING);

        calculateTotalEarningAmount(clazz, trainerEarnings);
        trainerEarnings.setCalculatedAt(LocalDateTime.now());

        return trainerEarningsRepository.save(trainerEarnings);
    }

    @Override
    public TrainerEarnings updateTrainerEarningPaymentStatus(long id) {
        TrainerEarnings trainerEarning = getTrainerEarningById(id);

        trainerEarning.setPaymentStatus(TrainerPaymentStatus.PAID);
        trainerEarning.setPaymentDate(LocalDateTime.now());

        return trainerEarningsRepository.save(trainerEarning);
    }

    @Transactional
    @Override
    public TrainerEarnings recalculateTrainerEarningPercentage(UpdateTrainerEarningRequest requestDto) {
        TrainerEarnings trainerEarning = getTrainerEarningById(requestDto.getTrainerEarningsId());
        Classes clazz = classRepository.findById(trainerEarning.getClazz().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + trainerEarning.getClazz().getId(), "TrainerEarnings"));

        trainerEarning.setEarningPercentage(requestDto.getEarningPercentage());
        calculateTotalEarningAmount(clazz, trainerEarning);

        return trainerEarningsRepository.save(trainerEarning);
    }

    @Override
    public void deleteTrainerEarningById(long id) {

        TrainerEarnings trainerEarnings = getTrainerEarningById(id);

        trainerEarningsRepository.delete(trainerEarnings);

    }

    private void calculateTotalEarningAmount(Classes clazz, TrainerEarnings trainer) {
        List<Booking> allBookings = bookingRepository.findAllByClazzId(clazz.getId());

        List<Booking> pendingBookings = allBookings.stream()
                .filter(b -> b.getPaymentStatus() == PaymentStatus.PENDING)
                .toList();

        if (!pendingBookings.isEmpty()) {
            throw new InvalidTrainerEarningsException("Unable to calculate Trainers Earnings as the class has pending payments", "TrainerEarnings");
        }

        BigDecimal totalPrice = allBookings.stream()
                .filter(b -> b.getPaymentStatus() == PaymentStatus.PAID)
                .map(Booking::getPricePaid)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        trainer.setEarningAmount(totalPrice.multiply(BigDecimal.valueOf(trainer.getEarningPercentage())));

    }
}
