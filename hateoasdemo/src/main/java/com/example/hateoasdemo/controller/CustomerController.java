package com.example.hateoasdemo.controller;

import com.example.hateoasdemo.entity.Customer;
import com.example.hateoasdemo.model.CustomerModel;
import com.example.hateoasdemo.model.CustomerModelAssembler;
import com.example.hateoasdemo.services.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerModelAssembler customerModelAssembler;

    private final PagedResourcesAssembler<Customer> pagedResourcesAssembler;

    public CustomerController(CustomerService customerService, CustomerModelAssembler customerModelAssembler, PagedResourcesAssembler<Customer> pagedResourcesAssembler) {
        this.customerService = customerService;
        this.customerModelAssembler = customerModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }


    /**
     * @param page      number of the page returned
     * @param size      number of entries in each page
     * @param sortList  list of columns to sort on
     * @param sortOrder sort order. Can be ASC or DESC
     * @return PagedModel object in Hateoas with customers after sorting
     */
    @GetMapping("/api/v1/customers")
    public PagedModel<CustomerModel> fetchCustomersWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "") List<String> sortList,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {
        Page<Customer> customerPage = customerService.fetchCustomerDataAsPageWithSorting(page, size, sortList, sortOrder.toString());
        return pagedResourcesAssembler.toModel(customerPage, customerModelAssembler);
    }
}
