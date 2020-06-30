package sendgrid.webhookdemo.controller;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SendGridEvent{
   public final String email;
   public final String eventType;
   public final String url;
   public final String sgMessageId;
   public SendGridEvent(@JsonProperty("email") String email,
                        @JsonProperty("event") String eventType,
                        @JsonProperty("url") String url,
                        @JsonProperty("sg_message_id") String sgMessageId) {
       this.email = email;
       this.eventType = eventType;
       this.url = url;
       this.sgMessageId = sgMessageId;
   }
}