package com.mgunawardhana.s3.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Created By - mgunawardhana
 * Date - 2023-12-31
 * Time - 17.55
 */
@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {
    private String statusMessage;
    private String statusCode;
    private String transactionId;
    private String responseTime;
    private String origin;
    private String errorType;
    private Object result;
}
