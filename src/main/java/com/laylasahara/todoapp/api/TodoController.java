package com.laylasahara.todoapp.api;

import com.laylasahara.todoapp.model.Category;
import com.laylasahara.todoapp.model.Todo;
import com.laylasahara.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {

    TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Todo> allTodos = todoService.getAllTodos();
        model.addAttribute("todos", allTodos);
        return "index";
    }

    @GetMapping("todo/add")
    public String createTodo(Model model) {
        Todo todo = new Todo();
        model.addAttribute("todo", todo);
        return "create_todo";
    }

    @PostMapping("todo/add")
    public String addTodo(@ModelAttribute("todo") Todo todo) {
        todoService.addTodo(todo);
        return "redirect:/";
    }

    @PostMapping("todo/update/{id}")
    public String updateTodo(@PathVariable("id") int id, @ModelAttribute("todo") Todo todo) {
        todoService.updateTodo(id, todo);
        return "redirect:/";
    }

    @GetMapping("todo/update/{id}")
    public String editTodo(@PathVariable("id") int id, Model model) {
        Todo todo = todoService.getTodo(id);
        model.addAttribute("todo", todo);
        return "edit_todo";
    }

    @GetMapping("todo/delete/{id}")
    public String deleteTodo(@PathVariable("id") int id) {
        todoService.delete(id);
        return "redirect:/";
    }

    @GetMapping("todo/{id}")
    public String viewTodo(@PathVariable("id") int id, Model model) {
        Todo todo = todoService.getTodo(id);
        model.addAttribute("todo", todo);
        return "view_todo";
    }

    @GetMapping("todo/next/{id}")
    public String nextCategory(@PathVariable("id") int id) {
        todoService.nextCategory(id);
        return "redirect:/todo/" + id;
    }

    @GetMapping("todos/filter/{category}")
    public String filterTodos(@PathVariable("category")Category category, Model model) {
        List<Todo> filteredTodos = todoService.filter(category);
        model.addAttribute("todos", filteredTodos);
        return "index";
    }
}
