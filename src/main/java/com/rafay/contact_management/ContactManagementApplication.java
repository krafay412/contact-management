package com.rafay.contact_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class, 
    HibernateJpaAutoConfiguration.class
})
public class ContactManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactManagementApplication.class, args);
	}

}
