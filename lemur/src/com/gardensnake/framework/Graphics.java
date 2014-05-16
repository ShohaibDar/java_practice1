package com.gardensnake.framework;

public interface Graphics {
	public static enum PixmapFormat {
		ARGB8888, ARGB4444, RGB565
	}

	public Pixmap newPixmap(String fileName, PixmapFormat format);

	public void clear(int color);

	public void drawPixel(int x, int y, int color);

	/**
	 * @param text
	 * @param x
	 * @param y
	 * @param align
	 *            0:Left-Align, 1:Center-Align, 2:Right-Align
	 * @param fontStyle
	 *            0:normal, 1:Bold, 2:SmallFont
	 * @param textSize
	 * @param color
	 */
	public void drawText(String text, int x, int y, int align, int fontStyle, int textSize, int color);

	public void drawLine(int x, int y, int x2, int y2, int thickness, int color);
	
	public void drawTrajectory(float x, float y, float x2, float y2, int color);

	public void fillRect(int x, int y, int width, int height, int color);

	public void traceRect(int x, int y, int width, int height, int strokeWidth, int color);

	public void drawBackground(boolean fullScreen, int color);

	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);

	public void drawPixmap(Pixmap pixmap, int x, int y);

	public int getWidth();

	public int getHeight();
}
