package Spring5MvcRestApplication.controllers;

import Spring5MvcRestApplication.v1.model.VendorDTO;
import Spring5MvcRestApplication.v1.model.VendorListDTO;
import Spring5MvcRestApplication.controllers.v1.VendorController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import Spring5MvcRestApplication.services.VendorService;

import java.util.Arrays;


import static Spring5MvcRestApplication.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerITTest {

    @MockBean
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc;

    VendorDTO vendorDTO1;
    VendorDTO vendorDTO2;

    @Before
    public void setUp()throws Exception{
        vendorDTO1 = new VendorDTO("Vendor 1", VendorController.BASE_URL + "1");
        vendorDTO2 = new VendorDTO("Vendor 2", VendorController.BASE_URL + "2");
    }

    @Test
    public void testGetVendorList() throws Exception{
        VendorListDTO vendors = new VendorListDTO(Arrays.asList(vendorDTO1, vendorDTO2));

        given(vendorService.getAllVendors()).willReturn(vendors);

        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void testGetVendorById() throws Exception{
        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO1);

        mockMvc.perform(get(VendorController.BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }

    @Test
    public void testCreateVendor() throws Exception{
        given(vendorService.createNewVendor(vendorDTO1)).willReturn(vendorDTO1);

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }

    @Test
    public void testUpdateVendor() throws Exception{
        given(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO1);

        mockMvc.perform(put(VendorController.BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }

    @Test
    public void testPatchVendor() throws Exception{
        given(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO1);

        mockMvc.perform(patch(VendorController.BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }

    @Test
    public void testDeleteVendor() throws Exception{
       mockMvc.perform(delete(VendorController.BASE_URL + "1")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }
}
