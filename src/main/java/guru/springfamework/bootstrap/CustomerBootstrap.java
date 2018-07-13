package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class CustomerBootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        Customer susan = new Customer();
        susan.setFirstName("Susan");
        susan.setLastName("Tanner");

        Customer joe = new Customer();
        joe.setFirstName("Joe");
        joe.setLastName("Newman");

        Customer michael = new Customer();
        michael.setFirstName("Michael");
        michael.setLastName("Lachappele");

        Customer david = new Customer();
        david.setFirstName("David");
        david.setLastName("Winter");

        customerRepository.save(susan);
        customerRepository.save(joe);
        customerRepository.save(michael);
        customerRepository.save(david);

        log.debug("Customer Data Loaded = " + customerRepository.count() );
    }
}

