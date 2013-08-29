package com.androidtest8web;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import flyrestaurant.entity.Order;
import flyrestaurant.entity.Table;
import flyrestaurant.entity.User;
import flyrestaurant.ph03.service.FoodService;
import flyrestaurant.ph03.service.OrderService;
import flyrestaurant.ph03.service.TableService;
import flyrestaurant.ph03.service.UserService;




@WebServlet("/")
public class OnlyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService = new UserService();
	FoodService foodService = new FoodService();
	TableService tableService = new TableService();
	OrderService orderService = new OrderService();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream");
		String to = request.getParameter("to");
		if ("login".equals(to)) {
			login(request, response);
		} else if ("update".equals(to)) {
			update(request, response);
		} else if ("queryTable".equals(to)) {
			queryTable(request, response);
		} else if ("getOrder".equals(to)) {
			getOrder(request, response);
		} else if ("addOrder".equals(to)) {
			addOrder(request, response);
		} else if ("pay".equals(to)) {
			pay(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("--------------login----------");
		String code = request.getParameter("code");
		String password = request.getParameter("password");
		User user = userService.login(code, password);
		sendObject(response, user);
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		@SuppressWarnings("rawtypes")
		List[] data = new List[3];
		data[0] = foodService.getFoods();
		data[1] = foodService.getFoodTypes();
		data[2] = tableService.getTables();

		sendObject(response, data);
	}

	public void queryTable(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String s = request.getParameter("seats");
		String n = request.getParameter("needEmpty");
		int seats = 0;
		boolean needEmpty = false;
		try {
			seats = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (n != null) {
			needEmpty = true;
		}

		List<Table> tables = tableService.query(seats, needEmpty);
		sendObject(response, tables);
	}

	public void getOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String code = request.getParameter("code");
		Order order = orderService.getOrder(code);
		sendObject(response, order);
	}

	public void addOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		try {
			Order order = (Order) ois.readObject();
			orderService.addOrder(order);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ois.close();
		is.close();
	}

	public void pay(HttpServletRequest request, HttpServletResponse response) {
		int orderId = 0;
		String id = request.getParameter("orderId");
		try {
			orderId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		orderService.pay(orderId);
	}

	public void sendObject(HttpServletResponse response, Object object)
			throws IOException {
		OutputStream os = response.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(object);
		oos.flush();
		oos.close();
	}
}
