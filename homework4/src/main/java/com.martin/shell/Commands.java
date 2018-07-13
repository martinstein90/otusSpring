package com.martin.shell;

import com.martin.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class Commands {
    private final InterviewService service;

    @Autowired
    public Commands(InterviewService service) {
        this.service = service;
    }

    @ShellMethod("Can you stand up for the truth interview")
    public void interview(@ShellOption String name, @ShellOption int amountQuestions) {
        service.interview(name, amountQuestions);
    }
}