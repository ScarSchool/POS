package janssen.spring;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

public class Utils {

  public static void exceptionFromValidationResult(BindingResult validationResult) {
    if (validationResult.hasErrors()) {
      final String message = validationResult.getFieldErrors().stream()
        .map(err -> String.format("- %s: %s", err.getField(), err.getDefaultMessage()))
        .reduce((a, b) -> a + "\n" + b)
        .get();

      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }
  }

}
