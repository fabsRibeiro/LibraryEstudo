package com.library.libraryapi.service;

import com.library.libraryapi.model.entity.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private static final String CRON_LATE_LOANS = "0 0 0 1/1 * ?";

    @Value("${application.mail.lateloans.message}")
    private String mensagem;
    private final LoanService loanService;
    private final EmailService emailService;


    @Scheduled(cron = CRON_LATE_LOANS)
    public void sendMailToLateLoans(){
        List<Loan> allLateLoans = loanService.getAllLateLoans();
        List<String> mainList = allLateLoans.stream()
                .map(loan -> loan.getCustomerEmail())
                .collect(Collectors.toList());

        String msg = "Atenção! Você tem um emprestimo atrasado, favor devolver o livro o mais rápido possível!";

        emailService.sendMails(mensagem, mainList);

    }
}
