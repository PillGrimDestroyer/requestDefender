package kz.hawk.requestdefender.beans.all;

import kz.hawk.requestdefender.controller.BeanConfigControllers;
import kz.hawk.requestdefender.impl.BeanConfigRegister;
import kz.hawk.requestdefender.scheduler.BeanConfigScheduler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@Import({BeanConfigControllers.class, BeanConfigRegister.class, BeanConfigScheduler.class})
public class BeanConfigAll {
}
