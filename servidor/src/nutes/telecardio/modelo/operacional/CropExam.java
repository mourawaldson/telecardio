package nutes.telecardio.modelo.operacional;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import nutes.telecardio.modelo.configuracao.NomesCampos;

public class CropExam {
	private static String formatName = NomesCampos.jpegFormat;
	private static int diference = 10;
	private static int newImage_W = 2150;
	private static int newImage_H1 = 474;
	private static int newImage_H2 = 473;
	private static int newImage_X = 177;
	private static int dynamic_H = newImage_H1 + diference;

	private static int dIdIIdIII_y = 413;
	private static String dIdIIdIII_Name = "DI_DII_DIII.jpg";

	private static int aVFaVFaVL_Y = dynamic_H + dIdIIdIII_y;
	private static String aVRaVFaVL_Name = "AVR_AVF_AVL.jpg";

	private static int v1v2v3_Y = dynamic_H + aVFaVFaVL_Y;
	private static String v1v2v3_Name = "V1_V2_V3.jpg";

	private static int v4v5v6_Y = dynamic_H + v1v2v3_Y + 1;
	private static String v4v5v6_Name = "V4_V5_V6.jpg";

	private static int dII_Y = dynamic_H + v4v5v6_Y;
	private static String dII_Name = "DII.jpg";

	private static String geral_Name = "GERAL.jpg";
	private static int geral_H = ((newImage_H1 * 2) + (newImage_H2 * 3)) + 40;

	public static void Crop(String path, String image) throws IOException {
		BufferedImage bufferedImage = null;
		BufferedImage bufferedNewImage = null;
		String fullPath = path + File.separator;
		File file = new File(fullPath + image);
		File newFile = null;
		bufferedImage = ImageIO.read(file);

		bufferedNewImage = bufferedImage.getSubimage(newImage_X, dIdIIdIII_y,
				newImage_W, newImage_H2);
		newFile = new File(fullPath + dIdIIdIII_Name);
		ImageIO.write(bufferedNewImage, formatName, newFile);

		bufferedNewImage = bufferedImage.getSubimage(newImage_X, aVFaVFaVL_Y,
				newImage_W, newImage_H1);
		newFile = new File(fullPath + aVRaVFaVL_Name);
		ImageIO.write(bufferedNewImage, formatName, newFile);

		bufferedNewImage = bufferedImage.getSubimage(newImage_X, v1v2v3_Y,
				newImage_W, newImage_H1);
		newFile = new File(fullPath + v1v2v3_Name);
		ImageIO.write(bufferedNewImage, formatName, newFile);

		bufferedNewImage = bufferedImage.getSubimage(newImage_X, v4v5v6_Y,
				newImage_W, newImage_H2);
		newFile = new File(fullPath + v4v5v6_Name);
		ImageIO.write(bufferedNewImage, formatName, newFile);

		bufferedNewImage = bufferedImage.getSubimage(newImage_X, dII_Y,
				newImage_W, newImage_H2);
		newFile = new File(fullPath + dII_Name);
		ImageIO.write(bufferedNewImage, formatName, newFile);

		bufferedNewImage = bufferedImage.getSubimage(newImage_X,
				dIdIIdIII_y - 60, newImage_W, geral_H);
		newFile = new File(fullPath + geral_Name);
		ImageIO.write(bufferedNewImage, formatName, newFile);
	}
}