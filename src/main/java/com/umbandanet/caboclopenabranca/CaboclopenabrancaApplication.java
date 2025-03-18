package com.umbandanet.caboclopenabranca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.umbandanet.caboclopenabranca.model")
public class CaboclopenabrancaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaboclopenabrancaApplication.class, args);
    }
}
