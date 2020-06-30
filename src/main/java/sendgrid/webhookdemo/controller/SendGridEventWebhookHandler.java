package sendgrid.webhookdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Controller
public class SendGridEventWebhookHandler {

   private static final Logger logger = Logger.getLogger(SendGridEventWebhookHandler.class.getName());

   private final Map<String, Set<String>> openedEmails = new ConcurrentHashMap<>();
   private final Map<String, Set<String>> clickedLinks = new ConcurrentHashMap<>();
   private final Map<String, Set<String>> processedEmails = new ConcurrentHashMap<>();
   private final Map<String, Set<String>> bounceEmails = new ConcurrentHashMap<>();
   private final Map<String, Set<String>> deferredEmails = new ConcurrentHashMap<>();
   private final Map<String, Set<String>> deliveredEmails = new ConcurrentHashMap<>();
   private final Map<String, Set<String>> droppedEmails = new ConcurrentHashMap<>();

   @PostMapping("/events")
   @ResponseBody
   public String receiveSGEventHook(@RequestBody List<SendGridEvent> events) {
       logger.info(String.format("Received %d events", events.size()));   
       events.forEach(event -> {
           switch (event.eventType) {
               case "open":
                   openedEmails.computeIfAbsent(event.sgMessageId, k -> new HashSet<>()).add(event.email);
                   logger.info("Received %d events email:"+event.email);
                   break;

               case "click":
                   clickedLinks.computeIfAbsent(event.url, k -> new HashSet<>()).add(event.email);
                   logger.info("Received %d events email:"+event.email);
                   break;
               case "processed":
                   processedEmails.computeIfAbsent(event.sgMessageId, k -> new HashSet<>()).add(event.email);
                   logger.info("Received %d events email:"+event.email);
                   break; 
               case "bounce":
                   bounceEmails.computeIfAbsent(event.sgMessageId, k -> new HashSet<>()).add(event.email);
                   logger.info("Received %d events email:"+event.email);
                   break; 
               case "deferred":
                   deferredEmails.computeIfAbsent(event.sgMessageId, k -> new HashSet<>()).add(event.email);
                   logger.info("Received %d events email:"+event.email);
                   break; 
               case "delivered":
                   deliveredEmails.computeIfAbsent(event.sgMessageId, k -> new HashSet<>()).add(event.email);
                   logger.info("Received %d events email:"+event.email);
                   break; 
               case "dropped":
                   droppedEmails.computeIfAbsent(event.sgMessageId, k -> new HashSet<>()).add(event.email);
                   logger.info("Received %d events email:"+event.email);
                   break;     

           }
       });

       return "ok";
   }


   @GetMapping("/opened")
   @ResponseBody
   public Map<String, Set<String>> getOpenedEmailData(){
       return openedEmails;
   }

   @GetMapping("/clicked")
   @ResponseBody
   public Map<String, Set<String>> getClickedLinksData(){
       return clickedLinks;
   }

}