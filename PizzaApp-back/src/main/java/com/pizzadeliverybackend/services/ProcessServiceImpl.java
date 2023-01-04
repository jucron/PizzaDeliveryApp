package com.pizzadeliverybackend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzadeliverybackend.domain.Account;
import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.OrderHistory;
import com.pizzadeliverybackend.model.OrderMinimal;
import com.pizzadeliverybackend.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.cmmn.api.CmmnRuntimeService;
import org.flowable.cmmn.api.CmmnTaskService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    private final CmmnRuntimeService cmmnRuntimeService;
    private final CmmnTaskService cmmnTaskService;
    private final OrderService orderService;

    private final String usernameKey = "username";
//    private final String orderStatusKey = "orderStatus";
    private final String orderIdKey = "order";

    @Override
    public void startProcess(String caseKey, String username) {
        log.info("startProcess initiated with caseKey: "+caseKey + " and username: "+username);
        //start process with caseKey identifier and username variable
        cmmnRuntimeService.createCaseInstanceBuilder()
                .variables(Map.of(usernameKey,username)).caseDefinitionKey(caseKey).start();
    }

    @Override
    public void completeTask(String username, Object object) {
        //get process data related to this username:
        String caseIdFromThisUsername = getCaseIdFromThisUsername(username);
        String taskDefKeyFromThisUsername = getTaskDefKeyFromThisUsername(caseIdFromThisUsername);
        ObjectMapper objectMapper = new ObjectMapper();
        //Execute internal changes differently from each task:
        switch (taskDefKeyFromThisUsername) {
            case "executeOrder": //Create Order for this username
                ClientOrder orderParameter = objectMapper.convertValue(object, ClientOrder.class);
                orderParameter.setAccount(new Account().withUsername(username));
                ClientOrder orderSaved = orderService.createOrder(orderParameter);
                cmmnRuntimeService.setVariable(caseIdFromThisUsername,orderIdKey,orderSaved.getId().toString());
                break;
            case "endFollowUp": //no Data to process at this stage
                break;
            case "sendFeedback": //register feedback in repo
                orderService.updateHistoryOrder(
                        (String) cmmnRuntimeService.getVariables(caseIdFromThisUsername).get(orderIdKey),
                        objectMapper.convertValue(object, OrderHistory.class));
                break;
        }
        //Complete task in Flowable:
        String taskIdFromThisUsername = getTaskIdFromThisUsername(caseIdFromThisUsername);
        cmmnTaskService.complete(taskIdFromThisUsername);
    }


    @Override
    public Response getTaskDefKey(String username) {
        try {
            String caseIdFromThisUsername = getCaseIdFromThisUsername(username);
            String taskDefKeyFromThisUsername = getTaskDefKeyFromThisUsername(caseIdFromThisUsername);
            log.info("getTaskDefKey: taskDefKey found - "+taskDefKeyFromThisUsername);
            return new Response().withMessage(taskDefKeyFromThisUsername);
        } catch (Exception e) {
            log.info("getTaskDefKey: no task found yet, returning Response with 'no_task' message");
            return new Response().withMessage("no_task");
        }
    }

    @Override
    public Response getOrderStatus(String username) {
        log.info("getOrderStatus executed for username: "+username);
        try {
            Map<String, Object> processData = getCaseVariablesFromThisUsername(username);
            return new Response().withMessage(
                    orderService.getOrder((String) processData.get(orderIdKey)).getStatus()
            );
        } catch (Exception e) {
            log.info("error: "+ e);
            return null;
        }
    }

    @Override
    public OrderMinimal getOrder(String username) {
        log.info("getOrder executed for username: "+username);
        try {
            Map<String, Object> processData = getCaseVariablesFromThisUsername(username);
            return orderService.getOrder((String) processData.get(orderIdKey));
        } catch (Exception e) {
            log.info("error: "+ e);
            return null;
        }
    }

    private String getTaskDefKeyFromThisUsername(String caseIdFromThisUsername) {
        return cmmnTaskService.createTaskQuery().caseInstanceId(caseIdFromThisUsername).singleResult().getTaskDefinitionKey();
    }

    private String getCaseIdFromThisUsername(String username) {
        return cmmnRuntimeService.createCaseInstanceQuery().variableValueEquals(usernameKey, username).singleResult().getId();
    }

    private String getTaskIdFromThisUsername(String caseIdFromThisUsername) {
        return cmmnTaskService.createTaskQuery().caseInstanceId(caseIdFromThisUsername).singleResult().getId();
    }

    private Map<String, Object> getCaseVariablesFromThisUsername(String username) {
        return cmmnRuntimeService.getVariables(getCaseIdFromThisUsername(username));
    }
}
