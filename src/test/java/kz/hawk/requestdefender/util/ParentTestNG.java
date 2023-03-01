package kz.hawk.requestdefender.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.IdGenerator;

@SpringBootTest(classes = {BeanConfigTests.class})
public class ParentTestNG extends AbstractTestNGSpringContextTests {

  @Autowired
  protected IdGenerator idGenerator;

}
