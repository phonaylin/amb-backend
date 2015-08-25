package com.amb.mm.travel.bus.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.amb.mm.travel.bus.BusOrder;

@Component("mailingService")
public class MailingService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
    private VelocityEngine velocityEngine;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
    
	public void sendConfirmationEmail(final BusOrder order) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(order.getCustomer().getEmailAddress());
//                message.setFrom("webmaster@csonth.gov.uk"); // could be parameterized...
                Map model = new HashMap();
                model.put("order", order);
                
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "order.confirmation.vm", "UTF-8" , model);
                
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }
}
