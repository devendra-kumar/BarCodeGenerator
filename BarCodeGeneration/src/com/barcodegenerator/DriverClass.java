package com.barcodegenerator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class DriverClass {

	public static void main(String[] args) throws IOException {
		String barCodeString = "CA0001345";
		Code128Bean codeBean = new Code128Bean();
		codeBean.setCodeset(Code128Constants.CODESET_ALL);
		final int dpi = 150;
		
		// configure the bar code generator
		// adjust bar code width
		codeBean.setModuleWidth(UnitConv.in2mm(5.0f / dpi));
		//codeBean.setHeight(55f);
		codeBean.setHeight(19f);
		codeBean.doQuietZone(false);

		File outPutFile = new File("E:/barcode.png");
		OutputStream out = new FileOutputStream(outPutFile);
		try {
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);
			codeBean.generateBarcode(canvas, barCodeString);
			
			System.out.println("finish barcode");
			canvas.finish();

		} catch (Exception ex) {
			System.out.println("Error : "+ex.toString());
		} finally {
			out.close();

		}

	}

}
