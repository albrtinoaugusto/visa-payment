package main;

import org.ztemplates.render.ZExpose;
import org.ztemplates.render.ZRenderer;
import org.ztemplates.render.velocity.ZVelocityRenderer;

import com.braintreegateway.Transaction;

@ZRenderer(ZVelocityRenderer.class)
public class ConfirmPayment {
    private Transaction transaction;
    
    public ConfirmPayment(Transaction transaction) {
        this.transaction = transaction;
    }
    
    @ZExpose
    public String getAmount() {
        return emptyStringIfNull(transaction.getAmount().toString());
    }
    
    @ZExpose
    public String getId() {
        return emptyStringIfNull(transaction.getId());
    }
    
    @ZExpose
    public String getFirstName() {
        return emptyStringIfNull(transaction.getCustomer().getFirstName());
    }
    
    @ZExpose
    public String getLastName() {
        return emptyStringIfNull(transaction.getCustomer().getLastName());
    }
    
    @ZExpose
    public String getEmail() {
        return emptyStringIfNull(transaction.getCustomer().getEmail());
    }
    
    @ZExpose
    public String getNumber() {
        return emptyStringIfNull(transaction.getCreditCard().getMaskedNumber());
    }
    
    @ZExpose
    public String getCardType() {
        return emptyStringIfNull(transaction.getCreditCard().getCardType());
    }

    private String emptyStringIfNull(String string) {
    	if (string == null) {
    		return "";
    	}
    	return string;
    }
}
