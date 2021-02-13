package spring.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

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

		// 認識結果をデータベースに保存
		insertHistory(uploadFile, result);

		return result;
	}

	private void insertHistory(MultipartFile uploadFile, Result result) {
		List<Label> labels = result.getLabels();
		double resultCat, resultDog;
		if(labels.get(0).getLabel() == "猫") {
			resultCat = labels.get(0).getScore() * 100;
			resultDog = labels.get(1).getScore() * 100;
		} else {
			resultDog = labels.get(0).getScore() * 100;
			resultCat = labels.get(1).getScore() * 100;
		}

		// 今日の日付
		LocalDateTime today = LocalDateTime.now();


		// 画像をBase64用にエンコード
		String base64str = null;
		try {
			base64str = Base64.getEncoder().encodeToString(uploadFile.getBytes());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// コンテンツタイプの取得
		String contentType = uploadFile.getContentType();

		// Base64形式にする
		StringBuilder sb = new StringBuilder();
        sb.append("data:");
        sb.append(contentType);
        sb.append(";base64,");
        sb.append(base64str);

        // 画像をBase64のバイナリ形式に変換
        byte[] img = sb.toString().getBytes();

		History history = new History(today, img, resultCat, resultDog);
		recogMapper.insertHistory(history);
	}

	public List<History> getHistoryList(){
		List<History> list = recogMapper.findAll();

		for(History history : list) {
			history.setImgStr(new String(history.getImg()));
		}
		return list;
	}
}
