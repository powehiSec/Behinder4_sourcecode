package de.jensd.fx.glyphs;

public interface GlyphStackIcons<T extends Enum<T> & GlyphIcons> {
   String name();

   T[] getGlyphs();
}
