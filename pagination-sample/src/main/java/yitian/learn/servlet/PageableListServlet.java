package yitian.learn.servlet;

import yitian.learn.dao.DataBaseUserRepository;
import yitian.learn.dao.PageableUserRepository;
import yitian.learn.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "PageableListServlet", urlPatterns = {"/pageableList"})
public class PageableListServlet extends HttpServlet {
  private PageableUserRepository repository;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String p = req.getParameter("page");
    int page;
    try {
      //当前页数
      page = Integer.valueOf(p);
    } catch (NumberFormatException e) {
      page = 1;
    }
    //用户总数
    int totalUsers = repository.counts();
    //每页用户数
    int usersPerPage = 20;
    //总页数
    int totalPages = totalUsers % usersPerPage == 0 ? totalUsers / usersPerPage : totalUsers / usersPerPage + 1;
    //本页起始用户序号
    int beginIndex = (page - 1) * usersPerPage;
    List<User> users = repository.listAllOf(beginIndex, usersPerPage);
    req.setAttribute("totalPages", totalPages);
    req.setAttribute("page", page);
    req.setAttribute("users", users);
    req.getRequestDispatcher("pageableList.jsp").forward(req, resp);
  }

  @Override
  public void init() throws ServletException {
    repository = new DataBaseUserRepository();
  }
}
