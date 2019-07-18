package examples.users;

import java.time.LocalDate;
import java.util.Arrays;

public class PeselService {

    public LocalDate getDateOfBirth(String pesel) {
        Integer year = this.getYear(Integer.parseInt(pesel.substring(0, 2)), Integer.parseInt(pesel.substring(2, 4)));
        Integer month = Integer.parseInt(pesel.substring(2, 4)) % 20;
        Integer day = Integer.parseInt(pesel.substring(4, 6));
        return LocalDate.of(year, month, day);
    }

    public Sex getSex(String pesel) {
        Integer sexDigit = Integer.parseInt(pesel.substring(9, 10));
        return sexDigit % 2 == 0 ? Sex.FEMALE : Sex.MALE;
    }

    public boolean isValid(String pesel) {
        if (pesel.length() != 11) {
            return false;
        }
        Integer[] digits = Arrays.stream(pesel.split("")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer controlValue = (9 * digits[0] + 7 * digits[1] + 3 * digits[2] + digits[3] + 9 * digits[4] + 7 * digits[5] + 3 * digits[6] + digits[7] + 9 * digits[8] + 7 * digits[9]) % 10;
        return controlValue.equals(digits[10]);
    }

    private Integer getYear(Integer yearPart, Integer monthPart) {
        Integer year = yearPart;
        if (monthPart < 20) {
            year += 1900;
        } else if (monthPart < 40) {
            year += 2000;
        } else if (monthPart < 60) {
            year += 2100;
        } else if (monthPart < 80) {
            year += 2200;
        } else {
            year += 1800;
        }
        return year;
    }

}
