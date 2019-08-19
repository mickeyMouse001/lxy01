package com.lxy.config.server.load;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.env.PropertySource;
/**
 * 解决中文乱码
 */
public class MyPropertiesHandler implements PropertySourceLoader {

    private static final Logger logger = LoggerFactory.getLogger(MyPropertiesHandler.class);

    @Override
    public String[] getFileExtensions() {
        return new String[]{"properties", "xml"};
    }


    private Map<String, String> getProperties(Resource resource) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        Map<String, String> map =null;
        try {
            inputStream = resource.getInputStream();
            properties.load(new InputStreamReader(inputStream, "utf-8"));
            map = new HashMap<String, String>((Map) properties);
            System.out.println(map);
            inputStream.close();
        } catch (IOException e) {
            logger.error("load inputstream failure...", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close IO failure ....", e);
                }
            }
        }
        return map;
    }

	@Override
	public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
		Map<String, ?> properties = getProperties(resource);
		if (properties.isEmpty()) {
			return Collections.emptyList();
		}
		return Collections
				.singletonList(new OriginTrackedMapPropertySource(name, properties));
	}
}
