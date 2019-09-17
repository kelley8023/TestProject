package yitian.learn.dao;


import yitian.learn.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUserRepository implements PageableUserRepository {
  private Connection connection;
  private static final String url = "jdbc:mysql://localhost:3306/page?useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai";
  //  "jdbc:mysql://localhost:3306/page"
  private static final String username = "root";
  private static final String password = "12345678";

  public DataBaseUserRepository() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<User> listAll() {
    List<User> users = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement("SELECT id,name,password,birthday FROM user ")) {
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setName(rs.getString(2));
        user.setPassword(rs.getString(3));
        user.setBirthday(rs.getObject(4, LocalDate.class));
        users.add(user);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return users;
  }

  @Override
  public List<User> listAllOf(int startIndex, int offset) {
    List<User> users = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement("SELECT id,name,password,birthday FROM user LIMIT ?,?")) {
      statement.setInt(1, startIndex);
      statement.setInt(2, offset);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setName(rs.getString(2));
        user.setPassword(rs.getString(3));
        user.setBirthday(rs.getObject(4, LocalDate.class));
        users.add(user);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return users;
  }

  @Override
  public int counts() {
    try (CallableStatement statement = connection.prepareCall("CALL user_counts(?)")) {
      statement.registerOutParameter(1, Types.INTEGER);
      statement.execute();
      return statement.getInt(1);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
