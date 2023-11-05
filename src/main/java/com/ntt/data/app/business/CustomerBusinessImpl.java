package com.ntt.data.app.business;

import com.ntt.data.app.config.MessageLoader;
import com.ntt.data.app.domain.CustomerDto;
import com.ntt.data.app.domain.ResponseDto;
import com.ntt.data.app.exception.BusinessRuleException;
import com.ntt.data.app.mapper.ICustomerMapper;
import com.ntt.data.app.model.Customer;
import com.ntt.data.app.repository.ICustomerRepository;
import com.ntt.data.app.util.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class CustomerBusinessImpl implements ICustomerBusiness{
    private static final Logger logger = LoggerFactory.getLogger(CustomerBusinessImpl.class);

    private final ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;
    @Override
    public ResponseDto<CustomerDto> getByDocumentNumber(String documentNumber) {
        if (StringUtils.isEmpty(documentNumber)) throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM002, MessageLoader.getInstance().getMessage(MessagesConstants.EM002, "documentNumber"));
        Customer customer = this.customerRepository.findByDocumentNumber(documentNumber).orElseThrow(()->new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM001, MessageLoader.getInstance().getMessage(MessagesConstants.EM001, documentNumber)));
        logger.info("Customer has been found!");
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001, documentNumber), this.customerMapper.toDto(customer));
    }
}
