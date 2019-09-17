package yitian.learn.dao;


import yitian.learn.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemoryUserRepository implements UserRepository {
  public static final int COUNTS = 302;

  @Override
  public List<User> listAll() {
    List<User> users = new ArrayList<>();
    for (int i = 0; i < COUNTS; ++i) {
      User user = new User();
      user.setId(i + 1);
      user.setName("用户" + i + 1);
      user.setPassword("12345" + i);
      user.setBirthday(LocalDate.now());
      users.add(user);
    }
    return users;
  }
}
