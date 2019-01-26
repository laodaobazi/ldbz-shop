package cn.ldbz.tags;

import java.util.HashMap;
import java.util.Map;

import org.beetl.core.Tag;
import org.beetl.core.TagFactory;
import org.beetl.ext.spring.SpringBeanTagFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import cn.ldbz.tags.annotation.BeetlTagName;

@Component
public class BeetlTagFactoryManager implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean(name = "beetlTagFactorys")
	public Map<String, TagFactory> tagFactorys() {
		Map<String, TagFactory> tags = new HashMap<>();
		Map<String, Tag> beans = applicationContext.getBeansOfType(Tag.class);
		for (String beanName : beans.keySet()) {
			BeetlTagName tagAnno = beans.get(beanName).getClass().getAnnotation(BeetlTagName.class);
			String tagName = tagAnno != null ? tagAnno.value() : beanName;
			tags.put(tagName, toSpringBeanTagFactory(beanName));
		}
		return tags;
	}

	private SpringBeanTagFactory toSpringBeanTagFactory(String beanName) {
		SpringBeanTagFactory springBeanTagFactory = new SpringBeanTagFactory();
		springBeanTagFactory.setApplicationContext(applicationContext);
		springBeanTagFactory.setName(beanName);
		return springBeanTagFactory;
	}
}
