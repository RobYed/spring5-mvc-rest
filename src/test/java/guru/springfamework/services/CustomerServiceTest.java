package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private static final Long ID = 2L;
    private static final String FIRST_NAME = "Bilbo";
    private static final String LAST_NAME = "Beutlin";

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() {

        // given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        // when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        // then
        assertEquals(3, customerDTOS.size());
    }

    @Test
    public void findById() {

        // given
        Customer customer = createTestCustomer();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        // then
        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        // then
        assertEquals(FIRST_NAME, customerDTO.getFirstname());
        assertEquals(LAST_NAME, customerDTO.getLastname());
    }

    @Test
    public void createNewCustomer() {

        CustomerDTO customerDTO = createTestCustomerDTO();

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstname());
        savedCustomer.setLastName(customerDTO.getLastname());
        savedCustomer.setId(1L);

        // given
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // when
        CustomerDTO savedDTO = customerService.createNewCustomer(createTestCustomerDTO());

        // then
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals(customerDTO.getLastname(), savedDTO.getLastname());
        assertEquals("/api/v1/customers/1", savedCustomer.getCustomerUrl());
    }

    @Test
    public void replaceExistingCustomer() {

        CustomerDTO customerDTO = createTestCustomerDTO();

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstname());
        savedCustomer.setLastName(customerDTO.getLastname());
        savedCustomer.setId(1L);

        // given
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // when
        CustomerDTO savedDTO = customerService.replaceExistingCustomer(1L, createTestCustomerDTO());

        // then
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals(customerDTO.getLastname(), savedDTO.getLastname());
        assertEquals("/api/v1/customers/1", savedCustomer.getCustomerUrl());
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        return customer;
    }

    private CustomerDTO createTestCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRST_NAME);
        customerDTO.setLastname(LAST_NAME);
        customerDTO.setCustomer_url("this/should/not/be/mapped/back");
        return customerDTO;
    }
}