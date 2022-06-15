package br.com.south.apirest.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;


@Component
public class DateUtil {

	public String formatLocalDateTime(LocalDateTime localDateTime) {
		return DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss").format(localDateTime);
	}

}
