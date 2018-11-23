package Spring5MvcRestApplication.v1.mapper;

import model.CustomerDTO;
import org.junit.Test;
import Spring5MvcRestApplication.domain.Customer;

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
