package edu.northwestu.intc3283.datasourcestarter.controller;

import edu.northwestu.intc3283.datasourcestarter.repository.DonorsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;

@Controller
@RequestMapping("/reports")
public class ReportsController {

    private final DonorsRepository donorsRepository;

    public ReportsController(DonorsRepository donorsRepository) { this.donorsRepository = donorsRepository; }

    @GetMapping("/weekly-donations")
    public String weeklyDonationReport(Model model) {
        LocalDate startDate = LocalDate.now().minusWeeks(6);
        LocalDate endDate = LocalDate.now();

        model.addAttribute("report", this.donorsRepository
                .weeklyDonationReport(startDate, endDate));

        return "donors/weeklydonations";
    }

    @GetMapping("/monthly-donations")
    public String monthlyDonationReport(Model model) {
        LocalDate startDate = LocalDate.now().minusMonths(3);
        LocalDate endDate = LocalDate.now();

        model.addAttribute("report", this.donorsRepository
                .monthlyDonationReport(startDate, endDate));

        return "donors/monthlydonations";
    }
}
