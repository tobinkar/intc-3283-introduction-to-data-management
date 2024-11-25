package edu.northwestu.intc3283.datasourcestarter.util;

import com.github.javafaker.Faker;
import edu.northwestu.intc3283.datasourcestarter.entity.Donation;
import edu.northwestu.intc3283.datasourcestarter.entity.Donor;
import edu.northwestu.intc3283.datasourcestarter.repository.DonationsRepository;
import edu.northwestu.intc3283.datasourcestarter.repository.DonorsRepository;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class DataGeneratorService {

    private final DonorsRepository donorsRepository;
    private final DonationsRepository donationsRepository;
    private final Random random = new Random();
    private final Faker faker = new Faker();

    public DataGeneratorService(DonorsRepository donorsRepository, DonationsRepository donationsRepository) {
        this.donorsRepository = donorsRepository;
        this.donationsRepository = donationsRepository;
    }

    public void generateRandomDonorsAndDonations(int numDonors, int maxDonationsPerDonor) {
        IntStream.range(0, numDonors).forEach(i -> {
            // Create and save a random donor using Faker
            Donor donor = new Donor();
            donor.setFirstName(faker.name().firstName());
            donor.setLastName(faker.name().lastName());
            donor.setEmail(faker.internet().emailAddress());
            donor.setAddress1(faker.address().streetAddress());
            donor.setAddress2(faker.address().secondaryAddress());
            donor.setCity(faker.address().city());
            donor.setState(faker.address().stateAbbr());
            donor.setZipCode(faker.address().zipCode());
            donor.setPhone(faker.phoneNumber().cellPhone().substring(0, 12));
            donorsRepository.save(donor);



            // Generate a random number of donations for this donor
            int numDonations = random.nextInt(maxDonationsPerDonor) + 1;
            IntStream.range(0, numDonations).forEach(j -> {
                Donation donation = new Donation();
                donation.setDonor(AggregateReference.to(donor.getId()));
                donation.setAmount(generateRandomAmount());
                String quote = faker.yoda().quote();
                donation.setCreatedAt(generateRandomDate());
                donationsRepository.save(donation);
                donation.setCreatedAt(
                        faker
                                .date()
                                .between(
                                        Date.from(Instant.now().minus(Duration.ofDays(90L))),
                                        Date.from(Instant.now())
                                )
                                .toInstant());
                this.donationsRepository.save(donation);

            });
        });
    }

    // Utility method for generating a random date
    private Instant generateRandomDate() {
        // Generate a random year between 2010 and 2023
        int randomYear = random.nextInt(2024 - 2010) + 2010;

        // Generate a random month between 1 and 12
        int randomMonth = random.nextInt(12) + 1;

        // Use YearMonth to get the maximum valid day for the generated month and year
        YearMonth yearMonth = YearMonth.of(randomYear, randomMonth);
        int maxDay = yearMonth.lengthOfMonth();

        // Generate a random day between 1 and the maximum valid day for that month/year
        int randomDay = random.nextInt(maxDay) + 1;

        // Generate a random hour, minute, and second
        int randomHour = random.nextInt(24);
        int randomMinute = random.nextInt(60);
        int randomSecond = random.nextInt(60);

        // Build the random date using LocalDateTime
        LocalDateTime randomDateTime = LocalDateTime.of(randomYear, randomMonth, randomDay, randomHour, randomMinute, randomSecond);

        // Convert LocalDateTime to Instant
        return randomDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    // Utility method for generating a random donation amount
    private Integer generateRandomAmount() {
        return random.nextInt(10, 1000);
    }
}