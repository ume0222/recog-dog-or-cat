package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.persistence.entity.History;
import spring.service.RecogService;

@Controller
public class HistoryController {

	private final RecogService recogService;

	@Autowired
	public HistoryController(RecogService recogService) {
		this.recogService = recogService;
	}

	@GetMapping("/history")
	public String history(Model model) {
		List<History> list = recogService.getHistoryList();
        model.addAttribute("histories", list);

        return "history";
	}
}
