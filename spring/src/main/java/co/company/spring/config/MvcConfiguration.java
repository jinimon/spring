package co.company.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import co.company.spring.common.AuthCheckInterceptor;

//import co.company.spring.controller.Greeter;

@Configuration
@ComponentScan(basePackages = "co.company")
@EnableWebMvc	// messageConvertor 빈 등록됨
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MvcConfiguration implements WebMvcConfigurer {
	/**
	 * 언어 변경을 위한 인터셉터를 생성한다.
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	/**
	 * 변경된 언어 정보를 기억할 로케일 리졸퍼를 생성한다. 여기서는 세션에 저장하는 방식을 사용한다.
	 * 
	 * @return
	 */
	@Bean
	public SessionLocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}

	/**
	 * 인터셉터를 등록한다.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		// 2. 등록한 빈 인터셉터에 추가
		// 이렇게 하면 모든 페이지에서 적용되기때문에 패턴 적용을 해줘야한다.
		// emp로 시작되는 모든 요청 url만 로그인 체크 하도록 수정
		registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/emp*");
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		// 파일 더 있으면 안에 ,로 추가해주면 된다.
		ms.setBasename("message.label"); // 폴더이름.파일명(확장자적을필요x)
		ms.setDefaultEncoding("UTF-8");
		ms.setCacheSeconds(10); // 일정 시간마다 파일 다시 읽어들임
		return ms;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(3);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	}

	@Bean
	// 1. 인터셉터 빈 등록
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}
	
	@Bean
	// bean 등록시의 id가 메소드 명이된다. 중요!
	public CommonsMultipartResolver multipartResolver() {
		// 객체 생성
		CommonsMultipartResolver multi = new CommonsMultipartResolver();
		
		// 옵션 추가 가능
		// setMaxUploadSizePerFile : 파일 하나당 업로드 가능한 최대 크기
		// setMaxUploadSize : 업로드하는 파일 총 양
		// setUploadTempDir : 이거 생략하면 각각 가지고 있는 임시폴더에 저장.
		multi.setMaxUploadSize(1024*10000);	// 10MB
		return multi;
	}
	
	@Bean
	BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver bean = new BeanNameViewResolver();
		bean.setOrder(1);
		return bean;
	}
}