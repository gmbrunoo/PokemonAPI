package com.example.PokemonAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
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

			try {

					ResponseEntity<Pokemon> entity = template.getForEntity(uri.toUriString(), Pokemon.class);
					entity.getBody().setBusca(uri.toString());
					entity.getBody().setIndex("Pokemon" + (i + 1));
					listaPokemon.add(entity.getBody());

			} catch (RestClientException e) {
				e.getCause();
				//System.out.println(e);
			}
		}

		System.out.println(listaPokemon);

		ObjectMapper mapper = new ObjectMapper();

		try {

			String content = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listaPokemon);

			File arquivo = new File("results.txt");

			if (!arquivo.exists()) {
				arquivo.createNewFile();
			}

			FileWriter escrevendo = new FileWriter(arquivo.getAbsoluteFile());
			BufferedWriter buffer = new BufferedWriter(escrevendo);

			buffer.write(content);
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
