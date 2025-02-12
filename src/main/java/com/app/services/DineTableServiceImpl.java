package com.app.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.DineTableDao;
import com.app.dao.RestaurantDao;
import com.app.dto.AddDineTableToRestaurantDto;
import com.app.dto.DineTableDto;
import com.app.entities.DineTable;
import com.app.entities.Restaurant;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DineTableServiceImpl implements DineTableService {

	@Autowired
	private DineTableDao dineTableDao;

	@Autowired
	private RestaurantDao restaurantDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<DineTableDto> getAllTables(Long restaurantId) {
		// TODO Auto-generated method stub
		System.out.println("In getAllTables: " + getClass());
		try {
			if (restaurantDao.existsById(restaurantId)) {
				Restaurant restaurant = restaurantDao.findById(restaurantId)
						.orElseThrow(() -> new ResourceNotFoundException("Invalid Restaurant Id"));
				List<DineTable> dineTableList = restaurant.getDineTables();
				
				System.out.println(dineTableList + " dineTableList");
				
				List<DineTableDto> dineTableDtoList = new ArrayList<>();
				for (DineTable dt : dineTableList) {
					dineTableDtoList.add(modelMapper.map(dt, DineTableDto.class));
				}
				System.out.println(dineTableDtoList + " Dine Tables List");
				return dineTableDtoList;
			}

		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Invalid Restaurant Id");
		}
		throw new ResourceNotFoundException("Invalid Restaurant Id");
	}

	@Override
	public String addNewTable(AddDineTableToRestaurantDto table, Long restaurantId) {
		// TODO Auto-generated method stub
		System.out.println("In addNewTable: " + getClass());
		try {
			if (restaurantDao.existsById(restaurantId)) {
				restaurantDao.findById(restaurantId)
						.orElseThrow(() -> new ResourceNotFoundException("Invalid Restaurant Id")).addTables(
								restaurantDao.findById(restaurantId)
										.orElseThrow(() -> new ResourceNotFoundException("Invalid Restaurant Id")),
								modelMapper.map(table, DineTable.class));

				return "Table added successfully to the restaurant";
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Invalid Restaurant Id");
		}
		throw new ResourceNotFoundException("Invalid Restaurant Id");
	}

	@Override
	public String deleteTable(Long tableId, Long restaurantId) {
		// TODO Auto-generated method stub
		System.out.println("In deleteTable: " + getClass() + " " + tableId + " " + restaurantId);
		try {
			if (dineTableDao.existsById(tableId)) {
				restaurantDao.findById(restaurantId)
						.orElseThrow(() -> new ResourceNotFoundException("Invalid Restaurant Id")).deleteTables(
								restaurantDao.findById(restaurantId)
										.orElseThrow(() -> new ResourceNotFoundException("Invalid Restaurant Id")),
								dineTableDao.findById(tableId)
										.orElseThrow(() -> new ResourceNotFoundException("Invalid Table Id")));
				dineTableDao.deleteById(tableId);
				return "Table deleted successfully!!";
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Invalid Table Id");
		}
		throw new ResourceNotFoundException("Invalid Table Id");
	}

	@Override
	public String updateTable(DineTableDto table,Long restaurantId ,Long tableId) {
		// TODO Auto-generated method stub
		System.out.println("In updateTable: " + getClass()+" "+tableId+" "+restaurantId);
		try {
			if(restaurantDao.existsById(restaurantId)) {
				if (dineTableDao.existsById(tableId)) {
//					DineTable dineTable = dineTableDao.findById(tableId).orElseThrow(()->new ResourceNotFoundException("Invalid Table Id"));
					DineTable dineTable=modelMapper.map(table, DineTable.class);
					dineTable.setId(tableId);
					dineTableDao.save(dineTable);
					return "Table updated successfully!";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Invalid Table Id");

		}
		throw new ResourceNotFoundException("Invalid Table Id");
	}

	@Override
	public DineTableDto findTableById(Long tableId, Long restaurantId) {
		// TODO Auto-generated method stub
		System.out.println("In findTableById: " + getClass());
		try {
			if (restaurantDao.existsById(restaurantId)) {
				if (dineTableDao.existsById(tableId)) {
					return modelMapper.map(dineTableDao.findById(tableId), DineTableDto.class);
				}
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Invalid Table Id/ Restaurant Id");
		}
		throw new ResourceNotFoundException("Invalid Table Id/ Restaurant Id");
	}

}
