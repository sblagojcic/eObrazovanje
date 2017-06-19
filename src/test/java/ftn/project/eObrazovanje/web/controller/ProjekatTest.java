package ftn.project.eObrazovanje.web.controller;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
public class ProjekatTest {
	protected Logger loger = LoggerFactory.getLogger(this.getClass());
}
