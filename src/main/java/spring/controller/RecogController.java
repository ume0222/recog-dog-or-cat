package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.persistence.entity.History;
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
		Result result = recogService.recog(uploadFile, id, apikey);
        model.addAttribute("result", result);

        List<History> list = recogService.getHistoryList();
        model.addAttribute("histories", list);

        return "result";
	}
}
