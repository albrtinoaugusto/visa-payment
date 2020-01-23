package main;

import java.math.BigDecimal;

import org.ztemplates.render.ZExpose;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

public class Payment {
    protected BraintreeGateway gateway;
    protected String amount;
    
    @ZExpose
    public String getTrUrl() {
        return gateway.transparentRedirect().url();
    }
    
    @ZExpose
    public String getTrData() {
        TransactionRequest trRequest = new TransactionRequest().
            type(Transaction.Type.SALE).
            amount(new BigDecimal(getAmount())).
            options().
                submitForSettlement(true).
                done();
        
        return gateway.trData(trRequest, "http://localhost:8080/trcheckout/payments/confirm");
    }
    
    @ZExpose
    public String getAmount() {
        return amount;
    }
    
    @ZExpose
    public String getEmail() {
        return "";
    }
    
    @ZExpose
    public String getExpirationDate() {
        return "";
    }
    
    @ZExpose
    public String getFirstName() {
        return "";
    }
    
    @ZExpose
    public String getLastName() {
        return "";
    }
}
