package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.persistence.entity.Result;
import spring.service.RecogService;

@Controller
@PropertySource("classpath:aimaker.properties")
public class RecogController {

	private final RecogService recogService;

	@Autowired
	public RecogController(RecogService recogService) {
		this.recogService = recogService;
	}

	@PostMapping("/result")
	public String recog(@RequestParam MultipartFile uploadFile, @Value("${aimaker.id}") String id,
			@Value("${aimaker.apikey}") String apikey, Model model) {

		// uploadFileがnullの場合
		if(uploadFile == null) {
			return "error/error";
		}

		Result result = recogService.recog(uploadFile, id, apikey);
        model.addAttribute("result", result);

        return "result";
	}
}
