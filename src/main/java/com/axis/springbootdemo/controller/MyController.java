package com.axis.springbootdemo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axis.springbootdemo.entity.Cricketer;

@RestController
public class MyController {
	
	private static ArrayList<Cricketer> crickList;
	static {
		crickList=new ArrayList<>();
		crickList.add(new Cricketer(1001,"Virat Kohli",111,51,8,6,217.5));
		crickList.add(new Cricketer(1002,"Suryakumar Yadav",105,53,4,6,216.5));
		crickList.add(new Cricketer(1003,"MS Dhoni",90,51,8,6,210));
		crickList.add(new Cricketer(1004,"Ravindra Jadeja",86,51,8,6,200));
		crickList.add(new Cricketer(1005,"Rohit Sharma",60,51,8,6,216));
	}
	
	@GetMapping("/message")
	public String getMessage() {
		return "Hello...First SpringBoot Project \nGood Afternoon.";
	}
	@GetMapping("/welcome")
	public String getWelcome() {
		return "Welcome..Happy to start Spring boot.";
	}
	@GetMapping("/cricketer")
	public Cricketer getCricketer() {
		return new Cricketer(1001,"Virat Kohli",111,51,8,6,217.5);
	}
	//Get all cricketers
	@GetMapping("/cricketers")
	public ArrayList<Cricketer>  getCricketers() {
		return crickList;
	}
	//Get cricketer by Id
	@GetMapping("/cricketer/{cricketerId}")
	public Cricketer getCricketerById(@PathVariable int cricketerId) {
		for(Cricketer ok:crickList) {
			if(ok.getCricketerId()==cricketerId) {
				return ok;  //return Cricketer if Id is found
			}
		}
		return null;    //retun null if Cricketer id not found
	}
	
	@PostMapping("/cricketer")
	public ResponseEntity<String> addCricketer(@RequestBody Cricketer cricketer) {
		crickList.add(cricketer);
		return new ResponseEntity<String>("Cricketer Added Successfully...",HttpStatus.OK);
	}
	
	//UPDATE REQUEST
		@PutMapping("/cricketer/update/{id}")
		public ResponseEntity<String> updateCricketer(@PathVariable int id,@RequestBody Cricketer updatedCricketer){
			if(id!=updatedCricketer.getCricketerId())
				return new ResponseEntity<String>("Cricketer ids does not match",HttpStatus.BAD_REQUEST);
			int index=crickList.indexOf(updatedCricketer);
			if(index == -1) {
				return new ResponseEntity<String>("Cricketer with id : "+id+" not found",HttpStatus.NOT_FOUND);
			}
			else {
				crickList.get(index).setBalls(updatedCricketer.getBalls());
				crickList.get(index).setFours(updatedCricketer.getFours());
				crickList.get(index).setRunsScored(updatedCricketer.getRunsScored());
				crickList.get(index).setSixes(updatedCricketer.getSixes());
				crickList.get(index).setStrikeRate(updatedCricketer.getStrikeRate());
				return new ResponseEntity<String>("Cricketer data updated successfully ",HttpStatus.OK);
			}
		}
		//Delete Mapping
		@DeleteMapping("/cricketer/delete/{cricketerId}")
		public ResponseEntity<String>deleteCricketer(@PathVariable int cricketerId){
		Cricketer cricketer = getCricketerById(cricketerId);
		if(cricketer==null) {
			return new ResponseEntity<String>("Crickter with id:"+cricketerId+" is nit found",HttpStatus.NOT_FOUND);
		}else {
			crickList.remove(cricketer);
			return new ResponseEntity<String>("Cricketer with id:"+cricketerId+" deleted successfully",HttpStatus.OK);
		}
		
		}
	
	
	
	
}
