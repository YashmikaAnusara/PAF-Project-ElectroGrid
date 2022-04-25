package com.electrogrid.PAF.service;

import com.electrogrid.PAF.model.Feedback;
import com.electrogrid.PAF.repository.DBConnection;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeedbackService {
	private DBConnection connection = new DBConnection();

	public Response addFeedback(Feedback feedback) {
		int insertedId = -99;
		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "INSERT INTO feedback(nic, name, address, message) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStmt.setString(1, feedback.getNic());
			preparedStmt.setString(2, feedback.getName());
			preparedStmt.setString(3, feedback.getAddress());
			preparedStmt.setString(4, feedback.getMessage());

			preparedStmt.execute();
			ResultSet rs = preparedStmt.getGeneratedKeys();
			if (rs.next()){
				insertedId  = Integer.parseInt(rs.getString(1));
			}
			con.close();

			feedback.setId(insertedId);
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
		return Response
				.status(Response.Status.CREATED)
				.entity(feedback)
				.build();
	}

	public Response getFeedbacks() {
		List<Feedback> feedbacks = new ArrayList<Feedback> ();

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from feedback";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nic = rs.getString("nic");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String message = rs.getString("message");
				Feedback feedback = new Feedback(nic, name, address, message);
				feedback.setId(id);
				feedbacks.add(feedback);

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
				.entity(feedbacks)
				.build();
	}

	public Response getFeedbackById(Integer feedbackid) {
		Feedback feedback = null;

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from feedback where id = " + feedbackid;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nic = rs.getString("nic");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String message = rs.getString("message");
				feedback = new Feedback(nic, name, address, message);
				feedback.setId(id);
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
				.entity(feedback)
				.build();
	}

	public Response deleteFeedback(Integer feedbackid) {
		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "DELETE from feedback WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, feedbackid);

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
				.entity("Product Feedback deleted successfully")
				.build();
	}

	public Response updateFeedback(Feedback feedback) {
		try
		{
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "UPDATE feedback SET nic=?, name=?, address=?, message=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, feedback.getNic());
			preparedStmt.setString(2, feedback.getName());
			preparedStmt.setString(3, feedback.getAddress());
			preparedStmt.setString(4, feedback.getMessage());
			preparedStmt.setInt(5, feedback.getId());

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
				.entity(feedback)
				.build();
	}

}
