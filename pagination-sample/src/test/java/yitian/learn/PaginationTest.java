package yitian.learn;

import org.junit.Test;
import yitian.learn.util.PaginationHelper;

import static org.junit.Assert.assertEquals;

public class PaginationTest {
  @Test
  public void testPagination() {
    PaginationHelper helper = new PaginationHelper();
    helper.setTotalCount(62);
    helper.setCountPerPage(20);
    assertEquals(4, helper.getPageCount());
    assertEquals(0, helper.getCurrentPageStart(1));
    assertEquals(60, helper.getCurrentPageStart(4));
    assertEquals(62, helper.getCurrentPageEnd(4));
  }
}

