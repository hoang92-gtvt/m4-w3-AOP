package service.impl;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.ICustomerRepository;
import service.ICustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository  customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();

    }

    @Override
    public Optional<Customer> findOne(Long id) throws Exception {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new Exception("customer not found!");
        }
        return customer;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
