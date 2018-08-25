package venezuela.rpc;

import java.math.BigDecimal;

public interface HelloService {

    String sayHello(String name);
    String sayHelloAgain(String name);

    String login(String username, String password);

    String sendMoney(String walletAddress, BigDecimal amount);
    BigDecimal getBalance();
}
