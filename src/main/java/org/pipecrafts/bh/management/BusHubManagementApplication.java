package org.pipecrafts.bh.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BusHubManagementApplication {

  public static void main(String[] args) {
     TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    SpringApplication.run(BusHubManagementApplication.class, args);
  }

}
