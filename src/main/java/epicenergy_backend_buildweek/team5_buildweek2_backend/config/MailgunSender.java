package epicenergy_backend_buildweek.team5_buildweek2_backend.config;

import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailgunSender {

    private String mailgunApiKey;
    private String mailgunDomain;

    public MailgunSender(@Value("${MAILGUN_DOMAIN_NAME}") String mailgunDomain, @Value("${MAILGUN_API_KEY}") String mailgunApiKey){
        this.mailgunApiKey = mailgunApiKey;
        this.mailgunDomain = mailgunDomain;
    };

    public void sendRegistrationEmail(String recipient){
        Unirest.post("https://api.mailgun.net/v3/" + this.mailgunDomain + "/messages")
                .basicAuth("api", this.mailgunApiKey)
                .queryString("from", "EpiEnergy@com")
                .queryString("to", recipient)
                .queryString("subject", "Registrazione avvenuta con successo")
                .queryString("text", "Complimenti per esserti registrato")
                .asJson();

    }
}
