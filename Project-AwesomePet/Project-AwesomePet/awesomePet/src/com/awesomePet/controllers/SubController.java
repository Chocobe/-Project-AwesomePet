package com.awesomePet.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class SubController {
	abstract public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException;
}
