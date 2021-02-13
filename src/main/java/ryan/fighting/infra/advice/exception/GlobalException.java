package ryan.fighting.infra.advice.exception;

import lombok.Getter;
import lombok.Setter;
import ryan.fighting.infra.util.CommonResponse;

@Getter
@Setter
public class GlobalException extends RuntimeException {

    private String errorCode;
    private String exceptionClassName;
    boolean isDisplatableMsg;
    private String message;
    private String stacktrace;

    public GlobalException(CommonResponse.Code code , Throwable exception) {
        errorCode = code.getCodeNumber();
        message = code.getCodeMessage();

        isDisplatableMsg = false;

        String stacktrace = "";
        for (StackTraceElement ste : exception.getStackTrace()) {
            stacktrace += (ste.toString() + " / \n");
        }
        this.stacktrace = stacktrace;
    }

}
