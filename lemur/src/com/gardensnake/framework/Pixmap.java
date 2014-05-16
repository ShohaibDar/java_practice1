package com.gardensnake.framework;

import com.gardensnake.framework.Graphics.PixmapFormat;


public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
