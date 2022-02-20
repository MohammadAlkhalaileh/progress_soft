package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/warehouse")
public class DataWarehouseController {

    private static final Logger logger = LoggerFactory.getLogger(DataWarehouseController.class);

    private final OrderRepository orderRepository;

    public DataWarehouseController(OrderRepository dataWarehouseRepository) {
        super();
        this.orderRepository = dataWarehouseRepository;
    }

    @PostMapping("/orders")
    public void save(@Valid @RequestBody Order dataWarehouse) {

        logger.trace("save method called");
        orderRepository.save(dataWarehouse);
        logger.trace("save method finished" + dataWarehouse.toString());
    }

}
