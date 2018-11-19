package alex.springfamework.services;

import alex.springfamework.api.v1.mapper.VendorMapper;
import alex.springfamework.api.v1.model.VendorDTO;
import alex.springfamework.domain.Vendor;
import alex.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        //then
        assertEquals(2, vendorDTOS.size());
    }

    @Test
    public void getVendorById() throws Exception{
        // given
        Vendor vendor = new Vendor();
        vendor.setId(ID);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));}
}