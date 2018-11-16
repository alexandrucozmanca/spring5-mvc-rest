package alex.springfamework.bootstrap;

import alex.springfamework.domain.Category;
import alex.springfamework.domain.Customer;
import alex.springfamework.repositories.CategoryRepository;
import alex.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class Bootstrap implements CommandLineRunner {

    public CategoryRepository categoryRepository;
    public CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadFruits();
        loadCustomers();
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
}
