package yitian.learn.dao;


import yitian.learn.entity.User;

import java.util.List;

public interface PageableUserRepository extends UserRepository {
  List<User> listAllOf(int startIndex, int offset);

  int counts();
}
