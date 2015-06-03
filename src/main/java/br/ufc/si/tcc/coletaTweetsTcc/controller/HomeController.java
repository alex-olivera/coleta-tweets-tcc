package br.ufc.si.tcc.coletaTweetsTcc.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import twitter4j.Twitter;
import br.ufc.si.tcc.coletaTweetsTcc.util.AutenticationFactory;
import br.ufc.si.tcc.coletaTweetsTcc.util.SearchTweets;
import br.ufc.si.tcc.coletaTweetsTcc.model.Search;
import br.ufc.si.tcc.coletaTweetsTcc.util.Constants;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/nova-busca")
	public String novaBusca(Model model) {

		model.addAttribute("search", new Search());

		return "nova-busca";

	}

	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
	public String buscar(@Valid @ModelAttribute("search") Search search,
			BindingResult result, RedirectAttributes redirect, Model model) {

		if (result.hasErrors()) {
			return "nova-busca";
		}

		long time = search.getTimeSearch();

		search.setTimeSearch(time * Constants.MINUTE_MILLISECONDS);

		Date dateCreation = new Date();
		search.setDateCreation(dateCreation);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = sdf.format(dateCreation);

		File raiz = new File(Constants.PATH);
		File pasta = new File(raiz, dateString);

		if (!pasta.exists())
			pasta.mkdir();

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(
					Constants.PATH + dateString + "/info.txt", true));

			bw.write("Descrição: " + search.getDescription());
			bw.write("\n");
			bw.write("Busca por: " + search.getValueSearch());
			bw.write("\n");
			bw.write("Data de criação: " + dateString);
			bw.write("\n");
			bw.write("Quantidade de tweets por busca: " + search.getQuantity());
			bw.write("\n");
			bw.write("Intervalo de busca: " + time + " minutos");
			bw.write("\n");
			bw.write("Repetições: " + search.getNumberSearch());
			bw.write("\n");

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Twitter twitter = AutenticationFactory.getTwitter();
		Thread searchThred = new Thread(new SearchTweets(search, twitter));
		searchThred.start();
		
		redirect.addFlashAttribute("info", "Busca iniciada com sucesso!");
		return "redirect:/";
	}

}
