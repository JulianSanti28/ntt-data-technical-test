package com.ntt.data.app.rest;

import com.ntt.data.app.business.CustomerBusinessImpl;
import com.ntt.data.app.config.MessageLoader;
import com.ntt.data.app.domain.CustomerDto;
import com.ntt.data.app.domain.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CustomerRestTest {
    private static final String ENDPOINT = "/customer";

    @Mock
    CustomerBusinessImpl customerBusiness;
    @InjectMocks
    private CustomerRest customerRest;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.customerRest = new CustomerRest(customerBusiness);
        mockMvc = MockMvcBuilders.standaloneSetup(customerRest)
                .build();
    }

    @Test
    void getCustomerByDocumentTest() throws Exception {
        final var DOCUMENT = "1";
        ResponseDto<CustomerDto> response = new ResponseDto<>(HttpStatus.OK.value(), MessageLoader.getInstance().getMessage("IM001"));
        when(this.customerBusiness.getByDocumentNumber(DOCUMENT)).thenReturn(response);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(ENDPOINT)
                .param("documentNumber", DOCUMENT);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}

