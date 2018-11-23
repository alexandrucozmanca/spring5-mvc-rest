package Spring5MvcRestApplication.controllers;


import model.CustomerDTO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import Spring5MvcRestApplication.controllers.v1.CustomerController;
import Spring5MvcRestApplication.services.CustomerService;
import Spring5MvcRestApplication.services.ResourceNotFoundException;

import java.util.Arrays;
import java.util.List;


import static Spring5MvcRestApplication.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FIRST_NAME = "Jim";
    public static final String LAST_NAME = "Cohen";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Ignore
    @Test
    public void testListCustomers() throws Exception{
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName("Cohen");
        customer1.setCustomerUrl(CustomerController.BASE_URL + "1");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("John");
        customer2.setLastName("Bernard");
        customer2.setCustomerUrl(CustomerController.BASE_URL + "2");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get(CustomerController.BASE_URL)
              .accept(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception{
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName(LAST_NAME);
        customer1.setCustomerUrl(CustomerController.BASE_URL + "1");


        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get(CustomerController.BASE_URL + "1")
              .accept(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName",equalTo(LAST_NAME)));
    }

    @Test
    public void testGetCustomerByIdNotFound() throws Exception{

        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "222")
              .accept(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void createNewCustomer() throws Exception{
        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "1");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(post(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "1")));
    }

    @Test
    public void updateCustomer() throws Exception{
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        // when
        mockMvc.perform(put(CustomerController.BASE_URL + "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "1")));
    }

    @Test
    public void patchCustomer() throws Exception{
        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName("UpdateLastName");
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        //when
        mockMvc.perform(patch(CustomerController.BASE_URL + "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo("UpdateLastName")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "1")));
    }

    @Test
    public void deleteCustomer() throws Exception{

        mockMvc.perform(delete(CustomerController.BASE_URL + "1")
              .accept(MediaType.APPLICATION_JSON)
              .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk());


    }

}