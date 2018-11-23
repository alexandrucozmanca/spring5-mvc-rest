package Spring5MvcRestApplication.services;

import Spring5MvcRestApplication.v1.mapper.VendorMapper;
import Spring5MvcRestApplication.v1.model.VendorDTO;
import Spring5MvcRestApplication.v1.model.VendorListDTO;
import Spring5MvcRestApplication.controllers.v1.VendorController;
import Spring5MvcRestApplication.domain.Vendor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import Spring5MvcRestApplication.repositories.VendorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {

    public static final String NAME = "Amazon";
    public static final long ID = 1L;

    @Mock
    VendorRepository vendorRepository;

    VendorServiceImpl vendorService;


    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getAllVendors() throws Exception{
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        // when
       VendorListDTO vendorDTOS = vendorService.getAllVendors();

        //then
        assertEquals(2, vendorDTOS.getVendors().size());
    }

    @Test
    public void getVendorById() throws Exception{
        // given
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void testSaveVendorByDTO() throws Exception{

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(NAME);
        savedVendor.setId(ID);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.saveVendorByDTO(ID, vendorDTO);

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + ID, savedDTO.getVendorUrl());
    }

    @Test
    public void testDeleteVendorById() throws Exception{

        vendorRepository.deleteById(ID);

        verify(vendorRepository, times(1)).deleteById(anyLong());


    }
}