package com.pizzadeliverybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaDeliveryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaDeliveryBackendApplication.class, args);
    }


}
/*
@Component
class Bootstrap implements CommandLineRunner {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        createExamples(30);
        accountRepository.save(new Account()
                .withUsername("john")
                .withPassword("123")
                .withLoginStatus("not_logged")
                .withTaskStatus("task_0"));
    }
    private void createExamples(int n) {
        List<String> statusList = new ArrayList<String>(List.of(
                "confirmed","accepted","baking","pizzaReady","delivering","pizzaDelivered","finished"));
        List<String> flavorList = new ArrayList<String>(List.of(
                "Four cheese","Ham & Cheese","Tuna","Pepperoni","Veggie Pizza","Margherita","Hawaiian Pizza"));
        Random random = new Random();

        for (int i = 1; i <= n; i++) {
            String status = statusList.get(random.nextInt(statusList.size()));
            Account account = accountRepository.save(new Account()
                    .withUsername("username_"+i)
                    .withPassword("123"));
            String orderId = orderService.createOrder(new ClientOrder()
                    .withClientName("name_"+i)
                    .withAddress("address_"+i)
                    .withPizzaFlavor(flavorList.get(random.nextInt(flavorList.size())))
                    .withPaid(random.nextBoolean())
                    .withStatus(status)
                    .withAccount(account))
                        .getId().toString();
            if (Objects.equals(status, "finished")) {
                orderService.updateHistoryOrder(orderId,new OrderHistory()
                        .withClientFeedbackNotes("The service was ok_"+i)
                        .withClientFeedbackScore(random.nextInt(6))
                );
                orderService.updateHistoryOrder(orderId,new OrderHistory()
                        .withDeliveryFeedback("My delivery was ok_"+i)
                );
            }
        }
    }
}

 */
