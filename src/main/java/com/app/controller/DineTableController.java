package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddDineTableToRestaurantDto;
import com.app.dto.DineTableDto;
import com.app.services.DineTableService;

@RestController
@RequestMapping("/restaurants/{restaurantId}/dine-tables")
public class DineTableController {
	
	@Autowired
	private DineTableService dineTableService;

	@GetMapping("/getAllTables")
	public ResponseEntity<?> getAllTablesController(@PathVariable Long restaurantId){
		System.out.println("In getAllTablesController: "+getClass()+" "+restaurantId);
		return ResponseEntity.status(HttpStatus.FOUND).body(dineTableService.getAllTables(restaurantId));
	}
	
	@GetMapping("/getTableById/{tableid}")
	public ResponseEntity<?> findTableByIdController(@PathVariable Long tableid,@PathVariable Long restaurantId){
		System.out.println("In findTableByIdController: "+getClass());
		return ResponseEntity.status(HttpStatus.FOUND).body(dineTableService.findTableById(tableid, restaurantId));
	}

	@PostMapping("/addNewTable")
	public ResponseEntity<?> addNewTableController(@RequestBody AddDineTableToRestaurantDto table,@PathVariable Long restaurantId) {
		System.out.println("In getAllTablesController add table: "+getClass());
		return ResponseEntity.ok(dineTableService.addNewTable(table, restaurantId));
	}

	@DeleteMapping("/deleteTable/{tableId}")
	public ResponseEntity<?> deleteTableController(@PathVariable Long restaurantId, @PathVariable Long tableId) {
		System.out.println("In getAllTablesController: "+getClass());
		return ResponseEntity.ok(dineTableService.deleteTable(tableId, restaurantId));
	}

	@PutMapping("/updateTable/{tableId}")
	public ResponseEntity<?> updateTableController(@RequestBody DineTableDto table,@PathVariable Long restaurantId,@PathVariable Long tableId) {
		System.out.println("In getAllTablesController: "+getClass());
		return ResponseEntity.ok(dineTableService.updateTable(table, restaurantId,tableId));
	}

}
