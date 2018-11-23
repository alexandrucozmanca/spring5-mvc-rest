package Spring5MvcRestApplication.bootstrap;

import Spring5MvcRestApplication.domain.Category;
import Spring5MvcRestApplication.domain.Customer;
import Spring5MvcRestApplication.domain.Vendor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import Spring5MvcRestApplication.repositories.CategoryRepository;
import Spring5MvcRestApplication.repositories.CustomerRepository;
import Spring5MvcRestApplication.repositories.VendorRepository;

@Controller
public class Bootstrap implements CommandLineRunner {

    public CategoryRepository categoryRepository;
    public CustomerRepository customerRepository;
    public VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadFruits();
        loadCustomers();
        loadVendors();
    }

    private void loadFruits(){

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Fruit data loaded = " + categoryRepository.count());
    }

    private void loadCustomers(){
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Michael");
        customer1.setLastName("Weston");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");
        customerRepository.save(customer2);

        System.out.println("Customer data loaded = " + customerRepository.count());
    }

    private void loadVendors(){
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Amazon");
        vendorRepository.save(vendor1);


        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Virgin");
        vendorRepository.save(vendor2);

        System.out.println("Vendor data loaded = " + vendorRepository.count());
    }
}
