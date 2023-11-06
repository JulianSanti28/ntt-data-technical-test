package com.ntt.data.app.business;

import com.ntt.data.app.domain.CustomerDto;
import com.ntt.data.app.domain.ResponseDto;
import com.ntt.data.app.exception.BusinessRuleException;
import com.ntt.data.app.mapper.ICustomerMapper;
import com.ntt.data.app.model.Customer;
import com.ntt.data.app.repository.ICustomerRepository;
import com.ntt.data.app.util.MessagesConstants;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CustomerBusinessImplTest {

    @InjectMocks
    private CustomerBusinessImpl customerBusiness;
    @Mock
    private ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper = Mappers.getMapper(ICustomerMapper.class);

    @Test
    void getCustomerBadRequestUserNotFoundTest() {
        final var DOCUMENT_NUMBER = "1";
        Mockito.when(this.customerRepository.findByDocumentNumber(DOCUMENT_NUMBER)).thenReturn(Optional.empty());
        BusinessRuleException response = Assertions.assertThrows(BusinessRuleException.class, () -> {
            this.customerBusiness.getByDocumentNumber(DOCUMENT_NUMBER);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Assertions.assertEquals(MessagesConstants.EM001, response.getErrorCode());
    }

    @Test
    void getCustomerBadRequestEmptyDocumentNumberTest() {
        final var DOCUMENT_NUMBER = StringUtils.EMPTY;
        BusinessRuleException response = Assertions.assertThrows(BusinessRuleException.class, () -> {
            this.customerBusiness.getByDocumentNumber(DOCUMENT_NUMBER);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Assertions.assertEquals(MessagesConstants.EM002, response.getErrorCode());
    }

    @Test
    void getCustomerSuccess() {
        ReflectionTestUtils.setField(customerBusiness, "customerMapper", customerMapper);
        final var DOCUMENT_NUMBER = "1";
        Mockito.when(this.customerRepository.findByDocumentNumber(DOCUMENT_NUMBER)).thenReturn(Optional.of(new Customer()));
        ResponseDto<CustomerDto> response = this.customerBusiness.getByDocumentNumber(DOCUMENT_NUMBER);
        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
