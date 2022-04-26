package com.electrogrid.PAF.service;

import com.electrogrid.PAF.model.Payment;
import com.electrogrid.PAF.repository.DBConnection;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
	private DBConnection connection = new DBConnection();

	public Response addPayment(Payment payment) {
		int insertedId = -99;
		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "INSERT INTO payment(paid, cvv, name, date,amount) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStmt.setString(1, payment.getPaid());
			preparedStmt.setString(2, payment.getCvv());
			preparedStmt.setString(3, payment.getName());
			preparedStmt.setString(4, payment.getDate());
			preparedStmt.setString(5, payment.getAmount());

			preparedStmt.execute();
			ResultSet rs = preparedStmt.getGeneratedKeys();
			if (rs.next()){
				insertedId  = Integer.parseInt(rs.getString(1));
			}
			con.close();

			payment.setId(insertedId);
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
		return Response
				.status(Response.Status.CREATED)
				.entity(payment)
				.build();
	}

	public Response getPayments() {
		List<Payment> payments = new ArrayList<Payment> ();

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String paid = rs.getString("paid");
				String cvv = rs.getString("cvv");
				String name = rs.getString("name");
				String date = rs.getString("date");
				String amount = rs.getString("amount");
				Payment payment = new Payment(paid, cvv, name, date,amount);
				payment.setId(id);
				payments.add(payment);

			}
			con.close();

		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}

		return Response
				.status(Response.Status.OK)
				.entity(payments)
				.build();
	}

	public Response getPaymentById(Integer paymentid) {
		Payment payment = null;

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from payment where id = " + paymentid;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String paid = rs.getString("paid");
				String cvv = rs.getString("cvv");
				String name = rs.getString("name");
				String date = rs.getString("date");
				String amount = rs.getString("amount");
				payment = new Payment(paid, cvv, name, date,amount);
				payment.setId(id);
			}
			con.close();

		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}

		return Response
				.status(Response.Status.OK)
				.entity(payment)
				.build();
	}

	public Response deletePayment(Integer paymentid) {
		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "DELETE from payment WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, paymentid);

			preparedStmt.execute();
			con.close();

		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}

		return Response
				.status(Response.Status.OK)
				.entity("Product Payment deleted successfully")
				.build();
	}

	public Response updatePayment(Payment payment) {
		try
		{
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "UPDATE payment SET paid=?, cvv=?, name=?, date=?, amount=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, payment.getPaid());
			preparedStmt.setString(2, payment.getCvv());
			preparedStmt.setString(3, payment.getName());
			preparedStmt.setString(4, payment.getDate());
			preparedStmt.setString(5, payment.getAmount());
			preparedStmt.setInt(6, payment.getId());

			preparedStmt.execute();
			con.close();
		}
		catch (Exception e)
		{
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error while updating the item")
					.build();
		}

		return Response
				.status(Response.Status.CREATED)
				.entity(payment)
				.build();
	}

}
