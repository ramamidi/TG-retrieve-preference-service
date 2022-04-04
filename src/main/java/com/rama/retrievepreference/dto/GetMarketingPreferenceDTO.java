package com.rama.retrievepreference.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMarketingPreferenceDTO {

    private Integer id;
    private Integer customerId;
    private boolean email;
    private boolean sms;
    private boolean post;
}
