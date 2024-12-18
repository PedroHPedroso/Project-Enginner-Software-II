module app {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires org.postgresql.jdbc;
    requires java.sql;
 
    requires org.junit.jupiter.api;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    
    opens Controller to javafx.fxml;
    opens model to org.hibernate.orm.core;
 
    exports Controller;
    exports Padroes;
    exports model;
}


