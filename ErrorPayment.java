package main;

import java.util.List;
import java.util.Map;

import org.ztemplates.render.ZExpose;
import org.ztemplates.render.ZRenderer;
import org.ztemplates.render.velocity.ZVelocityRenderer;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ValidationError;
import com.braintreegateway.ValidationErrors;

@ZRenderer(ZVelocityRenderer.class)
public class ErrorPayment extends Payment {
    private ValidationErrors errors;
    private Map<String, String> params;
    
    public ErrorPayment(BraintreeGateway gateway, Map<String, String> params, ValidationErrors errors) {
        this.gateway = gateway;
        this.amount = "100.00";
        this.params = params;
        this.errors = errors;
    }
    
    @ZExpose
    public String getEmail() {
        return paramOrEmpty("transaction[customer][email]");
    }
    
    @ZExpose
    public String getExpirationDate() {
        return paramOrEmpty("transaction[credit_card][expiration_date]");
    }
    
    @ZExpose
    public String getFirstName() {
        return paramOrEmpty("transaction[customer][first_name]");
    }
    
    @ZExpose
    public String getLastName() {
        return paramOrEmpty("transaction[customer][last_name]");
    }
    
    @ZExpose
    public boolean getAnyNumberErrors() {
        return errors.forObject("transaction").forObject("credit_card").onField("number").size() > 0;
    }
    
    @ZExpose
    public List<ValidationError> getNumberErrors() {
        return errors.forObject("transaction").forObject("credit_card").onField("number");
    }
    
    @ZExpose
    public boolean getAnyExpirationDateErrors() {
        return errors.forObject("transaction").forObject("credit_card").onField("expiration_date").size() > 0;
    }
    
    @ZExpose
    public List<ValidationError> getExpirationDateErrors() {
        return errors.forObject("transaction").forObject("credit_card").onField("expiration_date");
    }
    
    @ZExpose
    public boolean getAnyEmailErrors() {
        return errors.forObject("transaction").forObject("customer").onField("email").size() > 0;
    }
    
    @ZExpose
    public List<ValidationError> getEmailErrors() {
        return errors.forObject("transaction").forObject("customer").onField("email");
    }
    
    @ZExpose
    public boolean getAnyFirstNameErrors() {
        return errors.forObject("transaction").forObject("customer").onField("first_name").size() > 0;
    }
    
    @ZExpose
    public List<ValidationError> getFirstNameErrors() {
        return errors.forObject("transaction").forObject("customer").onField("first_name");
    }
    
    @ZExpose
    public boolean getAnyLastNameErrors() {
        return errors.forObject("transaction").forObject("customer").onField("last_name").size() > 0;
    }
    
    @ZExpose
    public List<ValidationError> getLastNameErrors() {
        return errors.forObject("transaction").forObject("customer").onField("last_name");
    }
    
    @ZExpose
    public boolean getAnyCvvErrors() {
        return errors.forObject("transaction").forObject("credit_card").onField("cvv").size() > 0;
    }
    
    @ZExpose
    public List<ValidationError> getCvvErrors() {
        return errors.forObject("transaction").forObject("credit_card").onField("cvv");
    }
    
    @ZExpose
    public int getNumErrors() {
        return errors.deepSize();
    }
    
    private String paramOrEmpty(String name) {
        String param = params.get(name);
        if (param == null) {
            return "";
        } else {
            return param;
        }
    }
}
