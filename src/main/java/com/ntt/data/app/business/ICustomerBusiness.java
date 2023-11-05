package com.ntt.data.app.business;

import com.ntt.data.app.domain.CustomerDto;
import com.ntt.data.app.domain.ResponseDto;

/**
 * An interface for Customer Business operations.
 */
public interface ICustomerBusiness {

    /**
     * Retrieves a CustomerDto based on the provided document number.
     *
     * @param documentNumber The document number used to fetch the customer information.
     * @return A ResponseDto containing the CustomerDto information if found, or an appropriate response if not found.
     */
    ResponseDto<CustomerDto> getByDocumentNumber(final String documentNumber);
}

