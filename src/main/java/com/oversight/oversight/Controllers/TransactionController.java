package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.Transaction;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @RequestMapping("/seeTransactions")
    public String homePage(Model model, HttpSession session){

        //Get all transactions from logged in user
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        List<Transaction> allTransactions = transactionService.findAllByUser(loggedIn);

        // Add data to the model
        model.addAttribute("transactions", allTransactions);
        return "seeTransactions";
    }

    @RequestMapping(value="/addTransaction", method = RequestMethod.GET)
    public String addTransactionGET(Transaction transaction){
        return "newTransaction";
    }

    @RequestMapping(value="/addTransaction", method = RequestMethod.POST)
    public String addTransactionPOST(Transaction transaction, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "newTransaction";
        }

        User loggedIn = (User) session.getAttribute("LoggedInUser");
        transaction.setUser(loggedIn);
        transactionService.save(transaction);
        return "redirect:/seeTransactions";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteTransaction(@PathVariable("id") long id, Model model, HttpSession session){
        Transaction transactionToDelete = transactionService.findByID(id);
        transactionService.delete(transactionToDelete);
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("transactions", loggedIn.getTransactions());
        return "redirect:/seeTransactions";
    }

}
