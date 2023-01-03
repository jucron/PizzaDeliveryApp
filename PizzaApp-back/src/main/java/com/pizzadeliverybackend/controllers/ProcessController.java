package com.pizzadeliverybackend.controllers;

import com.pizzadeliverybackend.model.OrderMinimal;
import com.pizzadeliverybackend.model.Response;
import com.pizzadeliverybackend.services.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process/client/")
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService processService;

    @PostMapping("{caseKey}/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public void startProcess(@PathVariable String caseKey,@PathVariable String username) {
        processService.startProcess(caseKey,username);
    }

    @PostMapping("{username}")
    @ResponseStatus(HttpStatus.OK)
    public void completeTask(@PathVariable String username, @RequestBody Object object) {
        processService.completeTask(username, object);
    }

    @GetMapping("{username}/taskDef")
    @ResponseStatus(HttpStatus.OK)
    public Response getTaskId(@PathVariable String username) {
        return processService.getTaskDefKey(username);
    }

    @GetMapping("{username}/orderStatus")
    @ResponseStatus(HttpStatus.OK)
    public Response getOrderStatus(@PathVariable String username) {
        return processService.getOrderStatus(username);
    }

    @GetMapping("{username}/order")
    @ResponseStatus(HttpStatus.OK)
    public OrderMinimal getOrder(@PathVariable String username) {
        return processService.getOrder(username);
    }
}
