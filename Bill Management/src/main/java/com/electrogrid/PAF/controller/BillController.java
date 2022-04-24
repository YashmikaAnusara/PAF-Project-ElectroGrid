package com.electrogrid.PAF.controller;

import com.electrogrid.PAF.model.Bill;
import com.electrogrid.PAF.service.BillService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;

/*
 *default Port : 8050
 *http://localhost:8050/api/v1/bill/*
 */
@Path("/billservice")
public class BillController {

    private Bill bill;
    private final BillService billService = new BillService();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBill(HashMap<String, ?> billData) {
        String nic = (String) billData.get("nic");
        String price = (String) billData.get("price");
        String date = (String) billData.get("date");
        String meterReader = (String) billData.get("meterReader");
        bill = new Bill(nic, price, date, meterReader);

        return billService.addBill(bill);
    }

    @PUT
    @Path("/{billId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBill(HashMap<String, ?> billData, @PathParam("billId") Integer billId) {
        String nic = (String) billData.get("nic");
        String price = (String) billData.get("price");
        String date = (String) billData.get("date");
        String meterReader = (String) billData.get("meterReader");
        bill = new Bill(nic, price, date, meterReader);
        bill.setId(billId);

        return billService.updateBill(bill);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBills() {
        return billService.getBills();
    }

    @GET
    @Path("/{billId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillById(@PathParam("billId") Integer billId) {
        return billService.getBillById(billId);
    }

    @DELETE
    @Path("/{billId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("billId") Integer billId) {
        return billService.deleteBill(billId);
    }
}
