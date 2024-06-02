package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import javafx.fxml.Initializable;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class MenuItemGUI {
    private String displayName;
    private String fxmlFileName;

    public MenuItemGUI(String displayName, String fxmlFileName) {
        this.displayName = displayName;
        this.fxmlFileName = fxmlFileName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFxmlFileName() {
        return fxmlFileName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}