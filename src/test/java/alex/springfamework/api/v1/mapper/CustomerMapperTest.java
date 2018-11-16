package alex.springfamework.api.v1.mapper;

import alex.springfamework.api.v1.model.CustomerDTO;
import alex.springfamework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final String FIRST_NAME = "Joe";
    public static final String LAST_NAME = "Bernard";
    public static final long ID = 1L;
    CustomerMapper categoryMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws  Exception{

        // given
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setId(ID);

        // when
        CustomerDTO customerDTO = categoryMapper.customerToCustomerDTO(customer);

        // then


        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }
}
