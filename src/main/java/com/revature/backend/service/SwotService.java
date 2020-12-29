package com.revature.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.backend.model.AnalysisItem;
import com.revature.backend.model.Swot;
import com.revature.backend.repository.AnalysisItemRepository;
import com.revature.backend.repository.SwotRepository;

@Service("swotService")
public class SwotService {

	@Autowired
	SwotRepository swotRepository;
	
	@Autowired
	AnalysisItemRepository analysisItemRepository;
	
	//create -swot
	public boolean createNewSwot(Swot swot) {
		Swot parent = new Swot(swot.getAssociate(), swot.getManager(), swot.getCreatedOn(), swot.getLastModified()); // jackson creates swot object
		List<AnalysisItem> items = swot.getAnalysisItems(); // we fetch all items from postman input
		for (AnalysisItem item : items) { // we add all items to parent object created by jackson
			item.setSwot(parent);		
		}
		parent.setAnalysisItems(items); // we add parent object to all items(this time items have parent object inside)
		return swotRepository.save(parent) != null; // we create parent object in db
	}

	//get all by associate id -swot
	public List<Swot> retrieveAllSwotByAssociateId(int associateId) {
		return swotRepository.findAllByAssociateId(associateId);
	}
	
	//create -item
	public boolean createNewItem(AnalysisItem analysisItem) {
		return analysisItemRepository.save(analysisItem) != null;
	}

	//update -item
	public AnalysisItem updateItem(AnalysisItem analysisItem) {
		return analysisItemRepository.save(analysisItem);
	}

	//delete -item
	public boolean deleteItem(AnalysisItem analysisItem) {
		analysisItemRepository.delete(analysisItem);
		return true; //TODO: this will always return true, fix the condition.
	}
	
	//delete -item
	public boolean deleteItem(int analysisItemId) {
		analysisItemRepository.deleteById(analysisItemId);
		return true; //TODO: this will always return true, fix the condition.
	}
	
	public List<Swot> retrieveAllSwot() {
		return swotRepository.findAll();
	}
	
}
