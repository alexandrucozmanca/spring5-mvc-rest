package alex.springfamework.services;

import alex.springfamework.api.v1.model.VendorDTO;
import alex.springfamework.api.v1.model.VendorListDTO;
import alex.springfamework.domain.Vendor;

import java.util.List;

public interface VendorService {

    VendorListDTO getAllVendors();

    VendorDTO getVendorByName(String name);

    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
}
