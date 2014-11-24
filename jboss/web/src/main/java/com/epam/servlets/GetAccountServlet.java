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
 * Servlet implementation class GetAccountServlet
 */
@WebServlet(description = "Get account servlet", urlPatterns = {"/GetAccountServlet", "/GetAccountServlet.do"})
public class GetAccountServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @EJB
    IAccountDaoLocal accountDaoLocal;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Account account = accountDaoLocal.getAccountById(id);
        request.setAttribute("account", account);
        request.getRequestDispatcher("WEB-INF/account.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
    }

}
