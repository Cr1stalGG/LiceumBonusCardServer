package by.grsu.liceum.exception.handler;

import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.AccountWithLoginNotFoundException;
import by.grsu.liceum.exception.ActivityTypeWithIdNotFoundException;
import by.grsu.liceum.exception.ActivityWithIdNotFoundException;
import by.grsu.liceum.exception.BonusWithIdNotFoundException;
import by.grsu.liceum.exception.CardWithIdNotFoundException;
import by.grsu.liceum.exception.CountOfMembersIsExpiredException;
import by.grsu.liceum.exception.GroupWithIdNotFoundException;
import by.grsu.liceum.exception.InstitutionWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidActivityCodeException;
import by.grsu.liceum.exception.InvalidBonusCountException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.exception.InvalidRatingAmountException;
import by.grsu.liceum.exception.InvalidRoleNameException;
import by.grsu.liceum.exception.InvalidTicketCodeException;
import by.grsu.liceum.exception.InvalidTransactionStatusException;
import by.grsu.liceum.exception.NotEnoughBalanceError;
import by.grsu.liceum.exception.NullableAccountCreationDtoException;
import by.grsu.liceum.exception.NullableGroupCreationDtoException;
import by.grsu.liceum.exception.SolvedActivityWithIdNotFoundException;
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
            AccountWithLoginNotFoundException.class,
            InstitutionWithIdNotFoundException.class,
            GroupWithIdNotFoundException.class,
            SolvedActivityWithIdNotFoundException.class
    })
    public ResponseEntity<String> handleNotFoundException(Exception e){
        return new ResponseEntity<>("Not found error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            InvalidBonusCountException.class,
            InvalidTicketCodeException.class,
            InvalidTransactionStatusException.class,
            InvalidRoleNameException.class,
            InvalidRatingAmountException.class,
            InvalidPermissionsException.class,
            InvalidActivityCodeException.class
    })
    public ResponseEntity<String> handleInvalidException(Exception e){
        return new ResponseEntity<>("Invalid error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            NotEnoughBalanceError.class,
            NullableAccountCreationDtoException.class,
            NullableGroupCreationDtoException.class,
            CountOfMembersIsExpiredException.class
    })
    public ResponseEntity<String> handleInvalidOperationException(Exception e){
        return new ResponseEntity<>("Cannot do operation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(Exception e){
        return new ResponseEntity<>("Invalid request body error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
