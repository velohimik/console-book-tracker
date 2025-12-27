package example.validator;

public class UserInputValidator {

    private static final int EXPECTED_YEAR_LENGTH = 4;

    public boolean validateStringIsBlank(String text) {
        return text.isBlank();
    }

    public boolean validateYearIsFourDigits(String year) {
        for (Character letter : year.toCharArray()) {
            if (!Character.isDigit(letter)) {
                return false;
            }
        }

        if (year.charAt(0) == '0') return false;

        return year.length() == EXPECTED_YEAR_LENGTH;
    }

    public boolean validateIdIsNotNumeric(String bookId) {
        for (Character letter : bookId.toCharArray()) {
            if (!Character.isDigit(letter)) {
                return true;
            }
        }

        return false;
    }
}
