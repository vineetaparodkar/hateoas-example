package com.example.hateoasdemo.services;

import com.example.hateoasdemo.dao.CustomerDao;
import com.example.hateoasdemo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerService {

    private final CustomerDao customerRepository;

    public CustomerService(CustomerDao customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Page<Customer> fetchCustomerDataAsPageWithSorting(int page, int pageSize, List<String> sortList, String sortOrder) {
        int size = pageSize == 0 ? Integer.MAX_VALUE : pageSize;
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return customerRepository.findAll(pageable);
    }


    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }

}

