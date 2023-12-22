package com.spring.web.api.backend.tag;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/alias")
//Будет ли он отображаться в свагере?
@Tag(name="Admin setting: Alias API")
public class AliasRestController {
	private final AliasRepository aliasRepository;

    public AliasRestController(AliasRepository aliasRepository) {
        this.aliasRepository = aliasRepository;
    }

    @GetMapping("/")
	List<Alias> getAliases(){
		List<Alias> aliases = new ArrayList<>();
		aliasRepository
			.findAll()
			.forEach(aliases::add);
		return aliases;
	}

	@GetMapping("/{id}")
	ResponseEntity<Alias> getAlias(@PathVariable(name="id") Long alias_id){
		Optional<Alias> alias = aliasRepository.findById(alias_id);
		return alias
			.map(ResponseEntity::ok)
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/")
	ResponseEntity<Alias> addVacancy(@RequestBody Alias alias) {
		//Alias
		return Optional.of(aliasRepository.save(alias))
			.map(ResponseEntity::ok)
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

}
