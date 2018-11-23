package Spring5MvcRestApplication.controllers;


import Spring5MvcRestApplication.controllers.RestResponseEntityExceptionHandler;
import Spring5MvcRestApplication.v1.model.VendorDTO;
import Spring5MvcRestApplication.v1.model.VendorListDTO;
import Spring5MvcRestApplication.controllers.v1.VendorController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import Spring5MvcRestApplication.services.ResourceNotFoundException;
import Spring5MvcRestApplication.services.VendorService;

import java.util.Arrays;


import static Spring5MvcRestApplication.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    public static final String NAME = "Amazon";

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListVendors() throws Exception{
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(NAME);

        VendorDTO vendor2 = new VendorDTO();

       VendorListDTO vendors = new VendorListDTO(Arrays.asList(vendor1, vendor2));

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get(VendorController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));

    }

    @Test
    public void testGetVendorById() throws Exception{
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + "1");

        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get(VendorController.BASE_URL + "1")
              .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void testGetVendorByIdNotFound() throws Exception{
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + "1");

        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(VendorController.BASE_URL + "22")
              .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNewVendor() throws Exception{
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL + "1");

        when(vendorService.createNewVendor(vendorDTO)).thenReturn(returnDTO);

        mockMvc.perform(post(VendorController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "1")));
    }

    @Test
    public void testUpdateVendor() throws Exception{
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL + "1");

        when(vendorService.saveVendorByDTO(anyLong(),any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(VendorController.BASE_URL + "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "1")));
    }

    @Test
    public void testDeleteVendor() throws Exception{
        mockMvc.perform(delete(VendorController.BASE_URL + "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}