package com.example.PokemonAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PokemonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonApiApplication.class, args);

		RestTemplate template = new RestTemplate();

		String[] pk_name = {"magikarp", "rayquaza", "rattata", "alvinho"};

		List<Pokemon> listaPokemon = new ArrayList<>();

		for (int i = 0; i <= pk_name.length-1; i++) {

			UriComponents uri = UriComponentsBuilder.newInstance()
					.scheme("https")
					.host("pokeapi.co")
					.path("api/v2/pokemon/")
					.pathSegment(pk_name[i])
					.build();

			ResponseEntity<Pokemon> entity = null;
			try {

				entity = template.getForEntity(uri.toUriString(), Pokemon.class);
				entity.getBody().setBusca(uri.toString());
				entity.getBody().setIndex("Pokemon" + (i + 1));
				listaPokemon.add(entity.getBody());

			} catch (RestClientException e) {
				e.getCause();
				//System.out.println(e);
			}
		}


		try {

			String novaLista = listaPokemon.toString().replace("[", "{").replace("]", "}");

//			Gson gson = new GsonBuilder().setPrettyPrinting().create();
//			String jsonAsString = gson.toJson(novaLista);

			File arquivo = new File("results.txt");

			if (!arquivo.exists()) {
				arquivo.createNewFile();
			}

			FileWriter escrevendo = new FileWriter(arquivo.getAbsoluteFile());
			BufferedWriter buffer = new BufferedWriter(escrevendo);

			buffer.write(novaLista);
			buffer.close();


			FileReader ler = new FileReader("results.txt");
			BufferedReader reader = new BufferedReader(ler);
			String linha;
			while( (linha = reader.readLine()) != null ){
				System.out.println(linha);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
