package kz.hawk.requestdefender.util;

import kz.hawk.requestdefender.beans.all.BeanConfigAll;
import kz.hawk.requestdefender.config.BeanConfigTestConfigs;
import kz.hawk.requestdefender.dao.BeanConfigTestDao;
import org.springframework.context.annotation.Import;

@Import({BeanConfigAll.class, BeanConfigTestDao.class, BeanConfigTestConfigs.class})
public class BeanConfigTests {
}
