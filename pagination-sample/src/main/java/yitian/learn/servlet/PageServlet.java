package yitian.learn.servlet;

import yitian.learn.dao.MemoryUserRepository;
import yitian.learn.dao.UserRepository;
import yitian.learn.entity.User;
import yitian.learn.util.PaginationHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "pageServlet", urlPatterns = {"/page"})
public class PageServlet extends HttpServlet {
  private List<User> users;
  private UserRepository repository;
  private PaginationHelper pagination;
  private int countPerPage;

  @Override
  public void init() throws ServletException {
    repository = new MemoryUserRepository();
    users = repository.listAll();
    pagination = new PaginationHelper();
    countPerPage = 20;
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 当前页数
    int page;
    try {
      page = Integer.valueOf(req.getParameter("page"));
    } catch (NumberFormatException e) {
      page = 1;
    }
    try {
      if (req.getParameter("countPerPage") != null)
        countPerPage = Integer.valueOf(req.getParameter("countPerPage"));
    } catch (NumberFormatException e) {
      countPerPage = 20;
    }
    // 设置用户总数
    pagination.setTotalCount(users.size());
    // 设置每页用户数
    pagination.setCountPerPage(countPerPage);

    req.setAttribute("pagination", pagination);
    req.setAttribute("users", users.subList(pagination.getCurrentPageStart(page), pagination.getCurrentPageEnd(page)));
    req.setAttribute("page", page);
    req.getRequestDispatcher("page.jsp").forward(req, resp);
  }
}
