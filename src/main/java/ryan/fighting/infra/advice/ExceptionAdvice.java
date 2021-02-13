package ryan.fighting.infra.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ryan.fighting.infra.advice.exception.GlobalException;
import ryan.fighting.module.domain.util.CommonResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = {
        "ryan.fighting.module"
})
@RequiredArgsConstructor
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(value= GlobalException.class)
    protected ResponseEntity Exception(GlobalException exception , HttpServletRequest request){
        CommonResponse response = new CommonResponse();
        Map<String,Object> rData = new HashMap<>();

        response.setCode(exception.getErrorCode() , exception.getMessage());

        printError(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private void printError(GlobalException exception) {
        log.error(" \n\n === === === === === === === === === ERROR === === === === === === === === === \n\n"
                +         "Error Code : " + exception.getErrorCode() + " / " + exception.getMessage() + "\n"
                +         exception.getStacktrace()
                +         " \n === === === === === === === === === === === === === === === === === === === \n");
    }

}
