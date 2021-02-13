package spring.persistence.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class RecogDAO {

	private final RestTemplate template;

	@Autowired
	public RecogDAO(RestTemplate template) {
		this.template = template;
	}

	/**
	 * APIを叩いて認識結果(JSON形式の文字列)を取得する
	 * [AIメーカーの犬猫判断モデル]
	 *   https://aimaker.io/app/image-classification/id/5962
	 *
	 * @param uploadFile ユーザが指定したファイル
	 * @param id AIメーカーのモデルのID
	 * @param apikey APIキー
	 * @return 認識結果(JSON形式の文字列)
	 */
	public String recog(MultipartFile uploadFile, String id, String apikey) {

		// ファイルをバイト列のリソースに変換
		// 参考
		// https://medium.com/@voziv/posting-a-byte-array-instead-of-a-file-using-spring-s-resttemplate-56268b45140b
		ByteArrayResource contentsAsResource = null;
		try {
			contentsAsResource = new ByteArrayResource(uploadFile.getBytes()) {
				@Override
				public String getFilename() {
					return uploadFile.getOriginalFilename(); // Filename has to be returned in order to be able to post.
				}
			};
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// リクエストURI
		String uri = "https://aimaker.io/image/classification/api";

		// ヘッダ
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.MULTIPART_FORM_DATA));

		// クエリパラメータを指定
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("id", id);
		params.add("apikey", apikey);
		params.add("file", contentsAsResource);

		// ボディとヘッダを指定してリクエスト生成
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(params, headers);

		// API呼び出し
		ResponseEntity<String> response = template.postForEntity(uri, entity, String.class);

		return response.getBody();
	}
}
