package org.vsynytsyn.fidotestbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FidoTestBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FidoTestBackendApplication.class, args);
    }

    /*
    * To start the app, the following env variables must be set:
    * * dbUsername
    * * dbPassword
    * * dbURL
    * - credentials for connection and link to the db in postgres
    *
    * * JWTSecret
    * - any char sequence
    *
    * * adminPassword
    * - password is needed to login to the app and create users and rooms
    * - login is admin@example.com
    *
    * */
}
