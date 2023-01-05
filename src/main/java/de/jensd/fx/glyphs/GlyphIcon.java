package de.jensd.fx.glyphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class GlyphIcon<T extends Enum<T> & GlyphIcons> extends Text {
   public static final Double DEFAULT_ICON_SIZE = 12.0;
   public static final String DEFAULT_FONT_SIZE = "1em";
   private StringProperty glyphStyle;
   private String glyphFontFamily;
   private String unicode;
   private ObjectProperty<String> glyphName;
   private ObjectProperty<Number> glyphSize;
   public final Class<T> typeOfT;

   @FXML
   public void init() {
   }

   public GlyphIcon(Class<T> iconType) {
      this.typeOfT = iconType;
      this.initProperties();
   }

   private void initProperties() {
      this.getStyleClass().addAll("glyph-icon");
      this.glyphSizeProperty().addListener((observable) -> {
         this.updateSize();
      });
      this.glyphStyleProperty().addListener((observable) -> {
         this.updateStyle();
      });
      this.glyphNameProperty().addListener((observable) -> {
         this.updateIcon();
      });
      this.setIcon(this.getDefaultGlyph());
   }

   public final GlyphIcon setStyleClass(String styleClass) {
      this.getStyleClass().add(styleClass);
      return this;
   }

   public final StringProperty glyphStyleProperty() {
      if (this.glyphStyle == null) {
         this.glyphStyle = new SimpleStringProperty("");
      }

      return this.glyphStyle;
   }

   public final String getGlyphStyle() {
      return this.glyphStyleProperty().getValue();
   }

   public final void setGlyphStyle(String style) {
      if (!this.getGlyphStyle().isEmpty() && !this.getGlyphStyle().endsWith(";")) {
         style = ";".concat(style);
      }

      this.glyphStyleProperty().setValue(this.getGlyphStyle().concat(style));
   }

   public final ObjectProperty<String> glyphNameProperty() {
      if (this.glyphName == null) {
         this.glyphName = new SimpleStyleableObjectProperty(StyleableProperties.GLYPH_NAME, this, "glyphName");
      }

      return this.glyphName;
   }

   public final String getGlyphName() {
      return (String)this.glyphNameProperty().getValue();
   }

   public final void setGlyphName(String glyphName) {
      this.glyphNameProperty().setValue(glyphName);
   }

   public final String getGlyphFontFamily() {
      return this.glyphFontFamily;
   }

   public final ObjectProperty<Number> glyphSizeProperty() {
      if (this.glyphSize == null) {
         this.glyphSize = new SimpleStyleableObjectProperty(StyleableProperties.GLYPH_SIZE, this, "glyphSize");
         this.glyphSize.setValue(DEFAULT_ICON_SIZE);
      }

      return this.glyphSize;
   }

   public final Number getGlyphSize() {
      return (Number)this.glyphSizeProperty().getValue();
   }

   public final void setGlyphSize(Number size) {
      if (size == null) {
            size = DEFAULT_ICON_SIZE;
      }
      this.glyphSizeProperty().setValue(size);
   }
   public final String getSize() {
      return this.getGlyphSize().toString();
   }

   public final void setSize(String sizeExpr) {
      Number s = this.convert(sizeExpr);
      this.setGlyphSize(s);
   }

   public final void setIcon(T glyph) {
      this.setGlyphName(glyph.name());
      this.glyphFontFamily = ((GlyphIcons)glyph).fontFamily();
      this.unicode = ((GlyphIcons)glyph).unicode();
   }

   public String unicode() {
      return this.unicode;
   }

   public abstract T getDefaultGlyph();

   private void updateSize() {
      Font f = new Font(this.getFont().getFamily(), this.getGlyphSize().doubleValue());
      this.setFont(f);
      this.setGlyphStyle(String.format("-fx-font-family: %s; -fx-font-size: %s;", this.getGlyphFontFamily(), this.getGlyphSize().doubleValue()));
   }

   void updateIcon() {
      GlyphIcons icon = (GlyphIcons)this.getDefaultGlyph();

      try {
         icon = (GlyphIcons)Enum.valueOf(this.typeOfT, this.getGlyphName());
      } catch (Exception var4) {
         String msg = String.format("Icon '%s' not found. Using '%s' (default) instead", this.getGlyphName(), this.getDefaultGlyph());
         Logger.getLogger(GlyphIcon.class.getName()).log(Level.SEVERE, msg);
      }

      this.setText(icon.unicode());
   }

   private void updateStyle() {
      this.setStyle(this.getGlyphStyle());
   }

   public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
      return StyleableProperties.STYLEABLES;
   }

   public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
      return getClassCssMetaData();
   }

   public Number convert(String sizeString) {
      return GlyphIconUtils.convert(sizeString, this.getFont());
   }

   private static class StyleableProperties {
      private static final CssMetaData<GlyphIcon, String> GLYPH_NAME = new CssMetaData<GlyphIcon, String>("-glyph-name", StyleConverter.getStringConverter(), "BLANK") {
         public boolean isSettable(GlyphIcon styleable) {
            return styleable.glyphName == null || !styleable.glyphName.isBound();
         }

         public StyleableProperty<String> getStyleableProperty(GlyphIcon styleable) {
            return (StyleableProperty)styleable.glyphNameProperty();
         }

         public String getInitialValue(GlyphIcon styleable) {
            return "BLANK";
         }
      };
      private static final CssMetaData<GlyphIcon, Number> GLYPH_SIZE;
      private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

      static {
         GLYPH_SIZE = new CssMetaData<GlyphIcon, Number>("-glyph-size", StyleConverter.getSizeConverter(), GlyphIcon.DEFAULT_ICON_SIZE) {
            public boolean isSettable(GlyphIcon styleable) {
               return styleable.glyphSize == null || !styleable.glyphSize.isBound();
            }

            public StyleableProperty<Number> getStyleableProperty(GlyphIcon styleable) {
               return (StyleableProperty)styleable.glyphSizeProperty();
            }

            public Number getInitialValue(GlyphIcon styleable) {
               return GlyphIcon.DEFAULT_ICON_SIZE;
            }
         };
         List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList(Text.getClassCssMetaData());
         styleables.add(GLYPH_NAME);
         styleables.add(GLYPH_SIZE);
         STYLEABLES = Collections.unmodifiableList(styleables);
      }
   }
}
