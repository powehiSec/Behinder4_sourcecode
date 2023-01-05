package de.jensd.fx.glyphs;

import java.lang.reflect.Field;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GlyphsFactory {
   public GlyphsFactory(Class clazz) {
      this.loadFont(this.getPathToFontFromClass(clazz));
   }

   public GlyphsFactory(String pathToIconFont) {
      this.loadFont(pathToIconFont);
   }

   private String getPathToFontFromClass(Class clazz) {
      String path = "";

      try {
         Field ttfPath = clazz.getField("TTF_PATH");
         path = (String)ttfPath.get((Object)null);
         return path;
      } catch (IllegalAccessException | NoSuchFieldException var4) {
         throw new RuntimeException(var4);
      }
   }

   private final void loadFont(String pathToIconFont) {
      try {
         Font.loadFont(this.getClass().getResource(pathToIconFont).openStream(), 10.0);
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }

   public Text createIcon(GlyphIcons icon) {
      return this.createIcon(icon, "1em");
   }

   public Text createIcon(GlyphIcons icon, String iconSize) {
      Text text = new Text(icon.unicode());
      text.getStyleClass().add("glyph-icon");
      text.setStyle(String.format("-fx-font-family: %s; -fx-font-size: %s;", icon.fontFamily(), iconSize));
      return text;
   }

   public Label createIconLabel(GlyphIcons icon, String text, String iconSize, String fontSize, ContentDisplay contentDisplay) {
      Text iconLabel = this.createIcon(icon, iconSize);
      Label label = new Label(text);
      label.setStyle("-fx-font-size: " + fontSize);
      label.setGraphic(iconLabel);
      label.setContentDisplay(contentDisplay);
      return label;
   }

   public Button createIconButton(GlyphIcons icon) {
      return this.createIconButton(icon, "");
   }

   public Button createIconButton(GlyphIcons icon, String text) {
      Text label = this.createIcon(icon, "1em");
      Button button = new Button(text);
      button.setGraphic(label);
      return button;
   }

   public Button createIconButton(GlyphIcons icon, String text, String iconSize, String fontSize, ContentDisplay contentDisplay) {
      Text label = this.createIcon(icon, iconSize);
      Button button = new Button(text);
      button.setStyle("-fx-font-size: " + fontSize);
      button.setGraphic(label);
      button.setContentDisplay(contentDisplay);
      return button;
   }

   public ToggleButton createIconToggleButton(GlyphIcons icon) {
      return this.createIconToggleButton(icon, "");
   }

   public ToggleButton createIconToggleButton(GlyphIcons icon, String text) {
      return this.createIconToggleButton(icon, text, "1em");
   }

   public ToggleButton createIconToggleButton(GlyphIcons icon, String text, String iconSize) {
      Text label = this.createIcon(icon, iconSize);
      ToggleButton button = new ToggleButton(text);
      button.setGraphic(label);
      return button;
   }

   public ToggleButton createIconToggleButton(GlyphIcons icon, String text, String iconSize, ContentDisplay contentDisplay) {
      return this.createIconToggleButton(icon, text, iconSize, "1em", contentDisplay);
   }

   public ToggleButton createIconToggleButton(GlyphIcons icon, String text, String iconSize, String fontSize, ContentDisplay contentDisplay) {
      Text label = this.createIcon(icon, iconSize);
      ToggleButton button = new ToggleButton(text);
      button.setStyle("-fx-font-size: " + fontSize);
      button.setGraphic(label);
      button.setContentDisplay(contentDisplay);
      return button;
   }

   public void setIcon(Tab tab, GlyphIcons icon) {
      this.setIcon(tab, icon, "1em");
   }

   public void setIcon(Tab tab, GlyphIcons icon, String iconSize) {
      tab.setGraphic(this.createIcon(icon, iconSize));
   }

   public void setIcon(Labeled labeled, GlyphIcons icon) {
      this.setIcon(labeled, icon, "1em");
   }

   public void setIcon(Labeled labeled, GlyphIcons icon, ContentDisplay contentDisplay) {
      this.setIcon(labeled, icon, "1em", contentDisplay);
   }

   public void setIcon(Labeled labeled, GlyphIcons icon, String iconSize) {
      this.setIcon(labeled, icon, iconSize, ContentDisplay.LEFT);
   }

   public void setIcon(Labeled labeled, GlyphIcons icon, String iconSize, ContentDisplay contentDisplay) {
      if (labeled == null) {
         throw new IllegalArgumentException("The component must not be 'null'!");
      } else {
         labeled.setGraphic(this.createIcon(icon, iconSize));
         labeled.setContentDisplay(contentDisplay);
      }
   }

   public void setIcon(MenuItem menuItem, GlyphIcons icon) {
      this.setIcon(menuItem, icon, "1em", "1em");
   }

   public void setIcon(MenuItem menuItem, GlyphIcons icon, String iconSize) {
      this.setIcon(menuItem, icon, "1em", iconSize);
   }

   public void setIcon(MenuItem menuItem, GlyphIcons icon, String fontSize, String iconSize) {
      if (menuItem == null) {
         throw new IllegalArgumentException("The menu item must not be 'null'!");
      } else {
         Text label = this.createIcon(icon, iconSize);
         menuItem.setStyle("-fx-font-size: " + fontSize);
         menuItem.setGraphic(label);
      }
   }

   public void setIcon(TreeItem treeItem, GlyphIcons icon) {
      this.setIcon(treeItem, icon, "1em");
   }

   public void setIcon(TreeItem treeItem, GlyphIcons icon, String iconSize) {
      if (treeItem == null) {
         throw new IllegalArgumentException("The tree item must not be 'null'!");
      } else {
         Text label = this.createIcon(icon, iconSize);
         treeItem.setGraphic(label);
      }
   }
}
