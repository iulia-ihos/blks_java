package cs.blokus.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs.blokus.dto.PerformanceDTO;
import cs.blokus.service.IPerformanceService;

@RestController
@RequestMapping("perf")
public class PerfomanceController {

	@Autowired
	private IPerformanceService performanceService;
	
	@GetMapping
	public List<PerformanceDTO> getTop() {
		return performanceService.getTopRankingUsers();	
	}
}
