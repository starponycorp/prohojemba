package com.starpony.prohojemba.verification;

import com.starpony.prohojemba.mail.MailService;
import com.starpony.prohojemba.verification.dto.CreateVerificationDto;
import com.starpony.prohojemba.verification.models.Verification;
import com.starpony.prohojemba.verification.repositories.VerificationRepository;
import com.starpony.prohojemba.verification.utils.VerificationCodeGenerator;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;
import java.util.Optional;


@Service
@Validated
public class VerificationService {
    private final MailService mailService;

    private final VerificationRepository verificationRepository;

    @Autowired
    public VerificationService(MailService mailService, VerificationRepository verificationRepository) {
        this.mailService = mailService;
        this.verificationRepository = verificationRepository;
    }

    public void create(@Valid CreateVerificationDto createVerificationDto) throws ValidationException {
        Verification verification = new Verification(
                createVerificationDto.getEmail(),
                createVerificationDto.getVerificationType(),
                VerificationCodeGenerator.generate());

        verificationRepository.create(verification);
    }

    public boolean verify(Verification verification) {
        Optional<Verification> expectedVerification =
                verificationRepository.get(verification.getType(), verification.getEmail());

        return expectedVerification.isPresent() && Objects.equals(verification, expectedVerification.get());
    }
}
