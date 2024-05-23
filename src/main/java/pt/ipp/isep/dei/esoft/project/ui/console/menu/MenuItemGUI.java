package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import javafx.fxml.Initializable;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class MenuItemGUI {
    private final String description;
    private final Initializable gui;

    public MenuItemGUI(String description, Initializable gui) {
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        }
        if (Objects.isNull(gui)) {
            throw new IllegalArgumentException("MenuItem does not support a null GUI.");
        }

        this.description = description;
        this.gui = gui;
    }

    public void run() {
        this.gui.initialize(null, null);
    }

    public boolean hasDescription(String description) {
        return this.description.equals(description);
    }

    public String toString() {
        return this.description;
    }
}