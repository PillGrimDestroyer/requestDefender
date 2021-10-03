package kz.hawk.requestdefender.util;

import kz.hawk.requestdefender.RequestDefenderApplication;
import kz.hawk.requestdefender.dao.BeanConfigTestDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Import;

@Import({RequestDefenderApplication.class})
@MapperScan(basePackageClasses = BeanConfigTestDao.class)
public class BeanConfigTests {
}
