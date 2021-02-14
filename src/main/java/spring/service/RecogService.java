package spring.service;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring.persistence.dao.RecogDAO;
import spring.persistence.entity.History;
import spring.persistence.entity.Label;
import spring.persistence.entity.Result;
import spring.persistence.mapper.RecogMapper;

@Service
public class RecogService {

	private final RecogDAO recogDAO;
	private final RecogMapper recogMapper;

	@Autowired
	public RecogService(RecogDAO recogDAO, RecogMapper recogMapper) {
		this.recogDAO = recogDAO;
		this.recogMapper = recogMapper;
	}

	/**
	 * DAOで認識結果を取得し、Resultクラスの形式に変換する
	 *
	 * @param uploadFile ユーザが指定したファイル
	 * @param id AIメーカーのモデルのID
	 * @param apikey APIキー
	 * @return 認識結果(Resultクラス形式)
	 */
	public Result recog(MultipartFile uploadFile, String id, String apikey) {
		String json = recogDAO.recog(uploadFile, id, apikey);

		// JSONをResultクラスに変換
		ObjectMapper mapper = new ObjectMapper();
		Result result = null;
		try {
			result = mapper.readValue(json, Result.class);
		} catch (JsonMappingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// 認識率をパーセントに変換
		// 小数点第二位で四捨五入
		for(Label label : result.getLabels()) {
			double recogPercent = label.getScore() * 100; // 認識率
			recogPercent = (recogPercent > 0) ? ((int)Math.round(recogPercent * 10)) / 10.0 : 0;
			label.setScore(recogPercent);
		}

		// 認識結果をデータベースに保存
		insertHistory(uploadFile, result);

		return result;
	}

	/**
	 * データベースに認識履歴を挿入する
	 * @param uploadFile ユーザが指定したファイル
	 * @param result 認識結果
	 */
	private void insertHistory(MultipartFile uploadFile, Result result) {
		List<Label> labels = result.getLabels();

		// 認識率を取得
		double resultCat, resultDog;
		if (labels.get(0).getLabel() == "猫") {
			resultCat = labels.get(0).getScore();
			resultDog = labels.get(1).getScore();
		} else {
			resultDog = labels.get(0).getScore();
			resultCat = labels.get(1).getScore();
		}

		// 今日の日付を取得
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String now = df.format(date);

		// 画像を縮小
		byte[] byteImg = compress(uploadFile);

		// Base64形式にする
		String base64Str = Base64.getEncoder().encodeToString(byteImg);
		String contentType = uploadFile.getContentType();
		StringBuilder sb = new StringBuilder();
		sb.append("data:");
		sb.append(contentType);
		sb.append(";base64,");
		sb.append(base64Str);

		// バイナリに変換
		byte[] base64Img = sb.toString().getBytes();

		// データベースに挿入
		History history = new History(now, base64Img, resultCat, resultDog);
		recogMapper.insertHistory(history);
	}

	/**
	 * 画像を圧縮する
	 * @param file 画像ファイル
	 * @return バイト列の縮小された画像
	 */
	public byte[] compress(MultipartFile file) {
		// 長辺の最大ピクセル数
		final int MAX_PIXEL_SIZE = 150;

		// BufferedImageを取得
		BufferedImage biSrc = null;
		try {
			biSrc = ImageIO.read(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 画像の幅と高さを取得
		int width = biSrc.getWidth();
		int height = biSrc.getHeight();

		//指定サイズに圧縮
		//縦横の縮小率を計算して，小さい方を縮小率とする
		//（縦横比を維持するため）
		double ratio = 1;
		if (width > height) { // 幅を圧縮
			ratio = (double) MAX_PIXEL_SIZE / (double) width;
		} else { // 高さを圧縮
			ratio = (double) MAX_PIXEL_SIZE / (double) height;
		}

		// 縮小された幅、高さ
		int shrinkedWidth = (int) (width * ratio);
		int shrinkedHeight = (int) (height * ratio);

		// 縮小画像を生成し、バイト列に変換
		BufferedImage biDst = new BufferedImage(shrinkedWidth, shrinkedHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = biDst.getGraphics();
		graphics.drawImage(biSrc.getScaledInstance(shrinkedWidth, shrinkedHeight, Image.SCALE_SMOOTH), 0, 0, null);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		try {
			ImageIO.write(biDst, "jpeg", bout);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bout.toByteArray();
	}

	/**
	 * 認識履歴を取得する
	 * @return 認識履歴
	 */
	public List<History> getHistoryList() {
		List<History> list = recogMapper.findAll();

		for (History history : list) {
			history.setImgStr(new String(history.getImg()));
		}
		return list;
	}
}
