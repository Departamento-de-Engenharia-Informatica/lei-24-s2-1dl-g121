module pt.ipp.isep.dei.esoft.project {
    requires AuthLib;
    requires gs.core;
    requires java.datatransfer;
    requires java.desktop;
    requires java.logging;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.apache.commons.lang3;
    requires java.sql.rowset;

    exports pt.ipp.isep.dei.esoft.project.ui;
    exports pt.ipp.isep.dei.esoft.project.ui.console.menu;
    exports pt.ipp.isep.dei.esoft.project.ui.gui;

    opens pt.ipp.isep.dei.esoft.project.ui to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.gui to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.gui.authorization;
    opens pt.ipp.isep.dei.esoft.project.ui.gui.authorization to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.gui.menu;
    opens pt.ipp.isep.dei.esoft.project.ui.gui.menu to javafx.fxml;

}