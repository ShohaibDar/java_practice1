package com.gardensnake.framework.impl;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.gardensnake.framework.Graphics;
import com.gardensnake.framework.Pixmap;

public class AndroidGraphics implements Graphics {
	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	// paint: Shapes (shapes,lines,pixels) paint2: all text
	Paint paint, paint2, paint3;
	Typeface font;
	Rect srcRect = new Rect();
	Rect dstRect = new Rect();

	public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
		this.assets = assets;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
		this.paint2 = new Paint();
		this.paint3 = new Paint();
		font = Typeface.createFromAsset(this.assets, "smallfont.ttf");
	}

	public Pixmap newPixmap(String fileName, PixmapFormat format) {
		Config config = null;
		if (format == PixmapFormat.RGB565)
			config = Config.RGB_565;
		else if (format == PixmapFormat.ARGB4444)
			config = Config.ARGB_4444;
		else
			config = Config.ARGB_8888;

		Options options = new Options();
		options.inPreferredConfig = config;

		InputStream in = null;
		Bitmap bitmap = null;
		try {
			in = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(in);
			if (bitmap == null)
				throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}

		if (bitmap.getConfig() == Config.RGB_565)
			format = PixmapFormat.RGB565;
		else if (bitmap.getConfig() == Config.ARGB_4444)
			format = PixmapFormat.ARGB4444;
		else
			format = PixmapFormat.ARGB8888;

		return new AndroidPixmap(bitmap, format);
	}

	public void clear(int color) {
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
	}

	public void drawPixel(int x, int y, int color) {
		paint.setColor(color);
		paint.setStrokeWidth(1);
		canvas.drawPoint(x, y, paint);
	}

	public void drawText(String text, int x, int y, int align, int fontStyle, int textSize, int color) {
		if (fontStyle == 0) {
			paint2.setTypeface(Typeface.DEFAULT);

		} else if (fontStyle == 1) {
			paint2.setTypeface(Typeface.DEFAULT_BOLD);
		} else if (fontStyle == 2) {
			paint2.setTypeface(font);

		}
		if (align == 0) {
			paint2.setTextAlign(Paint.Align.LEFT);
		} else if (align == 1) {
			paint2.setTextAlign(Paint.Align.CENTER);
		} else {
			paint2.setTextAlign(Paint.Align.RIGHT);
		}
		paint2.setAntiAlias(true);
		paint2.setColor(color);
		paint2.setTextSize(textSize);
		canvas.drawText(text, x, y, paint2);
	}

	public void drawLine(int x, int y, int x2, int y2, int thickness, int color) {
		paint.setColor(color);
		paint.setStrokeWidth(thickness);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	public void drawTrajectory(float x, float y, float x2, float y2, int color) {
		paint3.setStrokeWidth(3);
		paint3.setColor(color);
		paint3.setPathEffect(new DashPathEffect(new float[] { 5, 10 }, 0));
		canvas.drawLine(x, y, x2, y2, paint3);
	}

	public void fillRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, x + width, y + height, paint);
	}

	public void traceRect(int x, int y, int width, int height, int strokeWidth, int color) {
		paint.setColor(color);
		paint.setStrokeWidth(strokeWidth);
		paint.setStyle(Style.STROKE);
		canvas.drawRect(x, y, x + width, y + height, paint);
	}

	public void drawBackground(boolean fullScreen, int color) {
		paint.setColor(color);
		if (fullScreen) {
			canvas.drawColor(color);
		} else {
			paint.setStyle(Style.FILL);
			canvas.drawRect(0, 31, 320, 480, paint);
		}
	}

	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right = srcX + srcWidth - 1;
		srcRect.bottom = srcY + srcHeight - 1;

		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x + srcWidth - 1;
		dstRect.bottom = y + srcHeight - 1;

		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, null);
	}

	public void drawPixmap(Pixmap pixmap, int x, int y) {
		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, x, y, null);
	}

	public int getWidth() {
		return frameBuffer.getWidth();
	}

	public int getHeight() {
		return frameBuffer.getHeight();
	}
}
