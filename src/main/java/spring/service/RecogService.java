package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring.persistence.dao.RecogDAO;
import spring.persistence.entity.Result;

@Service
public class RecogService {

	private final RecogDAO recogDAO;

	@Autowired
	public RecogService(RecogDAO recogDAO) {
		this.recogDAO = recogDAO;
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

		return result;
	}
}
