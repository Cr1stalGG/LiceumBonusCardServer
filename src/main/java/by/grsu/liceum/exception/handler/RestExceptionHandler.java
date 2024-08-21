package by.grsu.liceum.exception.handler;

import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.AccountWithLoginNotFoundException;
import by.grsu.liceum.exception.ActivityTypeWithIdNotFoundException;
import by.grsu.liceum.exception.ActivityWithIdNotFoundException;
import by.grsu.liceum.exception.BonusWithIdNotFoundException;
import by.grsu.liceum.exception.CardWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidBonusCountException;
import by.grsu.liceum.exception.InvalidTicketCodeException;
import by.grsu.liceum.exception.InvalidTransactionStatusException;
import by.grsu.liceum.exception.NotEnoughBalanceError;
import by.grsu.liceum.exception.NullableAccountCreationDtoException;
import by.grsu.liceum.exception.TicketWithIdNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {
            AccountWithIdNotFoundException.class,
            ActivityWithIdNotFoundException.class,
            ActivityTypeWithIdNotFoundException.class,
            BonusWithIdNotFoundException.class,
            CardWithIdNotFoundException.class,
            TicketWithIdNotFoundException.class,
            AccountWithLoginNotFoundException.class
    })
    public ResponseEntity<String> handleNotFoundException(Exception e){
        return new ResponseEntity<>("Not found error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            InvalidBonusCountException.class,
            InvalidTicketCodeException.class,
            InvalidTransactionStatusException.class
    })
    public ResponseEntity<String> handleInvalidException(Exception e){
        return new ResponseEntity<>("Invalid error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            NotEnoughBalanceError.class,
            NullableAccountCreationDtoException.class
    })
    public ResponseEntity<String> handleInvalidOperationException(Exception e){
        return new ResponseEntity<>("Cannot do operation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(Exception e){
        return new ResponseEntity<>("Invalid request body error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
