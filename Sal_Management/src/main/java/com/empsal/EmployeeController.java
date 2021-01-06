package com.empsal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/home/")
public class EmployeeController {
	private final EmployeeRepositiry employeeRepositiry;
	private final BankaccRepository bankaccRepository;
	
	@Autowired
	public EmployeeController(EmployeeRepositiry employeeRepositiry, BankaccRepository bankaccRepository) {
		super();
		this.employeeRepositiry = employeeRepositiry;
		this.bankaccRepository = bankaccRepository;
	}
	@GetMapping
	private String AllEmployees(Model model) {
		model.addAttribute("employees", employeeRepositiry.findAll());
		return "employeelist";
		
	}
	@GetMapping("bank")
	private String AllBankacc(Model model) {
		model.addAttribute("bankacc",bankaccRepository.findAll());
		return "accountlist";
	}
	@GetMapping("paysalary")
	public String Givesalary(Model model) {
		model.addAttribute("employees", employeeRepositiry.findAll());
		return "paysalary";
	}
	
	
	@PostMapping("updatebank/{id}")
	public String updateBankacc(@PathVariable("id") Long id, @Valid Bankacc bankacc, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			bankacc = bankaccRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("Invalid Bankacc Id:" + id));
			return "updatebank";
		}
		
		
		bankaccRepository.save(bankacc);
		model.addAttribute("bankacc", bankaccRepository.findAll());
		return "redirect:/home/bank";
	}
	
	@GetMapping("editbank/{id}")
	public String ShowUpdatebankacc(@PathVariable("id") Long id, Model model) {
		Bankacc bankacc = bankaccRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Bankacc ID : " + id));
		bankacc.setCurrentbalance(0);
		model.addAttribute("bankacc", bankacc);
		return "updatebank";
	}
	@GetMapping("addacc")
	public String ShowallSaveBankacc(Bankacc bankacc) {
		return "saveaccounts";
	}
	@PostMapping("addacc")
	public String SaveBankacc(@Valid Bankacc bankacc, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "saveaccounts";
		}
		bankaccRepository.save(bankacc);
		model.addAttribute("bankacc", bankaccRepository.findAll());
		return "redirect:/home/bank";
	}
	@GetMapping("add")
	public String ShowallSaveEmployee(Employee employee) {
		return "saveemployee";
	}
	@PostMapping("add")
	public String SaveEmployees(@Valid Employee employee, @Valid Bankacc bankacc , BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "saveemployee";
		}
		if (employee.getEmprank().endsWith("Five")) {
			employee.setEmpsalary(employee.getEmpsalary()+5000);
		}else if (employee.getEmprank().endsWith("Four")) {
			employee.setEmpsalary(employee.getEmpsalary()+10000);
		}else if (employee.getEmprank().endsWith("Three")) {
			employee.setEmpsalary(employee.getEmpsalary()+15000);
		}else if (employee.getEmprank().endsWith("Two")) {
			employee.setEmpsalary(employee.getEmpsalary()+20000);
		}else if (employee.getEmprank().endsWith("One")) {
			employee.setEmpsalary(employee.getEmpsalary()+25000);
		}else {
			employee.setEmpsalary(employee.getEmpsalary());
		}
		employee.setEmpsalary(employee.getEmpsalary()+employee.getEmpsalary()*0.20+employee.getEmpsalary()*0.15);
		//employee.setEmpsalary(bankacc.getCurrentbalance());
		bankacc.setAccountname(employee.getEmpname());
		bankacc.setBankaccno(employee.getEmpbankaccno());
		bankacc.setCurrentbalance(employee.getEmpsalary());
		bankacc.setAccounttype("Savings");		
		employeeRepositiry.save(employee);	
		bankaccRepository.save(bankacc);	
		model.addAttribute("employees", employeeRepositiry.findAll());	
		model.addAttribute("bankacc", bankaccRepository.findAll());		
		return "redirect:/home/";
	}
	@GetMapping("edit/{id}")
	public String ShowUpdateEmployees(@PathVariable("id") Long id, Model model) {
		Employee employee = employeeRepositiry.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Employee ID : " + id));
		model.addAttribute("employee", employee);
		return "updateemployee";
	}
	
	@PostMapping("update/{id}")
	public String updateEmployees(@PathVariable("id") Long id, @Valid Employee employee, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			employee = employeeRepositiry.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("Invalid Employee Id:" + id));
			return "updateemployee";
		}
		employeeRepositiry.save(employee);
		model.addAttribute("employees", employeeRepositiry.findAll());
		return "redirect:/home/";
	}
	
	@GetMapping("delete/{id}")
	public String deleteEmployees(@PathVariable("id") Long id, Model model) {
		Employee employee = employeeRepositiry.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Employee Id:" + id));
		employeeRepositiry.delete(employee);
		model.addAttribute("employees", employeeRepositiry.findAll());
		return "redirect:/home/";
	}
	@GetMapping("pay/{id}")
	public String PayEmployees(@PathVariable("id") Long id, Model model, @Valid Employee employee,@Valid Bankacc bankacc,BindingResult result) {
			if (result.hasErrors()) {	
			bankacc = bankaccRepository.findById((long) 18)
					.orElseThrow(() -> new IllegalArgumentException("Invalid Bankacc ID : " + id));
			return "updatebank";
			}
			double amount = bankacc.getCurrentbalance();			
			double empsal= employee.getEmpsalary();			
			double remaining = amount - empsal;			
			bankacc.setCurrentbalance(remaining);
			
			bankaccRepository.save(bankacc);
			model.addAttribute("bankacc", bankaccRepository.findAll());
		return "redirect:/home/bank";
		}
		
	}


