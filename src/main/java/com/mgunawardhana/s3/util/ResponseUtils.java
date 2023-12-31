package com.mgunawardhana.s3.util;

import com.mgunawardhana.s3.constant.AppConstants;
import com.mgunawardhana.s3.constant.StatusCode;
import com.mgunawardhana.s3.constant.StatusMessage;
import com.mgunawardhana.s3.domain.APIResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class ResponseUtils {

    private final HttpServletRequest servletRequest;

    public ResponseUtils(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    /**
     * This method is used to wrap a successful response into an APIResponse object and return it as a ResponseEntity.
     *
     * @param value      This is the value that needs to be included in the response.
     * @param httpStatus This is the HTTP status that needs to be set for the response.
     * @return ResponseEntity<APIResponse> This returns the response wrapped in a ResponseEntity with the provided HTTP status.
     */
    public ResponseEntity<APIResponse> wrapSuccess(Object value, HttpStatus httpStatus) {
        APIResponse apiResponse = APIResponse.builder()
                .statusCode(StatusCode.SUCCESS.valueOf())
                .transactionId(servletRequest.getHeader(AppConstants.APP_TRACE_ID))
                .origin(servletRequest.getRequestURI()).statusMessage(StatusMessage.SUCCESS.valueOf())
                .responseTime(DateTimeUtils.format(new Date())).result(value).build();
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    /**
     * This method is used to wrap an error response into an APIResponse object and return it as a ResponseEntity.
     *
     * @param value      This is the value that needs to be included in the response.
     * @param errorType  This is the type of error that occurred.
     * @param httpStatus This is the HTTP status that needs to be set for the response.
     * @return ResponseEntity<APIResponse> This returns the error response wrapped in a ResponseEntity with the provided HTTP status.
     */
    public ResponseEntity<APIResponse> wrapError(Object value, String errorType, HttpStatus httpStatus) {
        APIResponse apiResponse = APIResponse.builder()
                .statusCode(StatusCode.FAILURE.valueOf())
                .transactionId(servletRequest.getHeader(AppConstants.APP_TRACE_ID))
                .statusMessage(StatusMessage.FAILURE.valueOf()).errorType(errorType)
                .origin(servletRequest.getRequestURI()).responseTime(DateTimeUtils.format(new Date()))
                .result(Collections.singletonMap(AppConstants.ERROR, value)).build();

        return ResponseEntity.status(httpStatus).body(apiResponse);
    }
}
