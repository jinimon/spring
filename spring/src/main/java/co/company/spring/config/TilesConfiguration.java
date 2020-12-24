package co.company.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;


//@Configuration
public class TilesConfiguration {
	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver tilesView = new UrlBasedViewResolver();
		tilesView.setViewClass(TilesView.class);
		tilesView.setOrder(2);
		return tilesView;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] { "/WEB-INF/views/tiles/tiles.xml"});
		return tiles;
	}
}
