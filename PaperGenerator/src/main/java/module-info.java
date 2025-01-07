module com.mycompany.papergenerator {
    requires javafx.controls;
    requires java.desktop;
    requires java.sql;

    exports com.mycompany.papergenerator;

    // Add these for modularized libraries
    requires kernel; // If the kernel library is available as an automatic module
    requires layout; // If the layout library is available as an automatic module
}
