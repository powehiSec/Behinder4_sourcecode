//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.jensd.fx.glyphs.fontawesome;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.text.Font;
import de.jensd.fx.glyphs.GlyphIcon;

public class FontAwesomeIconView extends GlyphIcon<FontAwesomeIcon> {
    public static final String TTF_PATH = "/de/jensd/fx/glyphs/fontawesome/fontawesome-webfont.ttf";

    public FontAwesomeIconView(FontAwesomeIcon icon, String iconSize) {
        super(FontAwesomeIcon.class);
        this.setIcon(icon);
        this.setStyle(String.format("-fx-font-family: %s; -fx-font-size: %s;", icon.fontFamily(), iconSize));
    }

    public FontAwesomeIconView(FontAwesomeIcon icon) {
        this(icon, "1em");
    }

    public FontAwesomeIconView() {
        this(FontAwesomeIcon.ANCHOR);
    }

    public FontAwesomeIcon getDefaultGlyph() {
        return FontAwesomeIcon.ANCHOR;
    }

    static {
        try {
            Font.loadFont(FontAwesomeIconView.class.getResource("/de/jensd/fx/glyphs/fontawesome/fontawesome-webfont.ttf").openStream(), 10.0D);
        } catch (IOException var1) {
            Logger.getLogger(FontAwesomeIconView.class.getName()).log(Level.SEVERE, (String)null, var1);
        }

    }
}
