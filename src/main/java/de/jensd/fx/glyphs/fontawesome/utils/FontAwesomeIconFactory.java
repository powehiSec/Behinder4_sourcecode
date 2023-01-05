package de.jensd.fx.glyphs.fontawesome.utils;

import de.jensd.fx.glyphs.GlyphsFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class FontAwesomeIconFactory extends GlyphsFactory {
   private static FontAwesomeIconFactory me;

   private FontAwesomeIconFactory() {
      super(FontAwesomeIconView.class);
   }

   public static FontAwesomeIconFactory get() {
      if (me == null) {
         me = new FontAwesomeIconFactory();
      }

      return me;
   }
}
