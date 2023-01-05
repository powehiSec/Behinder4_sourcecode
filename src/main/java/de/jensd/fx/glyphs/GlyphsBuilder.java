package de.jensd.fx.glyphs;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GlyphsBuilder {
   private GlyphIcon glyphIcon;

   private GlyphsBuilder(Class<? extends GlyphIcon> clazz) {
      try {
         this.glyphIcon = (GlyphIcon)clazz.getDeclaredConstructor().newInstance();
      } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException var3) {
         Logger.getLogger(GlyphsBuilder.class.getName()).log(Level.SEVERE, (String)null, var3);
      }

   }

   public static GlyphsBuilder create(Class<? extends GlyphIcon> clazz) {
      return new GlyphsBuilder(clazz);
   }

   public GlyphsBuilder glyph(GlyphIcons glyph) {
      this.glyphIcon.setGlyphName(glyph.name());
      return this;
   }

   public GlyphsBuilder size(String size) {
      this.glyphIcon.setSize(size);
      return this;
   }

   public GlyphsBuilder style(String style) {
      this.glyphIcon.setGlyphStyle(style);
      return this;
   }

   public GlyphsBuilder styleClass(String styleClass) {
      this.glyphIcon.setStyleClass(styleClass);
      return this;
   }

   public GlyphIcon build() {
      return this.glyphIcon;
   }
}
