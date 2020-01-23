package main;

import org.ztemplates.actions.ZMatch;
import org.ztemplates.web.ZTemplates;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;

@ZMatch("/payments/confirm")
public class ConfirmPaymentAction {
    public void after() throws Exception {
        BraintreeGateway gateway = Configuration.getBraintreeGateway();

        String queryString = ZTemplates.getServletService().getRequest().getQueryString();
        Result<Transaction> result = gateway.transparentRedirect().confirmTransaction(queryString);

        if (result.isSuccess()) {
            ZTemplates.getServletService().render(new ConfirmPayment(result.getTarget()));
        } else {
            ZTemplates.getServletService().render(new ErrorPayment(gateway, result.getParameters(), result.getErrors()));
        }
    }
}
