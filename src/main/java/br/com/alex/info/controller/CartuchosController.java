package br.com.alex.info.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alex.info.model.Cartucho;
import br.com.alex.info.model.TipoCartucho;
import br.com.alex.info.repository.CartuchosRepo;

@Controller
@RequestMapping("/cartuchos")
public class CartuchosController {
	
	@Autowired
	private CartuchosRepo cartuchosRepo;
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		return novo(cartuchosRepo.getOne(id));
		
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Cartucho cartucho) {
		ModelAndView modelAndView = new ModelAndView("cartuchos/cad_cartucho");
		
		modelAndView.addObject(cartucho);
		modelAndView.addObject("tipos", TipoCartucho.values());
		
		return modelAndView;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cartucho cartucho, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cartucho);
		}
		
		cartuchosRepo.save(cartucho);
		
		attributes.addFlashAttribute("mensagem", "Cartucho salvo com sucesso!");
		
		return new ModelAndView("redirect:/cartuchos/novo");
		
	}

}
