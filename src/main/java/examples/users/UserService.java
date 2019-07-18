package examples.users;

public class UserService {

    private final PeselService peselService;
    private final DateService dateService;

    public UserService(PeselService peselService, DateService dateService) {
        this.peselService = peselService;
        this.dateService = dateService;
    }

    public void validateUserData(User user) {
        if (!peselService.isValid(user.pesel)) {
            throw new IllegalArgumentException("Pesel is invalid");
        }
        if (!peselService.getDateOfBirth(user.pesel).equals(user.dateOfBirth)) {
            throw new IllegalArgumentException("Date of birth is inconsistent with pesel");
        }
        if (!peselService.getSex(user.pesel).equals(user.sex)) {
            throw new IllegalArgumentException("Sex is inconsistent with pesel");
        }
        if (user.dateOfBirth.isAfter(this.dateService.today())) {
            throw new IllegalArgumentException("Date of birth is after today");
        }
    }

}
