package com.electrogrid.PAF.controller;

import com.electrogrid.PAF.model.Payment;
import com.electrogrid.PAF.service.PaymentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.HashMap;

/*
 *default Port : 8040
 *http://localhost:8040/payment/*
 */
@Path("/payment")
public class PaymentController {

    private Payment payment;
    private final PaymentService paymentService = new PaymentService();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPayment(HashMap<String, ?> paymentData) {
        String paid = (String) paymentData.get("paid");
        String cvv = (String) paymentData.get("cvv");
        String name = (String) paymentData.get("name");
        String date = (String) paymentData.get("date");
        String amount = (String) paymentData.get("amount");
        payment = new Payment(paid, cvv, name, date,amount);

        return paymentService.addPayment(payment);
    }

    @PUT
    @Path("/{paymentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePayment(HashMap<String, ?> paymentData, @PathParam("paymentId") Integer paymentId) {
        String paid = (String) paymentData.get("paid");
        String cvv = (String) paymentData.get("cvv");
        String name = (String) paymentData.get("name");
        String date = (String) paymentData.get("date");
        String amount = (String) paymentData.get("amount");
        payment = new Payment(paid, cvv, name, date,amount);
        payment.setId(paymentId);

        return paymentService.updatePayment(payment);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPayments() {
        return paymentService.getPayments();
    }

    @GET
    @Path("/{paymentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentById(@PathParam("paymentId") Integer paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @DELETE
    @Path("/{paymentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("paymentId") Integer paymentId) {
        return paymentService.deletePayment(paymentId);
    }
}
