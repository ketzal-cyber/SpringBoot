package com.javadinamic.web.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javadinamic.web.models.Usuario;

@Controller
public class IndexController {

	@GetMapping({"/index","/"})
	public String home(Model model) {
		model.addAttribute("titulo", "Spring Framework!");
		
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario user = new Usuario();
		//SimpleDateFormat smdf = new SimpleDateFormat("yyyy/MM/dd");
		user.setCodUser(1);
		user.setUsuario("agente");
		user.setPassord("12345");
		user.setLogros("desarrollar");
		user.setFechaIngreso(new Date());
		user.setFechasalida(new Date());
		
		model.addAttribute("usuario", user);
		model.addAttribute("titulo", "Perfil del usuario ".concat(user.getUsuario()));
		return "perfil";
	}
	
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		List<Usuario> userList = getLista();
		
		model.addAttribute("titulo", "Listado de usuarios ");
		model.addAttribute("users", userList);
		return "listar";
	}
	
	
	
	/*
	 * String nombbre, String apeP, String apeM, String dNI, char genero, String domicilio,
			String telefono, String email, Date fechaNacimiento, String usuario,String logros,Date fechaIngreso
			@ModelAttribute("users") para pasara todas las vistas
			int codUser, String usuario, String logros, Date fechaIngreso
	 */
	public List<Usuario> getLista(){
		//Usuario user1 = new Usuario("Agent1","apep1","apem1","aa1a1",'m',"address1","111111","emai_1l@email.com",new Date(),"usuario1","logros1",new Date());
		
		/*
		List<Usuario> listUser = Arrays.asList(new Usuario("Agent1","apep1","apem1","aa1a1",'m',"address1","111111","emai_1l@email.com",new Date(),"usuario1","logros1",new Date()),
				new Usuario("Agent2","apep2","apem2","aa1a2",'m',"address2","111112","emai_2l@email.com",new Date(),"usuario2","logros2",new Date()),
				new Usuario("Agent3","apep3","apem3","aa1a3",'m',"address3","111113","emai_3l@email.com",new Date(),"usuario3","logros3",new Date()),
				new Usuario("Agent4","apep4","apem4","aa1a4",'m',"address4","111114","emai_4l@email.com",new Date(),"usuario4","logros4",new Date())
				);*/
		List<Usuario> listUser = Arrays.asList(new Usuario(1,"usuario1","logros1",new Date()),
				new Usuario(2,"usuario2","logros2",new Date()),
				new Usuario(3,"usuario3","logros3",new Date()),
				new Usuario(4,"usuario4","logros4",new Date()),
				new Usuario(5,"usuario5","logros5",new Date())
				);
		
		return listUser;
	}
	
}
