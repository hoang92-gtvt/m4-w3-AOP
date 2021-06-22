package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ICustomerService;

import java.util.Optional;

@Controller
@RequestMapping("Customers/")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("Create")
    public ModelAndView createForm(){
        ModelAndView m = new ModelAndView("create");
        m.addObject("customer", new Customer());
        return m;
    }
    @PostMapping("Create")
    public ModelAndView create(Customer customer){
        ModelAndView modelAndView = new ModelAndView("create");
        customerService.save(customer);
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("mesage","Thêm mới thành công");
        return modelAndView;
    }

    @GetMapping("Show")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("customer");
        Iterable<Customer> customers = customerService.findAll();
        modelAndView.addObject("customer",customers);
        return modelAndView;
    }

    @GetMapping("Remove")
    public String remove(Long id){
        customerService.delete(id);
        return "redirect:Show";
    }

    @GetMapping("Update")
    public ModelAndView updateForm(Long id){
        ModelAndView modelAndView = new ModelAndView("update");
        Optional<Customer> customer = null;
        try {
            customer = customerService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("customer1",new Customer());
        return modelAndView;
    }

    @PostMapping("Update")
    public String update(Customer customer){
        customerService.save(customer);
        return "redirect:Show";
    }

    @GetMapping("{id}/Find")
    public ModelAndView findById(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("find");
        try {
            Optional<Customer> customer = customerService.findOne(id);
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("404orr");

        }
    }

}
