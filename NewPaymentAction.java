package main;

import org.ztemplates.actions.ZMatch;
import org.ztemplates.web.ZTemplates;

@ZMatch("/payments/new")
public class NewPaymentAction {
    public void after() throws Exception {
        ZTemplates.getServletService().render(new NewPayment(Configuration.getBraintreeGateway()));
    }
}