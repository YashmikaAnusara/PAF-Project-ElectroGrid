package com.electrogrid.PAF.service;

import com.electrogrid.PAF.model.Bill;
import com.electrogrid.PAF.repository.DBConnection;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BillService {
	private DBConnection connection = new DBConnection();

	public Response addBill(Bill bill) {
		int insertedId = -99;
		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "INSERT INTO bill(nic, price, date, meterReader) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStmt.setString(1, bill.getNic());
			preparedStmt.setString(2, bill.getPrice());
			preparedStmt.setString(3, bill.getDate());
			preparedStmt.setString(4, bill.getMeterReader());

			preparedStmt.execute();
			ResultSet rs = preparedStmt.getGeneratedKeys();
			if (rs.next()){
				insertedId  = Integer.parseInt(rs.getString(1));
			}
			con.close();

			bill.setId(insertedId);
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
		return Response
				.status(Response.Status.CREATED)
				.entity(bill)
				.build();
	}

	public Response getBills() {
		List<Bill> bills = new ArrayList<Bill> ();

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from bill";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nic = rs.getString("nic");
				String price = rs.getString("price");
				String date = rs.getString("date");
				String meterReader = rs.getString("meterReader");
				Bill bill = new Bill(nic, price, date, meterReader);
				bill.setId(id);
				bills.add(bill);

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
				.entity(bills)
				.build();
	}

	public Response getBillById(Integer billid) {
		Bill bill = null;

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from bill where id = " + billid;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nic = rs.getString("nic");
				String price = rs.getString("price");
				String date = rs.getString("date");
				String meterReader = rs.getString("meterReader");
				bill = new Bill(nic, price, date, meterReader);
				bill.setId(id);
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
				.entity(bill)
				.build();
	}

	public Response deleteBill(Integer billid) {
		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "DELETE from bill WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, billid);

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
				.entity("Bill deleted successfully")
				.build();
	}

	public Response updateBill(Bill bill) {
		try
		{
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "UPDATE bill SET nic=?, price=?, date=?, meterReader=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, bill.getNic());
			preparedStmt.setString(2, bill.getPrice());
			preparedStmt.setString(3, bill.getDate());
			preparedStmt.setString(4, bill.getMeterReader());
			preparedStmt.setInt(5, bill.getId());

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
				.entity(bill)
				.build();
	}

}
