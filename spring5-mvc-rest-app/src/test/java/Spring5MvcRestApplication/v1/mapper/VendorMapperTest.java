package Spring5MvcRestApplication.v1.mapper;

import Spring5MvcRestApplication.v1.model.VendorDTO;
import Spring5MvcRestApplication.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    public static final String VENDOR_NAME = "Amazon";
    public static final Long ID = 1L;
    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() throws Exception{

        // given
        Vendor vendor = new Vendor();
        vendor.setName(VENDOR_NAME);
        vendor.setId(ID);

        // when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        // then
        assertEquals(VENDOR_NAME, vendorDTO.getName());
    }

    @Test
    public void vendorDTOToVendor() throws Exception{
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(VENDOR_NAME);

        // when
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        // then
        assertEquals(VENDOR_NAME, vendor.getName());
    }

}