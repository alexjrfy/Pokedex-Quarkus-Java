package pokemon.resources;

import pokemon.DTO.PokemonDTO;
import pokemon.entities.Pokemon;

import javax.decorator.Delegate;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("pokedex")
@Consumes("application/json")
@Produces("application/json")
public class PokemonResource {
    @GET
    public List<Pokemon> getAll(){
        return Pokemon.listAll();
    }

    @POST
    @Transactional
    public void create(PokemonDTO dto){
        Pokemon p = new Pokemon();
        p.name = dto.name;
        p.number = dto.number;
        p.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void update(@PathParam("id") long id, PokemonDTO dto){
        Optional<Pokemon> p = Pokemon.findByIdOptional(id);
        if(p.isPresent()){
            Pokemon pokemon = p.get();
            pokemon.name = dto.name;
            pokemon.number = dto.number;
            pokemon.persist();
        }else{
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") long id){
        Optional<Pokemon> p = Pokemon.findByIdOptional(id);
//        if(p.isPresent()){
//            Pokemon pokemon = p.get();
//            pokemon.delete();
//        }else{
//            throw new NotFoundException();
//        }

        p.ifPresentOrElse(Pokemon::delete, () -> {throw new NotFoundException();});
    }
}
