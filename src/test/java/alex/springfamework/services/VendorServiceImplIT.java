//package alex.springfamework.services;
//
//import alex.springfamework.api.v1.mapper.VendorMapper;
//import alex.springfamework.bootstrap.Bootstrap;
//import alex.springfamework.repositories.CategoryRepository;
//import alex.springfamework.repositories.CustomerRepository;
//import alex.springfamework.repositories.VendorRepository;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class VendorServiceImplIT {
////
////    @Autowired
////    CategoryRepository categoryRepository;
////
////    @Autowired
////    CustomerRepository customerRepository;
////
////    @Autowired
////    VendorRepository vendorRepository;
////
////    VendorServiceImpl vendorService;
////
////
////    @Before
////    public void setUp() throws Exception{
////        System.out.println("Loading Data");
////        System.out.println("Categories: " + categoryRepository.findAll().size());
////        System.out.println("Customers: " + customerRepository.findAll().size());
////        System.out.println("Vendors: " + vendorRepository.findAll().size());
////
////        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
////        bootstrap.run();
////
////        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
////    }
//}
