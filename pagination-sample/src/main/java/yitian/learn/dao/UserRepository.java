package yitian.learn.dao;


import yitian.learn.entity.User;

import java.util.List;

public interface UserRepository {
  List<User> listAll();
}
