package de.jensd.fx.glyphs;

public enum GlyphsStyle {
   DEFAULT("/styles/glyphs.css"),
   DARK("/styles/glyphs_dark.css"),
   LIGHT("/styles/glyphs_light.css"),
   BLUE("/styles/glyphs_blue.css"),
   RED("/styles/glyphs_red.css");

   private final String stylePath;

   private GlyphsStyle(String stylePath) {
      this.stylePath = stylePath;
   }

   public String getStylePath() {
      return this.stylePath;
   }

   public String toString() {
      return this.stylePath;
   }

   // $FF: synthetic method
   private static GlyphsStyle[] $values() {
      return new GlyphsStyle[]{DEFAULT, DARK, LIGHT, BLUE, RED};
   }
}
