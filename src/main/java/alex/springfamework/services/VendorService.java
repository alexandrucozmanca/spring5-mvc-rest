package alex.springfamework.services;

import alex.springfamework.api.v1.model.VendorDTO;
import alex.springfamework.domain.Vendor;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO getVendorByName(String name);

    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
