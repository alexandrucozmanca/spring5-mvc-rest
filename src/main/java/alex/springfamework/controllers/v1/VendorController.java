package alex.springfamework.controllers.v1;

import alex.springfamework.api.v1.model.VendorDTO;
import alex.springfamework.api.v1.model.VendorListDTO;
import alex.springfamework.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Past;

@Api(description = "Vendor Api")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors/";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Get all Vendors", notes = "Api Notes")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getListOfVendors(){
        return vendorService.getAllVendors();
    }

    @ApiOperation(value = "Get Vendor By Id", notes = "Api Notes")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id)
    {
        return vendorService.getVendorById(id);
    }


    @ApiOperation(value = "Create a new Vendor", notes = "Api Notes")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }

    @ApiOperation(value = "Update an existing Vendor", notes = "Api Notes")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendorByDTO(@PathVariable Long id,@RequestBody VendorDTO vendorDTO){
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @ApiOperation(value = "Patch a Vendor", notes = "Api Notes")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.patchVendor(id, vendorDTO);
    }

    @ApiOperation(value = "Delete a Vendor", notes = "Api Notes")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendorById(@PathVariable Long id){
        vendorService.deleteVendorById(id);
    }
}
