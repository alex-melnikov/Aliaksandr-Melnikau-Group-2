package com.epam.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.bean.Account;
import com.epam.dao.interfaces.IAccountDaoLocal;

/**
 * Servlet implementation class CreateNewAccountServlet
 */
@WebServlet(description = "Create new Account servlet", urlPatterns = {"/CreateNewAccountServlet", "/CreateNewAccountServlet.do"})
public class CreateNewAccountServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @EJB
    IAccountDaoLocal accountDaoLocal;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");
        Double balance = Double.parseDouble(request.getParameter("balance"));
        String currency = request.getParameter("currency");
        Account account = new Account(name, balance, currency);
        accountDaoLocal.createAccount(account);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
    }

}
