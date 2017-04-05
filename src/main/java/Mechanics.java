import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

/**
 * Created by GreatThane on 4/4/17.
 */
public class Mechanics {

    public static void main(String[] arguments)
            throws LoginException, RateLimitedException, InterruptedException
    {
        JDA api = new JDABuilder(AccountType.BOT)
                .setToken("Mjk4OTI0NTI0MzIwMDYzNDkw.C8WbZQ.yhiF6wIlOmPO82p4yCTbE94GsJQ")
                .addListener(new Commands())
                .buildAsync();
    }
}
