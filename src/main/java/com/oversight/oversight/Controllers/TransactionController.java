package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.Transaction;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
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

    //Redirects the user to see his transactions
    @RequestMapping("/seeTransactions")
    public String homePage(Model model, HttpSession session){

        //Get all transactions from logged in user
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        List<Transaction> allTransactions = transactionService.findAllByUser(loggedIn);

        // Add data to the model
        model.addAttribute("transactions", allTransactions);

        model.addAttribute("pieChartData", transactionService.getPieChartData(loggedIn));
        try{
            SpendingPlan sp = (SpendingPlan)session.getAttribute("seespendingplan");
            model.addAttribute("lineChartData", transactionService.getLineChartDataPlan(loggedIn, sp));        }
        catch (Exception e){
            model.addAttribute("lineChartData", transactionService.getLineChartData(loggedIn));
        }
        return "seeTransactions";
    }

    //See transactions sorted by category
    @RequestMapping("/seeTransactions/Category")
    public String homePageCat(Model model, HttpSession session){

        //Get all transactions from logged in user
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        List<Transaction> allTransactions = transactionService.findAllByUserByCategory(loggedIn);

        // Add data to the model
        model.addAttribute("transactions", allTransactions);

        model.addAttribute("pieChartData", transactionService.getPieChartData(loggedIn));
        try{
            SpendingPlan sp = (SpendingPlan)session.getAttribute("seespendingplan");
            model.addAttribute("lineChartData", transactionService.getLineChartDataPlan(loggedIn, sp));
        }
        catch (Exception e){
            model.addAttribute("lineChartData", transactionService.getLineChartData(loggedIn));
        }

        return "seeTransactions";
    }

    //See transactions sorted by amount
    @RequestMapping("/seeTransactions/Amount")
    public String homePageAmt(Model model, HttpSession session){

        //Get all transactions from logged in user
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        List<Transaction> allTransactions = transactionService.findAllByUserByAmount(loggedIn);

        // Add data to the model
        model.addAttribute("transactions", allTransactions);

        model.addAttribute("pieChartData", transactionService.getPieChartData(loggedIn));
        try{
            SpendingPlan sp = (SpendingPlan)session.getAttribute("seespendingplan");
            model.addAttribute("lineChartData", transactionService.getLineChartDataPlan(loggedIn, sp));        }
        catch (Exception e){
            model.addAttribute("lineChartData", transactionService.getLineChartData(loggedIn));
        }
        return "seeTransactions";
    }

    //Redirects the user to new transaction page
    @RequestMapping(value="/addTransaction", method = RequestMethod.GET)
    public String addTransactionGET(Transaction transaction, User user){
        return "newTransaction";
    }

    @RequestMapping(value="/addTransaction", method = RequestMethod.POST)
    public String addTransactionPOST(Transaction transaction, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "newTransaction";
        }

        if(transaction.getDate() == null){
            return "selectDate";
        }

        if(transaction.getAmount() < 0){
            return "negativeAmount";
        }

        //Get logged in user, and connect to transactions
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        transaction.setUser(loggedIn);

        //Save the transaction in the database, and redirect to see transactions page
        transactionService.save(transaction);
        return "redirect:/seeTransactions";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteTransaction(@PathVariable("id") long id, Model model, HttpSession session){
        //Get the transaction from the database, and delete
        Transaction transactionToDelete = transactionService.findByID(id);
        transactionService.delete(transactionToDelete);

        //update model with the new transaction list, redirect to see transactions page
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("transactions", loggedIn.getTransactions());
        return "redirect:/seeTransactions";
    }


}
