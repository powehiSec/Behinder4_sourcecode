package de.jensd.fx.glyphs.fontawesome.utils;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.Comparator;

public class FontAwesomeIconNameComparator implements Comparator<FontAwesomeIcon> {
   public int compare(FontAwesomeIcon o1, FontAwesomeIcon o2) {
      return o1 != null && o2 != null ? o1.name().compareTo(o2.name()) : 0;
   }
}
