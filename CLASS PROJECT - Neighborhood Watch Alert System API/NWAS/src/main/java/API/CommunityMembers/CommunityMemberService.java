package API.CommunityMembers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CommunityMemberService {

    private final CommunityMemberRepository communityMemberRepository;
    private final List<String> firstNames;
    private final List<String> lastNames;
    private final Random random;

    @Autowired
    public CommunityMemberService(CommunityMemberRepository communityMemberRepository) {
        this.communityMemberRepository = communityMemberRepository;
        this.firstNames = loadNames("first_names.txt");
        this.lastNames = loadNames("last_names.txt");
        this.random = new Random();
    }

    private List<String> loadNames(String fileName) {
        // load names from file and return as a list
        return List.of();
    }

    public CommunityMember addCommunityMember(CommunityMember communityMember) {
        if (communityMember == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Community member object is null");
        }
        // set the 'hasLivedHereSince' attribute to a randomly generated date
        communityMember.setHasLivedHereSince(generateRandomDate());
        return communityMemberRepository.save(communityMember);
    }

    public List<CommunityMember> getAllCommunityMembers() {
        return communityMemberRepository.findAll();
    }

    public List<CommunityMember> getCommunityMembersByAgeRange(int minAge, int maxAge) {
        return communityMemberRepository.findAll().stream()
                .filter(member -> member.getAge() >= minAge && member.getAge() <= maxAge)
                .collect(Collectors.toList());
    }

    public CommunityMember getCommunityMemberById(String id) {
        return communityMemberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Community member not found with ID: " + id));
    }

    public CommunityMember updateCommunityMember(String id, CommunityMember updatedCommunityMember) {
        CommunityMember existingCommunityMember = getCommunityMemberById(id);
        if (updatedCommunityMember == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Updated community member details were not provided");
        }
        if (updatedCommunityMember.getName() != null) {
            existingCommunityMember.setName(updatedCommunityMember.getName());
        }
        if (updatedCommunityMember.getSurname() != null) {
            existingCommunityMember.setSurname(updatedCommunityMember.getSurname());
        }
        if (updatedCommunityMember.getAge() > 0) {
            existingCommunityMember.setAge(updatedCommunityMember.getAge());
        }
        if (updatedCommunityMember.getAddressNumber() != null) {
            existingCommunityMember.setAddressNumber(updatedCommunityMember.getAddressNumber());
        }
        if (updatedCommunityMember.getPhoneNumber() != null) {
            existingCommunityMember.setPhoneNumber(updatedCommunityMember.getPhoneNumber());
        }
        if (updatedCommunityMember.getHasLivedHereSince() != null) {
            existingCommunityMember.setHasLivedHereSince(updatedCommunityMember.getHasLivedHereSince());
        }

        return communityMemberRepository.save(existingCommunityMember);
    }


    public void deleteCommunityMember(String id) {
        communityMemberRepository.deleteById(id);
    }

    // generates a random date representing 'has lived here since'
    private LocalDate generateRandomDate() {
        // generates a random date between 1 and 20 years ago
        long minDay = LocalDate.now().minusYears(20).toEpochDay();
        long maxDay = LocalDate.now().minusYears(1).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public CommunityMember generateRandomCommunityMember() {
        String firstName = getRandomItem(firstNames);
        String lastName = getRandomItem(lastNames);
        int age = random.nextInt(51) + 20; // age between 20 and 70
        String addressNumber = String.valueOf(random.nextInt(9999) + 1); // address number between 1 and 9999
        String phoneNumber = generateRandomPhoneNumber();
        LocalDate hasLivedHereSince = LocalDate.now().minusYears(random.nextInt(20) + 1);

        return new CommunityMember(firstName, lastName, age, addressNumber, phoneNumber, hasLivedHereSince);
    }

    private String generateRandomPhoneNumber() {
        return String.format("%03d-%03d-%04d", random.nextInt(1000), random.nextInt(1000), random.nextInt(10000));
    }

    private <T> T getRandomItem(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    public List<CommunityMember> addMultipleCommunityMembers(List<CommunityMember> members) {
        return communityMemberRepository.saveAll(members);
    }

    public List<CommunityMember> updateMultipleCommunityMembers(List<CommunityMember> members) {
        List<CommunityMember> updatedMembers = new ArrayList<>();
        for (CommunityMember member : members) {
            Optional<CommunityMember> existingMemberOptional = communityMemberRepository.findById(String.valueOf(member.getId()));
            if (existingMemberOptional.isPresent()) {
                CommunityMember existingMember = existingMemberOptional.get();
                existingMember.setPhoneNumber(member.getPhoneNumber());
                updatedMembers.add(communityMemberRepository.save(existingMember));
            }
        }
        return updatedMembers;
    }


    public void deleteMultipleCommunityMembers(List<String> ids) {
        ids.forEach(communityMemberRepository::deleteById);
    }
}
