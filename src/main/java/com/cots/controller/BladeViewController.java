package com.cots.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cots.dao.BladeViewService;
import com.cots.model.BladeView;
import com.cots.model.PerformReboot;
import com.cots.model.PostReq;

@RestController
public class BladeViewController {
	
//	@Autowired
//	ObjectFactory<BladeViewService> bladeViewService;
	
//	@Autowired
//	@Qualifier(value = "bladeViewService")
//	private BladeViewService bladeViewService;
	
	@Autowired
	@Qualifier(value = "bladeViewService")
	private BladeViewService bladeViewService1;
	
//	@Autowired
//	 private BaldeViewRepository baldeViewRepository;
	
	@RequestMapping(value="/add",method=RequestMethod.PUT,consumes = "application/json", produces = "application/json")
	public void add(@RequestBody BladeView bladeView) {
         System.out.println("......adding blade to bladeviewservice...........for blade id :: : ::"+bladeView.getBladeId() +bladeView);
         bladeViewService1.add(bladeView);
	}
	
	@RequestMapping(value="/getallblade",method=RequestMethod.GET, produces = "application/json")
	public List<BladeView> getAllBlade() {
		try {
			Thread.sleep(2000);
		}catch(Exception e) {
			System.out.println("###########"+e);
		}
		System.out.println("no: of blades:.........."+bladeViewService1.getAllbladeDetails().size());
		List<BladeView> list=new ArrayList<BladeView>();
		list.addAll(bladeViewService1.getAllbladeDetails());
		Collections.sort(list,new Comparator<BladeView>() {
		    @Override
		    public int compare(BladeView b1, BladeView b2) {
		        return new Integer(b1.getBladeId()).compareTo(b2.getBladeId());
		    }
		});
		return list;
	}
	
	@RequestMapping(value="/getbladebyid/{bladeId}",method=RequestMethod.GET)
	public BladeView findBladeViewByBladeId(@PathVariable int bladeId) {
		return bladeViewService1.getBladeById(bladeId);
	}
	
	@RequestMapping(value="/deletebladeview/{bladeId}",method=RequestMethod.DELETE)
	
	public void deleteEmployeeById(@PathVariable("bladeId") int bladeId) {
		bladeViewService1.deleteBladeById(bladeId);
	}
	
	
	
	@RequestMapping(value="/rebootblade",method=RequestMethod.PUT,consumes = "application/json", produces = "application/json")
	public void performReboot(@RequestBody PerformReboot performReboot) {
         System.out.println("......rebooting blade to performRebootservice...........for blade id :: : ::"+performReboot.getBladeId() +performReboot);
        
	}
	
	@RequestMapping(value="/postreq",method=RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public Object postReq(@RequestBody PostReq postreq) {
         System.out.println("......@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ :: : ::"+postreq.getName());
         if(postreq.getName().equals("Rajesh")) {
        	 postreq.setName("Rajesh Kumar...");
         }
         
         System.out.println("!!!!!!!!!!"+bladeViewService1);
         System.out.println("!!!!!!!!!!"+bladeViewService1);
          return postreq;
	}


}
