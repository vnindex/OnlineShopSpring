package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dao.FavoriteDao;
import com.example.demo.Dao.OrderDao;
import com.example.demo.Model.OrderModel;
import com.example.demo.Model.StatisFavorite;
import com.example.demo.Model.StatisOrder;
import com.example.demo.Service.SessionService;

@Controller
public class StatisController {
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	FavoriteDao favoriteDao;
	
	@Autowired 
	OrderDao orderDao;
	
	@GetMapping("/admin/statis/favorite")
	public String favorite(Model model, @RequestParam("keyword13") Optional<String> name, @RequestParam("p") Optional<Integer> p) {
		String findName;
		if(sessionService.get("keyword13") == null) {
			findName = name.orElse("");
		}
		else {
			findName = name.orElse(sessionService.get("keyword13"));
		}		
		
		sessionService.set("keyword13", findName);
		
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		
		Page<StatisFavorite> list = favoriteDao.fillToTable("%" + findName + "%", pageable);
		model.addAttribute("statisFavorite", list);
		model.addAttribute("location", 0);
		return "manager/statisFavorite";
	}
	
	@GetMapping("/admin/statis/order")
	public String order(Model model, @RequestParam("keyword14") Optional<String> name, @RequestParam("p") Optional<Integer> p) {
		String findName;
		if(sessionService.get("keyword13") == null) {
			findName = name.orElse("");
		}
		else {
			findName = name.orElse(sessionService.get("keyword13"));
		}		
		
		sessionService.set("keyword13", findName);
		
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		
		Page<StatisOrder> list = orderDao.fillToTable("%" + findName + "%", pageable);
		model.addAttribute("statisOrder", list);
		model.addAttribute("location", 0);
		return "manager/statisOrder";
	}
}
