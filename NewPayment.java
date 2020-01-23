package com.braintreegateway.example;

import org.ztemplates.render.ZRenderer;
import org.ztemplates.render.velocity.ZVelocityRenderer;

import com.braintreegateway.BraintreeGateway;

@ZRenderer(ZVelocityRenderer.class)
public class NewPayment extends Payment {
    public NewPayment(BraintreeGateway gateway) {
        this.gateway = gateway;
        this.amount = "100.00";
    }
}