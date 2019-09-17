package yitian.learn.servlet;

import yitian.learn.dao.MemoryUserRepository;
import yitian.learn.dao.UserRepository;
import yitian.learn.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ListAllServlet", urlPatterns = {"/list"})
public class ListAllServlet extends HttpServlet {
  private List<User> users;
  private UserRepository repository;

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
    int totalUsers = users.size();
    //每页用户数
    int usersPerPage = 20;
    //总页数
    int totalPages = totalUsers % usersPerPage == 0 ? totalUsers / usersPerPage : totalUsers / usersPerPage + 1;
    //本页起始用户序号
    int beginIndex = (page - 1) * usersPerPage;
    //本页末尾用户序号的下一个
    int endIndex = beginIndex + usersPerPage;
    if (endIndex > totalUsers)
      endIndex = totalUsers;
    req.setAttribute("totalUsers", totalUsers);
    req.setAttribute("usersPerPage", usersPerPage);
    req.setAttribute("totalPages", totalPages);
    req.setAttribute("beginIndex", beginIndex);
    req.setAttribute("endIndex", endIndex);
    req.setAttribute("page", page);
    req.setAttribute("users", users);
    req.getRequestDispatcher("list.jsp").forward(req, resp);
  }

  @Override
  public void init() throws ServletException {
    repository = new MemoryUserRepository();
    users = repository.listAll();
  }
}
