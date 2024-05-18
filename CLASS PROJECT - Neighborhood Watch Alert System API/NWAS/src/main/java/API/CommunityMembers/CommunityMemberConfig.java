package API.CommunityMembers;

import API.Alerts.AlertService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Configuration
public class CommunityMemberConfig {

    private final CommunityMemberRepository communityMemberRepository;
    private final AlertService alertService;
    private final List<String> firstNames;
    private final List<String> lastNames;
    private final Random random;

    public CommunityMemberConfig(CommunityMemberRepository communityMemberRepository, AlertService alertService) {
        this.communityMemberRepository = communityMemberRepository;
        this.alertService = alertService;
        this.firstNames = loadNames("first_names.txt");
        this.lastNames = loadNames("last_names.txt");
        this.random = new Random();
    }

    // Load names from files
    private List<String> loadNames(String fileName) {
        List<String> names = new ArrayList<>();
        try {
            Path filePath = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());
            names.addAll(Files.readAllLines(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Error loading names from file: " + fileName, e);
        }
        return names;
    }

    // Command line runner to generate community members
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            List<CommunityMember> members = generateRandomCommunityMembers();
            members.forEach(member -> {
                communityMemberRepository.save(member);
                alertService.createAlertFromCommunityMember(member);
            });
        };
    }

    // Generate random community members
    private List<CommunityMember> generateRandomCommunityMembers() {
        List<CommunityMember> members = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String firstName = getRandomItem(firstNames);
            String lastName = getRandomItem(lastNames);
            int age = random.nextInt(51) + 20; // age between 20 and 70
            String addressNumber = String.valueOf(random.nextInt(9999) + 1); // address number between 1 and 9999
            String phoneNumber = generateRandomPhoneNumber();
            // Generating a random date
            LocalDate hasLivedHereSince = LocalDate.now().minusYears(random.nextInt(20) + 1);

            CommunityMember member = new CommunityMember(firstName, lastName, age, addressNumber, phoneNumber, hasLivedHereSince);
            members.add(member);
        }
        return members;
    }

    // Generate random phone number
    private String generateRandomPhoneNumber() {
        return String.format("%03d-%03d-%04d", random.nextInt(1000), random.nextInt(1000), random.nextInt(10000));
    }

    // Get a random item from a list
    private <T> T getRandomItem(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
