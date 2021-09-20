package search.product.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "photo")
@Data
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(generator = "id")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo_url")
    private String photoUrl;

    public Photo(String photoUrl){
        this.photoUrl = photoUrl;
    }

}
