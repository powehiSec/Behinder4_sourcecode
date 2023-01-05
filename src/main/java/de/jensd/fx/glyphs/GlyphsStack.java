package de.jensd.fx.glyphs;

import java.util.Collection;
import javafx.scene.layout.StackPane;

public class GlyphsStack extends StackPane {
   public static GlyphsStack create() {
      return new GlyphsStack();
   }

   public GlyphsStack add(GlyphIcon icon) {
      this.getChildren().add(icon);
      return this;
   }

   public GlyphsStack addAll(GlyphIcon... icons) {
      if (icons != null && icons.length > 0) {
         this.getChildren().addAll(icons);
      }

      return this;
   }

   public GlyphsStack addAll(Collection<? extends GlyphIcon> icons) {
      if (icons != null && !icons.isEmpty()) {
         this.getChildren().addAll(icons);
      }

      return this;
   }

   public GlyphsStack addAll(int index, Collection<? extends GlyphIcon> icons) {
      if (icons != null && !icons.isEmpty()) {
         this.getChildren().addAll(index, icons);
      }

      return this;
   }

   public GlyphsStack remove(GlyphIcon icon) {
      this.getChildren().remove(icon);
      return this;
   }
}
