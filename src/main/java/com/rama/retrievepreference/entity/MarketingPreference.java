package com.rama.retrievepreference.entity;

import com.rama.retrievepreference.dto.GetMarketingPreferenceDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

/**
 * A MarketingPreference.
 */
@Entity
@Setter
@Getter
@ToString
public class MarketingPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    private boolean email;
    private boolean sms;
    private boolean post;

    public MarketingPreference() {
    }

    public Optional<GetMarketingPreferenceDTO> toGetMarketingPreferenceDTO() {
        Optional<GetMarketingPreferenceDTO> optionalGetMarketingPreferenceDTO = Optional.empty();
        if(!ObjectUtils.isEmpty(this)) {
            GetMarketingPreferenceDTO getMarketingPreferenceDTO = GetMarketingPreferenceDTO.builder()
                    .customerId(this.getCustomerId())
                    .id(this.getId())
                    .post(this.isPost())
                    .sms(this.isSms())
                    .email(this.isEmail()).build();
            optionalGetMarketingPreferenceDTO = Optional.of(getMarketingPreferenceDTO);
        }
        return optionalGetMarketingPreferenceDTO;
    }

    /*public Page<GetMarketingPreferenceDTO> toPageable(Page<MarketingPreference> marketingPreferences) {
        return marketingPreferences.map(new Converter<MarketingPreference, GetMarketingPreferenceDTO>() {
            @Override
            public ObjectDto convert(ObjectEntity entity) {
                ObjectDto dto = new ObjectDto();
                // Conversion logic

                return dto;
            }
        });
    }*/
}
