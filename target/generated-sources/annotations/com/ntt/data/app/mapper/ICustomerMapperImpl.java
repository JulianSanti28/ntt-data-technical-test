package com.ntt.data.app.mapper;

import com.ntt.data.app.domain.AddressDto;
import com.ntt.data.app.domain.CustomerDto;
import com.ntt.data.app.model.Address;
import com.ntt.data.app.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-05T18:19:59-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class ICustomerMapperImpl implements ICustomerMapper {

    @Override
    public Customer toDomain(CustomerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustomerId( dto.getCustomerId() );
        customer.setFirstName( dto.getFirstName() );
        customer.setSecondName( dto.getSecondName() );
        customer.setFirstLastName( dto.getFirstLastName() );
        customer.setSecondLastName( dto.getSecondLastName() );
        customer.setPhoneNumber( dto.getPhoneNumber() );
        customer.setAddress( addressDtoToAddress( dto.getAddress() ) );

        return customer;
    }

    @Override
    public CustomerDto toDto(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setCustomerId( entity.getCustomerId() );
        customerDto.setFirstName( entity.getFirstName() );
        customerDto.setSecondName( entity.getSecondName() );
        customerDto.setFirstLastName( entity.getFirstLastName() );
        customerDto.setSecondLastName( entity.getSecondLastName() );
        customerDto.setPhoneNumber( entity.getPhoneNumber() );
        customerDto.setAddress( addressToAddressDto( entity.getAddress() ) );

        return customerDto;
    }

    protected Address addressDtoToAddress(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setDescription( addressDto.getDescription() );
        address.setCity( addressDto.getCity() );

        return address;
    }

    protected AddressDto addressToAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setDescription( address.getDescription() );
        addressDto.setCity( address.getCity() );

        return addressDto;
    }
}
