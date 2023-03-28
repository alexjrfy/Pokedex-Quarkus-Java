package pokemon.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import java.util.Date;
@Entity
public class Pokemon extends PanacheEntity{
    public String name;
    public int number;
    @CreationTimestamp
    public Date created_at;
    @UpdateTimestamp
    public Date updated_at;
}
