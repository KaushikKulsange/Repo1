package com.app.services;

import java.util.List;

import com.app.dto.AddDineTableToRestaurantDto;
import com.app.dto.DineTableDto;

public interface DineTableService {

	List<DineTableDto> getAllTables(Long restaurantId);

	String addNewTable(AddDineTableToRestaurantDto table, Long restaurantId);

	String deleteTable(Long tableId, Long restaurantId);

	String updateTable(DineTableDto table,Long restaurantId ,Long tableId);
	
	DineTableDto findTableById(Long tableId, Long restaurantId);
}
