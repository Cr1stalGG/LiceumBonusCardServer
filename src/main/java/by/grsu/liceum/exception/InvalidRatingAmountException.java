package by.grsu.liceum.exception;

public class InvalidRatingAmountException extends RuntimeException {
    public InvalidRatingAmountException(int minRatingValue, int maxRatingValue, int value) {
        super(String.format("Invalid rating amount:{expected from: %s to: %s, actual: %s}", minRatingValue, maxRatingValue, value));
    }
}
