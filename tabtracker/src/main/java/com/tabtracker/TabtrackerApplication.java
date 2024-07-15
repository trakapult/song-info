package com.tabtracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.CommandLineRunner;

import com.tabtracker.services.SeedService;

@SpringBootApplication
@EnableJpaAuditing
public class TabtrackerApplication implements CommandLineRunner {
  @Autowired
  private SeedService seedService;

  public static void main(String[] args) {
    SpringApplication.run(TabtrackerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    seedService.run();
  }
}
