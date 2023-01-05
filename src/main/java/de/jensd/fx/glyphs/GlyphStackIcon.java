package de.jensd.fx.glyphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableDoubleProperty;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.scene.layout.StackPane;

public abstract class GlyphStackIcon<T extends Enum<T> & GlyphStackIcons<W>, V extends GlyphIcon, W extends Enum<W> & GlyphIcons> extends StackPane {
   public static final double DEFAULT_ICON_SIZE = 32.0;
   public static final String GLYPH_ICON_STACK = "glyph-icon-stack";
   private StringProperty glyphStyle;
   private ObjectProperty<String> glyphName;
   private DoubleProperty glyphSize;

   public GlyphStackIcon() {
      this((T) null, 32.0);
   }

   public GlyphStackIcon(T iconStack) {
      this(iconStack, 32.0);
   }

   public GlyphStackIcon(T iconStack, double size) {
      T iconStackFixed = iconStack;
      if (iconStack == null) {
         iconStackFixed = this.getDefaultGlyph();
      }

      this.getStyleClass().addAll("root", "glyph-icon-stack", iconStackFixed.name().toLowerCase());
      this.setMinSize(size, size);
      this.setPrefSize(size, size);
      this.setPrefSize(-1.0, -1.0);
      this.initBindings();
      this.initValues(iconStackFixed, size);
   }

   private void initBindings() {
      this.glyphSizeProperty().addListener((observable) -> {
         this.updateSize();
      });
      this.glyphStyleProperty().addListener((observable) -> {
         this.updateStyle();
      });
      this.glyphNameProperty().addListener((observable) -> {
         this.updateIcon();
      });
   }

   private void initValues(T icon, double size) {
      for(int i = 0; i < ((GlyphStackIcons)icon).getGlyphs().length; ++i) {
         W glyphIcons = (W) ((GlyphStackIcons)icon).getGlyphs()[i];
         V glyphIcon = this.getGlyph(glyphIcons, size);
         glyphIcon.getStyleClass().add("glyph-icon-stack-" + i);
         this.getChildren().add(glyphIcon);
      }

   }

   private void updateSize() {
      this.getChildren().stream().filter((node) -> {
         return node instanceof GlyphIcon;
      }).forEach((node) -> {
         ((GlyphIcon)node).setGlyphSize(this.getGlyphSize());
      });
      this.setMinSize(this.getGlyphSize(), this.getGlyphSize());
   }

   private void updateStyle() {
      this.setStyle(this.getGlyphStyle());
   }

   private void updateIcon() {
      this.getChildren().stream().filter((node) -> {
         return node instanceof GlyphIcon;
      }).forEach((node) -> {
         ((GlyphIcon)node).updateIcon();
      });
   }

   public abstract T getDefaultGlyph();

   protected abstract V getGlyph(W var1, double var2);

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

   public final void setGlyph(T glyph) {
      this.setGlyphName(glyph.name());
   }

   public final DoubleProperty glyphSizeProperty() {
      if (this.glyphSize == null) {
         this.glyphSize = new SimpleStyleableDoubleProperty(StyleableProperties.GLYPH_SIZE, this, "glyphSize");
         this.glyphSize.setValue((Number)32.0);
      }

      return this.glyphSize;
   }

   public final Double getGlyphSize() {
      return this.glyphSizeProperty().getValue();
   }

   public final void setGlyphSize(Double size) {
      Number sizeFixed = size == null ? 32.0 : size;
      this.glyphSizeProperty().setValue((Number)sizeFixed);
   }

   public StringProperty glyphStyleProperty() {
      if (this.glyphStyle == null) {
         this.glyphStyle = new SimpleStringProperty("");
      }

      return this.glyphStyle;
   }

   public String getGlyphStyle() {
      return this.glyphStyleProperty().getValue();
   }

   public void setGlyphStyle(String style) {
      this.glyphStyleProperty().setValue(style);
   }

   public GlyphStackIcon setStyleClass(String styleClass) {
      this.getStyleClass().add(styleClass);
      return this;
   }

   public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
      return StyleableProperties.STYLEABLES;
   }

   public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
      return getClassCssMetaData();
   }

   private static class StyleableProperties {
      private static final CssMetaData<GlyphStackIcon, String> GLYPH_NAME = new CssMetaData<GlyphStackIcon, String>("-glyph-name", StyleConverter.getStringConverter(), "BLANK") {
         public boolean isSettable(GlyphStackIcon styleable) {
            return styleable.glyphName == null || !styleable.glyphName.isBound();
         }

         public StyleableProperty<String> getStyleableProperty(GlyphStackIcon styleable) {
            return (StyleableProperty)styleable.glyphNameProperty();
         }

         public String getInitialValue(GlyphStackIcon styleable) {
            return "BLANK";
         }
      };
      private static final CssMetaData<GlyphStackIcon, Number> GLYPH_SIZE = new CssMetaData<GlyphStackIcon, Number>("-glyph-size", StyleConverter.getSizeConverter(), 32.0) {
         public boolean isSettable(GlyphStackIcon styleable) {
            return styleable.glyphSize == null || !styleable.glyphSize.isBound();
         }

         public StyleableProperty<Number> getStyleableProperty(GlyphStackIcon styleable) {
            return (StyleableProperty)styleable.glyphSizeProperty();
         }

         public Number getInitialValue(GlyphStackIcon styleable) {
            return 32.0;
         }
      };
      private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

      StyleableProperties() {
      }

      static {
         List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList(StackPane.getClassCssMetaData());
         styleables.add(GLYPH_NAME);
         styleables.add(GLYPH_SIZE);
         STYLEABLES = Collections.unmodifiableList(styleables);
      }
   }
}
