package ryan.fighting.infra.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ToString
public class CommonResponse {

    public enum Code{
        SUCCESS("0000", "success"),

        // ACCOUNT
        LOGIN_REQUIRED("1000", "Need to login"),
        PROFILE_QUIZ_REQUIRED("1001", "Need to take profile quiz"),
        EMAIL_PASSWORD_MISMATCH("1002", "Email and password you entered didn't match"),
        USED_EMAIL("1003","Email already used"),
        USER_NOT_EXIST("1004","User not exist"),
        INVALID_PASSWORD("1005", "Password is not valid"),
        INAPPROPRIATE_USER("1006", "Inappropriate user"),
        NOT_FOUND_PROVIDER("1007" , "not found provider"),
        NOT_ORDERDETAIL("1101", "Not found Order Detail"),

        // CART
        CART_EXCEPTION("1201" , "Failed Cart Work"),

        // WISHLIST
        TOO_MANY_WISH_LIST("1301", "You can not make more than 5 wish list"),
        TOO_MANY_WISH_LIST_ITEM("1301","30 Max items in wish list"),
        PRODUCT_ALREADY_EXIST("1302", "Selected item has been added to your wish list"),
        PRODUCT_EXCEPTION("1303", "Product exception - execute"),

        // VISIT
        VISIT_EXCEPTION("1401" , "Visitor Process Error"),

        // REVIEW
        REVIEW_EXCEPTION("1501" , "Sorry Review Error"),
        NOT_FOUND_REVIEW("1502" , "Not Exist Review"),
        REVIEW_UPDATE_FAILED("1503" , "Update Review Failed"),

        // CHECKOUT
        NOT_ENOUGH_STOCK("3000", "Some of your requested items are either sold out or low on stock."),
        INVALID_PROMOTION("3101", "Code already used or Invalid Code"),
        NOT_AVAILABLE_ORDER("3301", "Your orders is already placed. Don't click PAY twice."),
        NOT_AVAIABLE_PAYMENT("3501", "Requested payment method is not available or empty"),
        NOT_AVAIABLE_AMOUNT("3502", "impossible Checkout amount"),
        NOT_INPUT_PHONENUMBER("3503", "Not Input Phone Number"),

        // EXCEPTION
        PAYMENT_EXCEPTION("3801", "Payment exception"),
        ACCOUNT_EXCEPTION("3802", "Account exception"),
        ADDRESS_EXCEPTION("3803", "Address exception"),
        MAIN_EXCEPTION("3804", "Main exception"),
        FIN_EXCEPTION("3805" , "Fin Process Failed"),
        USER_STYLE_EXCEPTION("3805", "Error User Style - please Check Login or Profile"),
        WISHLIST_EXCEPTION("3806", "WishList exception"),
        CHECKOUT_EXCEPTION("3807", "Checkout exception"),
        CONTENT_EXCEPTION("3808", "Checkout exception"),
        PAYPAL_EXCEPTION("3809", "Failed Paypal Checkout"),

        DOPAY_FAIL_EXCEPTION("3302" , "Payment id Failed"),

        //REQUEST,
        BAD_REQUEST("4000", "Bad request"),
        ACCESS_DENIED("4003", "Access is denied"),

        // PAYPAL
        PAYPAL_ADDRESS_EXCEPTION("5010" , "Paypal address null"),

        // IMAGE
        IMAGEUPLOAD_FAILED("6001" , "Image Upload Failed"),
        IMAGEDELETE_FAILED("6001" , "Image Delete Failed"),

        //SERVER
        SERVER_ERROR("8000", "Server error"),
        EXCEPTION("8888", "Exception"),
        NO_DATA("9999", "No Data");

        private String codeNumber;
        private String codeMessage;

        Code(String codeNumber, String codeMessage){
            this.codeNumber = codeNumber;
            this.codeMessage = codeMessage;
        }

        public String getCodeNumber(){ return codeNumber; }
        public String getCodeMessage(){ return codeMessage; }
    }

    @JsonProperty("rCode")
    private String rcode;

    @JsonProperty("rMessage")
    private String rmessage;

    @JsonProperty("rData")
    private Map<?, ?> rdata;

    public CommonResponse(){
        this.rcode = "RET" + Code.SERVER_ERROR.codeNumber;
        this.rmessage = Code.SERVER_ERROR.codeMessage;
        this.rdata = null;
    }

    public CommonResponse(Code code, Map<?, ?> rdata){
        this.rcode = "RET" + code.codeNumber;
        this.rmessage = code.codeMessage;
        this.rdata = rdata;
    }

    public void setCode(String code , String message){
        this.rcode = "RET" + code;
        this.rmessage = message;
    }

    public void setCode(Code code){
        this.rcode = "RET" + code.codeNumber;
        this.rmessage = code.codeMessage;
    }

    public void setRdata(Map<?, ?> rdata) {
        this.rdata = rdata;
    }

    public void setRmessage(String rmessage){ this.rmessage = rmessage; }
}
