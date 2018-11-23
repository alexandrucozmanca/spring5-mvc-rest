package Spring5MvcRestApplication.services;

import Spring5MvcRestApplication.v1.model.VendorDTO;
import Spring5MvcRestApplication.v1.model.VendorListDTO;

public interface VendorService {

    VendorListDTO getAllVendors();

    VendorDTO getVendorByName(String name);

    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
}
