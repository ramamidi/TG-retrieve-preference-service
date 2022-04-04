package com.rama.retrievepreference.controller;

import com.rama.retrievepreference.dto.GetMarketingPreferenceDTO;
import com.rama.retrievepreference.entity.MarketingPreference;
import com.rama.retrievepreference.service.impl.MarketingPreferenceServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class MarketingPreferenceControllerTest {

    @Mock
    private MarketingPreferenceServiceImpl marketingPreferenceService;
    @Mock
    private MessageSource messageSource;
    @Mock
    private LocalValidatorFactoryBean validator;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MarketingPreferenceController marketingPreferenceController =
                new MarketingPreferenceController(marketingPreferenceService);
        mockMvc =
                MockMvcBuilders.standaloneSetup(marketingPreferenceController).build();
    }

    @Test
    public void shouldAbleToGetQuestions() throws Exception {
        // Arrange
        MarketingPreference marketingPreference = new MarketingPreference();
        marketingPreference.setId(1);
        marketingPreference.setCustomerId(1);
        marketingPreference.setEmail(true);
        marketingPreference.setPost(true);
        marketingPreference.setSms(true);
        when(marketingPreferenceService.findOne(eq(1))).thenReturn(marketingPreference.toGetMarketingPreferenceDTO());

        // Act
        MvcResult mockMvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/marketing-preferences/1")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is(true)))
                .andExpect(jsonPath("$.post", is(true)))
                .andExpect(jsonPath("$.sms", is(true))).andReturn();

        // Assert
        Assertions.assertThat(mockMvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    public void shouldThrowExceptionWhenNotAbleToFind() throws Exception {
        // Arrange
        when(marketingPreferenceService.findOne(eq(1))).thenReturn(Optional.empty());

        // Act
        MvcResult mockMvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/marketing-preferences/1").accept("application/json"))
                        .andExpect(status().is(404))
                        .andReturn();

        // Assert
        Assertions.assertThat(mockMvcResult.getResponse().getStatus()).isEqualTo(404);
    }

    @Test
    public void shouldAbleToGetMarketingPreferenceWithPageable() throws Exception {
        // Arrange
        MarketingPreference marketingPreference = new MarketingPreference();
        marketingPreference.setId(1);
        marketingPreference.setCustomerId(1);
        marketingPreference.setEmail(true);
        marketingPreference.setPost(true);
        marketingPreference.setSms(true);
        List<GetMarketingPreferenceDTO> marketingPreferenceList =
                Arrays.asList(marketingPreference.toGetMarketingPreferenceDTO().get());
        Page<GetMarketingPreferenceDTO> page = new PageImpl<>(marketingPreferenceList);
        when(marketingPreferenceService.findAll(any())).thenReturn(page);

        // Act
        MvcResult mockMvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/marketing-preferences?page=0&size=10"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isArray())
                        .andExpect(jsonPath("$", hasSize(1)))
                        .andExpect(jsonPath("$[0].id", is(notNullValue())))
                        .andExpect(jsonPath("$[0].id", is(1)))
                        .andExpect(jsonPath("$[0].email", is(true)))
                        .andExpect(jsonPath("$[0].post", is(true)))
                        .andExpect(jsonPath("$[0].sms", is(true)))
                        .andExpect(content().contentType("application/json")).andReturn();

        // Assert
        Assertions.assertThat(mockMvcResult.getResponse().getStatus()).isEqualTo(200);
    }
}