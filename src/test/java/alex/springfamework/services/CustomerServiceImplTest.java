package alex.springfamework.services;

import alex.springfamework.api.v1.mapper.CategoryMapper;
import alex.springfamework.api.v1.mapper.CustomerMapper;
import alex.springfamework.api.v1.model.CustomerDTO;
import alex.springfamework.controllers.v1.CustomerController;
import alex.springfamework.domain.Customer;
import alex.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    public static final String LAST_NAME = "Doe";
    public static final String FIRST_NAME = "John";

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerServiceImpl customerService;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl();
        customerService.setCustomerMapper(customerMapper);
        customerService.setCustomerRepository(customerRepository);
    }

    @Test
    public void  getAllCustomers() throws Exception{
        // given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        // when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(2, customerDTOS.size());
    }

    @Test
    public void getCustomerById() throws Exception{
        // given
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setLastName(LAST_NAME);
        customer.setFirstName(FIRST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(1l);

        assertEquals(LAST_NAME, customerDTO.getLastName());
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
    }

    @Test
    public void createNewCustomer() throws Exception{

       // given
       CustomerDTO customerDTO = new CustomerDTO();
       customerDTO.setLastName(LAST_NAME);
       customerDTO.setFirstName(FIRST_NAME);

       Customer savedCustomer = new Customer();
       savedCustomer.setFirstName(customerDTO.getFirstName());
       savedCustomer.setLastName(customerDTO.getLastName());
       savedCustomer.setId(1l);

       when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

       //when
        CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);

        // then
        assertEquals(FIRST_NAME, savedDTO.getFirstName());
        assertEquals(LAST_NAME, savedDTO.getLastName());
        assertEquals(CustomerController.BASE_URL + "1", savedDTO.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDTO() throws Exception{

        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDTO = customerService.saveCustomerByDTO(1L, customerDTO);

        // then
        assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals(CustomerController.BASE_URL + "1", savedDTO.getCustomerUrl());

    }

    @Test
    public void deleteCustomerById() throws Exception{
        Long id = 1L;

        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}