package com.tarabut.retrievepreference.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
