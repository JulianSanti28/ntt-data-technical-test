package com.ntt.data.app.mapper;

import com.ntt.data.app.domain.CustomerDto;
import com.ntt.data.app.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper extends IEntityMapper<CustomerDto, Customer>{

    Customer toDomain(CustomerDto dto);
    CustomerDto toDto(Customer entity);

}
